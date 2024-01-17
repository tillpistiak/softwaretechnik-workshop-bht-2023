# MONITOR

## Table of Contents
- [MONITOR](#monitor)
  - [Table of Contents](#table-of-contents)
  - [Micrometer](#micrometer)
    - [Configuration](#configuration)
    - [Custom Metrics](#custom-metrics)
  - [Prometheus](#prometheus)
    - [Configuration](#configuration-1)
  - [Grafana](#grafana)
    - [Configuration TBD](#configuration-tbd)

## Micrometer
*Micrometer is a Java library that provides a simple and consistent way to instrument your code for monitoring purposes. It is designed to be vendor-agnostic, meaning it can work with various monitoring systems such as Prometheus, Graphite, Datadog, and more.*

*With Micrometer, you can easily collect and expose metrics from your application, allowing you to monitor its performance, behavior, and resource usage. It provides a unified API for recording different types of metrics, including counters, timers, gauges, histograms, and distribution summaries.* (Github Copilot)

### Configuration
1. add dependency to pom.xml
   ```xml
    <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-micrometer-registry-prometheus</artifactId>
    </dependency>
   ```
1. default metrics are now available on the application path `/q/metrics`
### Custom Metrics
TBD

## Prometheus
*Prometheus is an open-source monitoring and alerting system that is widely used in the software development industry. It is designed to collect and store time-series data, allowing you to monitor the performance and health of your applications and infrastructure.*

*Prometheus follows a pull-based model, where it periodically scrapes metrics data from various targets, such as applications, services, and servers. These targets expose their metrics through an HTTP endpoint, which Prometheus can access and retrieve the data from.*

*Once Prometheus collects the metrics, it stores them in a time-series database, where they can be queried and analyzed using a powerful query language called PromQL. This allows you to create custom dashboards, set up alerts based on specific conditions, and gain insights into the behavior and performance of your systems.* (Github Copilot)

### Configuration
1. add k8s [deployment](../../k8s/prometheus-deployment.yaml), [service](../../k8s/prometheus-svc.yaml), [pvc](../../k8s/prometheus-pvc.yaml)
2. add k8s [config-map](../../k8s/prometheus-config.yaml) and configure prometheus with the prometheus.yaml file
   ```yaml
   global:
     scrape_interval: 15s # in which should prometheus scrape the defined metrics endpoints
  
   scrape_configs:  
     - job_name: 'appointment-service'
       metrics_path: /q/metrics
       static_configs:
         - targets: ['appointment-service:8080']
   ```
3. deploy to k8s with `kubectl apply -f ...` or run the deploy action

## Grafana
*Grafana is an open-source data visualization and monitoring tool that allows you to create interactive dashboards and visualize metrics from various data sources. It provides a user-friendly interface for exploring and analyzing data, making it easier to monitor the performance and health of your applications and infrastructure.*

*With Grafana, you can connect to different data sources such as databases, time-series databases (like Prometheus), cloud monitoring services, and more. It supports a wide range of data formats and protocols, making it flexible and adaptable to different monitoring setups.* (Github Copilot)


### Configuration TBD
1. add grafana k8s file
2. deploy to k8s with `kubectl apply -f ...` or run the deploy action
3. add a new subdomain, dns entry, tls cert & ingress for grafana
4. open grafana on the new subdomain
5. login with admin:admin
6. set new personal password
7. connect to prometheus
8. import java micrometer dashboard