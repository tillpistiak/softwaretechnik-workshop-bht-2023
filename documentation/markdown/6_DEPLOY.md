# DEPLOY

## Inhaltsverzeichnis
- [DEPLOY](#deploy)
  - [Inhaltsverzeichnis](#inhaltsverzeichnis)
  - [Cloud provider: Digital Ocean](#cloud-provider-digital-ocean)
    - [Digital Ocean Access Token](#digital-ocean-access-token)
  - [Cluster: Kubernetes](#cluster-kubernetes)
    - [Deployments](#deployments)
  - [Github Pipeline](#github-pipeline)

## Cloud provider: Digital Ocean
*DigitalOcean is a prominent cloud service provider renowned for its simplicity, cost-effectiveness, and developer-friendly approach. Catering primarily to small to medium-sized businesses and individual developers, DigitalOcean offers a range of cloud computing services, including scalable virtual machines known as Droplets, managed databases, object storage, and more. Developers leverage DigitalOcean to deploy, manage, and scale applications and websites in the cloud.* (ChatGPT)

Configuration as described in [How to get it running in the cloud - Digital Ocean](../../README.md#digital-ocean)

### Digital Ocean Access Token
*A Digital Ocean access token is a secure authentication credential that grants access to the Digital Ocean platform's resources and services. Users generate access tokens within the Digital Ocean control panel, and these tokens are then utilized to authenticate requests for creating, managing, or deploying resources such as virtual machines, storage, and databases.* (ChatGPT)

Configuration as described in [How to get it running in the cloud - Digital Ocean](../../README.md#digital-ocean)

## Cluster: Kubernetes
*Kubernetes is a powerful open-source container orchestration platform designed to automate the deployment, scaling, and management of containerized applications. Originally developed by Google and now maintained by the Cloud Native Computing Foundation (CNCF), Kubernetes provides a unified and extensible platform for container orchestration, allowing developers to abstract away the underlying infrastructure complexities.*

*It enables the deployment of applications in containers, such as Docker containers, across clusters of machines. Kubernetes facilitates automatic load balancing, scaling applications based on demand, and ensures high availability by managing containerized workloads efficiently.* (ChatGPT)


Configuration as described in [How to get it running in the cloud - Digital Ocean](../../README.md#digital-ocean)


### Deployments
*In Kubernetes, a Deployment is a high-level object that defines how to create and manage a group of identical pods. Pods are the smallest and most basic unit of deployment in Kubernetes, representing a single instance of a running process or application.*

*A Deployment ensures that a specified number of pod replicas are running and available at all times. If a pod fails or is terminated, the Deployment controller automatically replaces it with a new pod to maintain the desired number of replicas.*

The project consists of the following deployments
- appointment-service - thats the main application we're developing
- mariadb-deployment - persistent database
- prometheus - to scrape the metrics
- grafana - to visualize the scraped metrics
- ingress-nginx-controller
as well as some auxiliary deployments which are necessary to run the k8s cluster.


## Github Pipeline
To deploy the project directly from Github we created another workflow "deploy". This workflow can be triggered manually per `workflow_dispatch` but will also be triggered automatically every time the build workflow succeeds. 
The pipeline does the following steps:
1. Use the `digitalocean/action-doctl` action and the DO personal access token to log into doctl
   ```yaml
      - name: Install doctl
        uses: digitalocean/action-doctl@v2
        with:
          token: ${{ secrets.DIGITALOCEAN_ACCESS_TOKEN }}
   ```
2. replace the secret placeholders with actual values from Github Secrets
   ```yaml
      - name: Replace placeholders with tls secrets
        run: sed -i 's|<CERT>|${{ secrets.TLS_CERT }}|' $GITHUB_WORKSPACE/k8s/tls-secret.yaml && sed -i 's|<KEY>|${{ secrets.TLS_KEY }}|' $GITHUB_WORKSPACE/k8s/tls-secret.yaml
   ```
3. configure `kubectl` on the runner with `doctl`
   ```yaml
      - name: Save DigitalOcean kubeconfig with short-lived credentials
        run: doctl kubernetes cluster kubeconfig save --expiry-seconds 600 ${{ secrets.CLUSTER_NAME }}
   ```
4. deploy the application
   ```yaml
      - name: Deploy to DigitalOcean Kubernetes
        run: kubectl apply -f $GITHUB_WORKSPACE/k8s -n appointment-service
   ```
5. restart the appointment-service deployment
   ```yaml
      - name: Restart Deployment
        run: kubectl rollout restart deployment appointment-service -n appointment-service
   ```
6. verify the pod has started again
   ```yaml
      - name: Verify deployment
        run: kubectl rollout status deployment/appointment-service -n appointment-service  --timeout=15s
   ```