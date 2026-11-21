#!/usr/bin/env bash

# Port forwarding to OAuth2.0 authserver (for logging into the Chat application)
kubectl port-forward svc/authserver 9000:9000


