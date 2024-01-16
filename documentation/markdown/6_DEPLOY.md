# DEPLOY

## Inhaltsverzeichnis
- [DEPLOY](#deploy)
  - [Inhaltsverzeichnis](#inhaltsverzeichnis)
  - [Cloud provider: Digital Ocean](#cloud-provider-digital-ocean)
    - [Digital Ocean Access Token](#digital-ocean-access-token)
  - [Cluster: Kubernetes](#cluster-kubernetes)
    - [Deployments](#deployments)
    - [ConfigMaps](#configmaps)
    - [Secrets](#secrets)
    - [Services](#services)
    - [Persistent Volume Claims](#persistent-volume-claims)
  - [Github Pipeline](#github-pipeline)

## Cloud provider: Digital Ocean
*DigitalOcean is a prominent cloud service provider renowned for its simplicity, cost-effectiveness, and developer-friendly approach. Catering primarily to small to medium-sized businesses and individual developers, DigitalOcean offers a range of cloud computing services, including scalable virtual machines known as Droplets, managed databases, object storage, and more. Developers leverage DigitalOcean to deploy, manage, and scale applications and websites in the cloud.* (ChatGPT)

### Digital Ocean Access Token
*A Digital Ocean access token is a secure authentication credential that grants access to the Digital Ocean platform's resources and services. Users generate access tokens within the Digital Ocean control panel, and these tokens are then utilized to authenticate requests for creating, managing, or deploying resources such as virtual machines, storage, and databases.* (ChatGPT)


## Cluster: Kubernetes
*Kubernetes is a powerful open-source container orchestration platform designed to automate the deployment, scaling, and management of containerized applications. Originally developed by Google and now maintained by the Cloud Native Computing Foundation (CNCF), Kubernetes provides a unified and extensible platform for container orchestration, allowing developers to abstract away the underlying infrastructure complexities.*

*It enables the deployment of applications in containers, such as Docker containers, across clusters of machines. Kubernetes facilitates automatic load balancing, scaling applications based on demand, and ensures high availability by managing containerized workloads efficiently.* (ChatGPT)

### Deployments


### ConfigMaps
*ConfigMaps are Kubernetes objects used to store configuration data separately from application code. They provide a means to decouple configuration settings from containerized applications, facilitating easier management, updating, and sharing of configuration parameters across multiple containers.* (ChatGPT)

### Secrets


### Services
*n the context of Kubernetes (K8s), a "service" is a fundamental concept used to expose and provide network connectivity to a set of pods.*

*A service acts as an abstraction layer that allows other components within the cluster or external clients to access the pods without needing to know their specific IP addresses or ports. It provides a stable network endpoint that can be used to communicate with the pods, even if the underlying pods are scaled, relocated, or replaced.* (GitHub Copilot)

### Persistent Volume Claims
*Persistent Volume Claims (PVCs) are a fundamental concept in container orchestration systems like Kubernetes, providing a way for applications to request and utilize persistent storage resources. In the Kubernetes ecosystem, when an application requires storage that persists beyond the lifecycle of a pod, it makes a Persistent Volume Claim.* (ChatGPT) 

## Github Pipeline