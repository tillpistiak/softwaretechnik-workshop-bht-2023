# OPERATE

## Table of Contents
- [OPERATE](#operate)
  - [Table of Contents](#table-of-contents)
  - [Domain, DNS, TLS: Cloudflare](#domain-dns-tls-cloudflare)
  - [Load Balancer / Ingress Controller](#load-balancer--ingress-controller)
  - [Ingress](#ingress)

## Domain, DNS, TLS: Cloudflare
*A domain is a human-readable, alphanumeric label associated with a specific IP address on the internet. It serves as a user-friendly way to locate and access websites or resources on the World Wide Web. Domains play a critical role in simplifying the way users navigate the internet, providing a recognizable and standardized naming system for online resources.* (ChatGPT)

Configuration as described in [How to get it running in the cloud - Cloudflare](../../README.md#cloudflare)


## Load Balancer / Ingress Controller
*A load balancer and an ingress controller are both components used in managing network traffic in a distributed system. Let's break down each of them:*

*Load Balancer: A load balancer is responsible for distributing incoming network traffic across multiple servers or resources to ensure efficient utilization and high availability. It acts as a traffic manager, evenly distributing requests to different servers based on various algorithms such as round-robin, least connections, or IP hash.
The main purpose of a load balancer is to prevent any single server from becoming overwhelmed with traffic, thus improving the overall performance and reliability of the system. It helps to scale horizontally by adding more servers and distributing the load among them.* (Github Copilot)

Configuration as described in [How to get it running in the cloud - Digital Ocean](../../README.md#digital-ocean)


## Ingress
*In Kubernetes (K8s), an Ingress is an API object that manages external access to services within a cluster. It acts as a traffic controller, routing incoming requests to the appropriate services based on rules defined in the Ingress resource.* (Github Copilot)

The placeholders in the ingress.yaml are replaced with the real values by the "deploy" workflow. This way the actual host address can be configurable without building a new version of the project. 

The ingress.yaml file contains information about to which service to route if a request is being sent for one of the hosts.
```yaml
- host: <SERVICE_HOST> # if the request was sent to the host of the service
  http:
    paths:
      - path: /
        pathType: Prefix
        backend:
          service:
            name: appointment-service # to which service should be routed
            port:
              number: 8080 # which port should be called on the service
```

The ingress is also responsible for the tls termination.
```yaml
tls:
  - hosts: # which hosts should be protected by this secret (certificate)
      - <SERVICE_HOST>
      - <GRAFANA_HOST>
    secretName: tls-secret # where to find the certificate and private key
```