# Softwaretechnik Workshop Gruppe 7
## Inhaltsverzeichnis
- [Softwaretechnik Workshop Gruppe 7](#softwaretechnik-workshop-gruppe-7)
  - [Inhaltsverzeichnis](#inhaltsverzeichnis)
  - [1 Systemkomponenten](#1-systemkomponenten)
    - [Infrastruktur](#infrastruktur)
    - [Software](#software)
    - [Source Control](#source-control)
    - [Ausgangsschnittstellen](#ausgangsschnittstellen)
    - [Zielsysteme und Schnittstellen](#zielsysteme-und-schnittstellen)
  - [2 Netzwerkkonzept](#2-netzwerkkonzept)
    - [Was wird benötigt](#was-wird-benötigt)
  - [3 Cloud-Bedarf](#3-cloud-bedarf)
    - [Autarke Anteile](#autarke-anteile)
    - [Darstellung Aufbau](#darstellung-aufbau)
  - [4 Support](#4-support)
    - [Entry point für Incidents](#entry-point-für-incidents)
    - [Supportzeit](#supportzeit)
    - [Reaktionszeit](#reaktionszeit)
    - [Wiederherstellungszeit](#wiederherstellungszeit)
    - [Wartungszugang erforderlich?](#wartungszugang-erforderlich)
## 1 Systemkomponenten
### Infrastruktur
Backend
- Digital Ocean Cloud
- Cloudflare DNS & TLS
  
Frontend
- Github Pages

CI/CD
- Github Actions
  
DB
- MariaDB

![components](documentation/diagrams/components.png)

### Software
Backend
- Java 17
- Quarkus
- RESTeasy, JPA

Frontend
- HTML
- JS mit jQuery


### Source Control
Github free tier 


### Ausgangsschnittstellen
- [OpenAPI Spec](documentation/api/appointment-service-v1.yaml)
- [Postman Collection](documentation/postman/Appointment%20Service.postman_collection.json)


### Zielsysteme und Schnittstellen
- Terminverwaltung
- Core Functionalities
  - verschiedene User anlegen, bearbeiten, entfernen
  - Termine anlegen, einladen, verschieben, entfernen
  - Prüfung ob Termin verfügbar
- Stretch
  - Userverwaltung mit OAuth2 
  - Benachrichtungen per Mail
  - User Stats
- Backend only -> REST Api 
- Dummy Frontend zur Demonstration der Backend Funktionalität

## 2 Netzwerkkonzept 
### Was wird benötigt
- Domain von Cloudflare
- TLS Zertifikate von Cloudflare
- Digital Ocean Load Balancer 
- nginx Ingress Controller

## 3 Cloud-Bedarf
### Autarke Anteile
- Digital Ocean K8s
### Darstellung Aufbau
## 4 Support
### Entry point für Incidents
### Supportzeit
### Reaktionszeit
### Wiederherstellungszeit
### Wartungszugang erforderlich?
