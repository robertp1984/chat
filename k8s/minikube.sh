#!/usr/bin/env bash

# General
minikube start
minikube addons enable metrics-server
minikube addons enable ingress

kubectl apply -f namespaces.yaml

# Application Chat
kns chat
kubectl create secret generic openai-api-key --from-literal=OPENAI_API_KEY=${OPENAI_API_KEY}
kubectl create secret generic api-ninjas-api-key --from-literal=API_NINJAS_API_KEY=${API_NINJAS_API_KEY}
kubectl apply -f chat.yaml

# Ingress with TLS and dummy certificate
kns chat
kubectl -n chat create secret tls ingress-tls --key tls/localhost.pem --cert tls/localhost.crt
kubectl apply -f ingress.yaml

# Minikube tunnel
minikube tunnel

