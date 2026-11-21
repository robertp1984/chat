#!/usr/bin/env bash

# Minikube initialization
minikube start -n 3
minikube addons enable metrics-server
minikube addons enable ingress

