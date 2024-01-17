# AppointmentService
## Table of Contents
- [AppointmentService](#appointmentservice)
  - [Table of Contents](#table-of-contents)
  - [How to start developing locally?](#how-to-start-developing-locally)
  - [How to get it running in the cloud?](#how-to-get-it-running-in-the-cloud)
    - [Preparation](#preparation)
      - [Digital Ocean](#digital-ocean)
      - [Cloudflare](#cloudflare)
      - [Github](#github)
    - [Deployment](#deployment)
    - [Monitoring](#monitoring)
  - [Lessons Learned](#lessons-learned)
  - [What's next?](#whats-next)

## How to start developing locally?
 The following steps are required to run the application locally on **MacOS**. Please make sure [Homebrew](https://brew.sh/), [SDKMAN](https://brew.sh/) and [Git](https://www.git-scm.com/downloads) are already present on your machine. 

For other operating systems the same tools are required. For installation guides please refer to the official documentation.
1. install necessary software <br>
   - Java JDK 17 (https://www.oracle.com/java/technologies/downloads/)
      ```bash
      sdk install java 17.0.10-amzn
      ```
   - IDE (https://code.visualstudio.com/Download)
      ```bash
      brew install vscode
      ```
   - Docker (https://www.docker.com/products/docker-desktop/)
      ```bash
      brew install docker
      ```
   - Maven (https://maven.apache.org/install.html) <br>
     :information_source: you can skip this step if you want to use maven wrapper
      ```bash
      brew install maven
      ```
2. checkout and open project in IDE
   ```bash
   git clone https://github.com/tillpistiak/softwaretechnik-workshop-bht-2023.git

   cd softwaretechnik-workshop-bht-2023
   
   code .
   ```

3. start database (make sure docker is already running)
    ```bash
    docker-compose up
    ```

4. run app
    ```bash
    mvn clean quarkus:dev
    ```
    :information_source: you can start developing now. Quarkus will recognize most changes itself and apply them via hot reload. Some will require a restart
5. run tests & quality gates
    ```bash
    mvn clean verify
    ```


## How to get it running in the cloud?

### Preparation

#### Digital Ocean

1. create Digital Ocean account, add payment details etc
2. create a new kubernetes cluster (Digital Ocean `Kubernetes / Create Cluster`)
   - choose Frankfurt as datacenter
   - keep the recommended version 
   - choose a pool & cluster name 
   - select cheapest options and only one node <br>
     :warning: selecting more than one node will not benefit availability as the application doesnt support running on multiple nodes in parallel at the moment
   - total monthly costs should be 12€ as of January 2024![create cluster](documentation/screenshots/do_create_cluster.png)

3. create an access token for your local machine (Digital Ocean)
   - go to `API / Tokens / Personal Access Tokens` and click on "Generate New Token"
     - choose a name and select the prefered expiration time
     - enable write access
     - click on "Generate Token"![generate token](documentation/screenshots/do_generate_token.png)
     - copy the generated token, it will be required for the next step and cant be shown again
   - create a **second** access token to use in your Github Repository, keep for later
  
4. configure kubectl on your local machine & install ingress-controller (Terminal)
   1. install with brew
      ```bash
      brew install doctl 
      ```
   2. login to your DO account with the generated token
      ```bash
      doctl auth init # paste the token when asked for it
      doctl kubernetes cluster kubeconfig save <use_your_cluster_name>
      ```
   3. check kubernetes status
      ```bash
      kubectl cluster-info
      # Output should look like
      #> Kubernetes control plane is running at https://d3e5528e-70df-4a9c-b511-47594d2e6fdb.k8s.ondigitalocean.com
      #> CoreDNS is running at https://d3e5528e-70df-4a9c-b511-47594d2e6fdb.k8s.ondigitalocean.com/api/v1/namespaces/kube-system/services/kube-dns:dns/proxy

      
      kubectl get nodes
      # Output should look like
      #> NAME                   STATUS   ROLES    AGE   VERSION
      #> pool-3mz19dpbc-x5qe1   Ready    <none>   25d   v1.28.2
      ```

   4. create load balancer & ingress controller ( :warning: doing this will create a 12$/month Digital Ocean Loadbalancer)
      ```bash
      # install ingress controller
      kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v1.1.1/deploy/static/provider/do/deploy.yaml
      
      
      # confirm pods have started
      kubectl get pods -n ingress-nginx -l app.kubernetes.io/name=ingress-nginx --watch

      # Output
      # NAME                                       READY   STATUS      RESTARTS   AGE
      # ingress-nginx-controller-c96557986-m47rq   1/1     Running     0          13m
      
      # confirm Load Balancer was successfully created
      # make sure EXTERNAL-IP is set for the Load Balancer
      kubectl get svc --namespace=ingress-nginx
      ```
    5. write `EXTERNAL-IP` down for later

#### Cloudflare

1. register domain ([Cloudflare Dashboard](https://dash.cloudflare.com))
   1. create a free account https://www.cloudflare.com/de-de/plans/ 
   2. go to `Domain Registration -> Register Domains`
   3. search for the domain
   4. select domain & complete payment
   ![cloudflare domain registration](documentation//screenshots/cloudflare_domain_registration.png)

2. create dns entries ([Cloudflare Dashboard](https://dash.cloudflare.com))
   1. select your domain on the homepage of the cloudflare dashboard
   2. go to `DNS / Records`
   3. click on "Add record"
      1. select type `A`
      2. choose a name for the subnaim, e.g. "appointmentservice" -> this will create a DNS entry for the domain "`appointmentservice.<your_domain>.<your_tld>`"
      3. enter the `EXTERNAL_IP` from the Load Balancer into the `IPv4` field
      4. keep `Proxied` enabled
      5. click "Save" ![add dns](documentation/screenshots/cloudflare_dns.png)
   4. do the same for the grafana subdomain, e.g. "`grafana.<your_domain>.<your_tld>`"![grafana dns](documentation/screenshots/cloudflare_grafana_dns.png)


3. configure tls ([Cloudflare Dashboard](https://dash.cloudflare.com))
   1. go to `SSL/TLS / Overview`
   2. enable `Full(strict)` as encryption mode ![tls mode](image.png)
   3. go to `SSL/TLS / Origin Server`
   4. click on "Create Certificate"
   5. select `ECC` as private key type
   6. enter **BOTH** subdomains (service & grafana) as Hostnames
   7. select the preferred validity (will be done again before the certificate expires)
   8. click on "Create"
   9. copy both private key and certificate and store them securely. if you loose the private key you'll have to generate a new keypair   10. ![tls cert creation](image-1.png)
   10. convert the certificate and key to base64
       ```bash
       echo "<paste_certificate>"|base64
       echo "<paste_key>"|base64
       ```


#### Github

1. go to your repository and open the settings
2. go to `"Secrets and variables" / Actions`
3. Use the "new repository secret" button to create the following secrets, paste the mentioned values
   ```properties
   CLUSTER_NAME=<name_of_kubernetes_cluster> # can be found in DO dashboard

   DIGITALOCEAN_ACCESS_TOKEN=<do_access_token> # DO access token you created for GitHub
   
   MYSQL_PASSWORD=<password> # you can choose the password
   
   MYSQL_ROOT_PASSWORD=<password> # you can choose the password
   
   TLS_CERT=<tls_cert_base64> # base64 encoded tls certificate from cloudflare
   
   TLS_KEY=<tls_key_base64> # base64 encoded tls private key from cloudflare
   ```
4. result should look like this![Alt text](image-2.png)

### Deployment



### Monitoring

## Lessons Learned
- Nginx config DO
- Write access deployment.yaml / prometheus
- SSL Zertifikate von Cloudflare
- h2 in mem testing
- Derby user


## What's next?
- User Authentication & Roles
- Alert Manager?
- Load Testing

