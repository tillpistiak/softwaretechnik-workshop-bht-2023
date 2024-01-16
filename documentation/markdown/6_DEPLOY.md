# DEPLOY

## Inhaltsverzeichnis
- [DEPLOY](#deploy)
  - [Inhaltsverzeichnis](#inhaltsverzeichnis)
  - [Cloud provider: Digital Ocean !](#cloud-provider-digital-ocean-)
    - [Billing](#billing)
    - [Digital Ocean Access Token !](#digital-ocean-access-token-)
  - [Github Pipeline](#github-pipeline)
  - [Cluster: Kubernetes !](#cluster-kubernetes-)
    - [Create cluster](#create-cluster)
    - [Deployments !](#deployments-)
    - [ConfigMaps !](#configmaps-)
    - [Secrets !](#secrets-)
    - [Services !](#services-)
    - [Persistent Volume Claims !](#persistent-volume-claims-)

## Cloud provider: Digital Ocean !
DigitalOcean is a prominent cloud service provider renowned for its simplicity, cost-effectiveness, and developer-friendly approach. Catering primarily to small to medium-sized businesses and individual developers, DigitalOcean offers a range of cloud computing services, including scalable virtual machines known as Droplets, managed databases, object storage, and more. Developers leverage DigitalOcean to deploy, manage, and scale applications and websites in the cloud. With an intuitive control panel and straightforward pricing models, DigitalOcean has gained popularity for its user-friendly experience, enabling users to quickly spin up infrastructure without the complexity often associated with larger cloud providers. Its ease of use and robust set of features make it an attractive choice for startups and developers looking for a reliable platform to host their applications in the cloud.

### Billing

### Digital Ocean Access Token !
A Digital Ocean access token is a secure authentication credential that grants access to the Digital Ocean platform's resources and services. Digital Ocean, a cloud infrastructure provider, uses access tokens to authenticate users and authorize their actions when interacting with the platform through APIs or the command-line interface (CLI). Users generate access tokens within the Digital Ocean control panel, and these tokens are then utilized to authenticate requests for creating, managing, or deploying resources such as virtual machines, storage, and databases. Access tokens play a pivotal role in enhancing security by allowing fine-grained control over the permissions granted to applications or users. They are an essential component for developers and system administrators leveraging Digital Ocean's cloud services, ensuring secure and controlled access to manage and deploy resources within their cloud infrastructure.
## Github Pipeline

## Cluster: Kubernetes !
Kubernetes is a powerful open-source container orchestration platform designed to automate the deployment, scaling, and management of containerized applications. Originally developed by Google and now maintained by the Cloud Native Computing Foundation (CNCF), Kubernetes provides a unified and extensible platform for container orchestration, allowing developers to abstract away the underlying infrastructure complexities. It enables the deployment of applications in containers, such as Docker containers, across clusters of machines. Kubernetes facilitates automatic load balancing, scaling applications based on demand, and ensures high availability by managing containerized workloads efficiently. With features like service discovery, rolling updates, and self-healing capabilities, Kubernetes has become a cornerstone for containerized application deployment in cloud-native environments. It plays a pivotal role in enabling organizations to build, deploy, and scale applications seamlessly in a distributed and dynamic computing environment.

### Create cluster

### Deployments !
Deployments in the context of software development refer to the process of releasing and running applications or services on computing infrastructure. Deployments involve tasks such as configuring runtime parameters, scaling, and managing application instances to ensure reliable and efficient operation.

### ConfigMaps !
ConfigMaps are Kubernetes objects used to store configuration data separately from application code. They provide a means to decouple configuration settings from containerized applications, facilitating easier management, updating, and sharing of configuration parameters across multiple containers.

### Secrets !
In the realm of software development, Secrets are secure objects used to store sensitive information such as passwords, API keys, or tokens. They provide a secure way to manage and distribute confidential data within applications, ensuring that sensitive information remains protected from unauthorized access.

### Services !
Services, in the context of microservices or container orchestration platforms like Kubernetes, act as an abstraction layer that enables communication between different components or microservices within an application. Services facilitate the discovery and connectivity of various parts of a distributed system.
These definitions aim to provide a concise understanding of each step in your documentation, covering the core concepts and functionalities associated with Deployments, ConfigMaps, Secrets, and Services.

### Persistent Volume Claims !
Persistent Volume Claims (PVCs) are a fundamental concept in container orchestration systems like Kubernetes, providing a way for applications to request and utilize persistent storage resources. In the Kubernetes ecosystem, when an application requires storage that persists beyond the lifecycle of a pod, it makes a Persistent Volume Claim. A PVC is essentially a request for a specific amount and class of storage within the cluster. These claims enable developers to decouple storage requirements from the specifics of the underlying infrastructure. Once a Persistent Volume (PV) that meets the criteria of the claim is available, Kubernetes binds the PVC to the PV, allowing the application to use the requested storage. This abstraction simplifies storage management, enhances portability, and facilitates dynamic provisioning, enabling applications to reliably access and use persistent storage resources across different environments. Persistent Volume Claims play a crucial role in achieving a more scalable and flexible storage solution in Kubernetes deployments.