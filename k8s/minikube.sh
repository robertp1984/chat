#!/usr/bin/env bash

# General
minikube start -n 3
minikube addons enable metrics-server
minikube addons enable ingress

# Namespaces and current namespace
kubectl apply -f namespaces.yaml
kns chat


# Secrets
kubectl apply -f secrets.yaml


# Helm general
helm repo add bitnami https://charts.bitnami.com/bitnami
helm repo update


# Redis using Helm
helm install redis bitnami/redis -f redis/redis-values.yaml

# OAuth2 authentication server
kubectl apply -f authserver.yaml

# Application Chat
kubectl create secret generic openai-api-key --from-literal=OPENAI_API_KEY=${OPENAI_API_KEY}
kubectl create secret generic api-ninjas-api-key --from-literal=API_NINJAS_API_KEY=${API_NINJAS_API_KEY}
kubectl apply -f chat.yaml

# Prometheus
kubectl create secret generic slack-web-hook-url --from-literal=SLACK_WEB_HOOK_URL=${SLACK_WEB_HOOK_URL}
kubectl create configmap prometheus-config --from-file=prometheus/config/prometheus.yml --dry-run=client -o yaml | kubectl apply -f -
kubectl create configmap prometheus-rules --from-file=../docker/prometheus/rules/ --dry-run=client -o yaml | kubectl apply -f -
kubectl apply -f prometheus/prometheus.yaml

# AlertManager
kubectl create configmap alertmanager-config --from-file=../docker/alertmanager/ --dry-run=client -o yaml | kubectl apply -f -
kubectl apply -f alertmanager.yaml


# Ingress with TLS and dummy certificate
kubectl create secret tls ingress-tls --key ingress/tls/localhost.pem --cert ingress/tls/localhost.crt
kubectl apply -f ingress/ingress.yaml


# Minikube tunnel
minikube tunnel

# Port forwarding to OAuth2.0 authserver (for logging into the Chat application)
kubectl port-forward svc/authserver 9000:9000
