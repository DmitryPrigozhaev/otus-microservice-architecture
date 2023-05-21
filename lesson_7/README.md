# Lesson 7

Prometheus. Grafana.

### Task

Instrument the service from the last lesson with metrics in the Prometheus format
using the library for your framework and program language. Make a dashboard in
`Grafana`, which would have metrics broken down by API methods:

1. Latency (response time) with quantiles of 0.5, 0.95, 0.99, max;
2. RPS;
3. Error Rate (number of 500 responses).

Add graphs to the dashboard with metrics for the whole service, taken from `nginx-ingress-controller`:

1. Latency (response time) with quantiles of 0.5, 0.95, 0.99, max;
2. RPS;
3. Error Rate (number of 500 responses).

Set up an alert in the `Grafana` for Error Rate and Latency.

### Provide output:

1. Screenshots of dashboards with charts at the time of service stress testing. For example,
   after 5-10 minutes of exercise;
2. JSON dashboards.

### Task with a star (+5 points)

Using existing system metrics from `Kubernetes`, add charts with metrics to the dashboard:

1. Application pods memory consumption;
2. Application pods CPU consumption.

Instrument the database with the prometheus exporter for this database.
Add graphs with database operation metrics to the general dashboard.

# Solution

// TODO

# Guide

### Preconditions

1. Docker installed;

2. Gradle installed;

3. Kubectl installed:

   1. Install the `kubectl` in any way possible (if required). Linux Mint [example](https://kubernetes.io/ru/docs/tasks/tools/install-kubectl/#%D1%83%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0-kubectl-%D0%B2-linux):

      ```shell
      # download latest version
      curl -LO https://storage.googleapis.com/kubernetes-release/release/`curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt`/bin/linux/amd64/kubectl
      
      # make binary executable
      chmod +x ./kubectl
      
      # move the binary to the directory from the PATH environment variable
      sudo mv ./kubectl /usr/local/bin/kubectl
      
      # make sure the latest version is installed
      kubectl version --client
      ```

4. Minikube installed:

   1. Install the `minikube` in any way possible (if required). Linux Mint [example](https://kubernetes.io/ru/docs/tasks/tools/install-minikube/):

      ```shell
      # download latest version
      curl -Lo minikube https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 && chmod +x minikube
      
      # making the minikube executable available from any directory
      sudo install minikube /usr/local/bin/
      
      # check cluster status
      minikube status
      ```

5. Helm installed:

   1. Install the `helm` in any way possible (if required). Linux Mint example via `snap`:

      ```shell
      # install helm
      snap install helm --classic
      
      # check helm version
      helm versoin
      ```

### Build project

1. Download project to a pre-created directory:

   ```shell
   git clone https://github.com/DmitryPrigozhaev/otus-microservice-architecture.git
   ```

2. Build project:

   ```shell
   gradle build
   ```

### Publish Docker image

1. Create local docker image with name "lesson_7" in repository "dmitryprigozhaev":

   ```shell
   docker build -t dmitryprigozhaev/lesson_7:latest .
   ```

2. Push image to repository:

   ```shell
   docker push dmitryprigozhaev/lesson_7:latest
   ```

### Run an Application in a Cluster

1. Configure `minikube`:

   ```shell
   # start kubernetes in Docker with `minikube`
   minikube start --driver=docker
   minikube tunnel & disown
   ```

2. Install the nginx ingress controller via `helm`:

   ```shell
   # otus-lesson-7-nginx-ingress.yaml is available in the project
   kubectl create namespace m && \ 
     helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ && \ 
     helm repo update && \ 
     helm install nginx ingress-nginx/ingress-nginx --namespace m \
     -f otus-microservice-architecture/lesson_7/helm/ingress/otus-lesson-7-nginx-ingress.yaml
   ```

3. Create namespace:

   ```shell
   kubectl create namespace otus
   ```

4. Run application via helm:

   1. Update dependencies, in particular, load the database image for the chart:

      ```shell
      helm dependency update otus-microservice-architecture/lesson_7/helm
      ```

   2. Examine a chart for possible issues:

      ```shell
      helm lint otus-microservice-architecture/lesson_7/helm
      ```

   3. Install the chart:

      ```shell
      helm install lesson7 otus-microservice-architecture/lesson_7/helm
      ```
