#!/usr/bin/env bash

# General
minikube start -n 3
minikube addons enable metrics-server
minikube addons enable ingress

