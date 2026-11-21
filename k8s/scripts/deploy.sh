#!/usr/bin/env bash

# Namespaces and current namespace
kubectl apply -f apps/namespaces.yaml
kubectl config set-context --current --namespace=chat


# Secrets
kubectl apply -f apps/secrets.yaml
kubectl create secret generic api-keys --from-literal=OPENAI_API_KEY="${OPENAI_API_KEY}"  --from-literal=API_NINJAS_API_KEY="${API_NINJAS_API_KEY}"  --from-literal=SLACK_WEB_HOOK_URL="${SLACK_WEB_HOOK_URL}"

# Helm general
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update


# Redis using Helm
helm install redis bitnami/redis -f helm/redis/values.yaml

# OAuth2 authentication server
kubectl apply -f apps/authserver/authserver.yaml

# Application Chat
kubectl apply -f apps/chat/chat.yaml

# Prometheus
kubectl create configmap prometheus-config --from-file=config/prometheus/prometheus.yml --dry-run=client -o yaml | kubectl apply -f -
kubectl create configmap prometheus-rules --from-file=config/prometheus/rules/ --dry-run=client -o yaml | kubectl apply -f -
kubectl apply -f apps/prometheus/prometheus.yaml

# AlertManager
kubectl create configmap alertmanager-config --from-file=config/alertmanager --dry-run=client -o yaml | kubectl apply -f -
kubectl apply -f apps/alertmanager/alertmanager.yaml


# Ingress with TLS and dummy certificate
kubectl create secret tls ingress-tls --key apps/ingress/tls/localhost.pem --cert apps/ingress/tls/localhost.crt
kubectl apply -f apps/ingress/ingress.yaml


