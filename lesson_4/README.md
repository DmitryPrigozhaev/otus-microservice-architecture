# Lesson 4

Deploy two versions of the application in a cluster and configure traffic
balancing between them.

### Task

Detailed description of the task: https://github.com/izhigalko/otus-homework-istio

1. Deploy Minikube;
2. Deploy [Istio](https://istio.io/) with Ingress gateway;
3. Deploy two versions of an application using [Istio](https://istio.io/);
4. Set up traffic balancing between application versions at the Gateway level 50% to 50%;

### Provide output

1. Take a screenshot of the service map in [Kiali](https://kiali.io/) with examples of calling two versions of the
   service.

# Solution

TODO

# Guide

### Preconditions

1. Docker installed;

2. Gradle installed;

3. Kubectl installed:

    1. Install the `kubectl` in any way possible (if required). Linux
       Mint [example](https://kubernetes.io/ru/docs/tasks/tools/install-kubectl/#%D1%83%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0-kubectl-%D0%B2-linux):

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

    1. Install the `minikube` in any way possible (if required). Linux
       Mint [example](https://kubernetes.io/ru/docs/tasks/tools/install-minikube/):

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

6. Istio installed:

    1. Install the `istio` in any way possible (if required). Linux Mint example:

        ```shell
        # download the latest release with the command:
        curl -sL https://istio.io/downloadIstioctl | sh -

        # add the istioctl client to your path:
        export PATH=$HOME/.istioctl/bin:$PATH
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

1. Create local docker image with name "lesson_4" in repository "dmitryprigozhaev":

   ```shell
   docker build -t dmitryprigozhaev/lesson_4:latest .
   ```

2. Push image to repository:

   ```shell
   docker push dmitryprigozhaev/lesson_4:latest
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
    # otus-lesson-4-nginx-ingress.yaml is available in the project
    kubectl create namespace m && \ 
        helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx/ && \ 
        helm repo update && \ 
        helm install nginx ingress-nginx/ingress-nginx --namespace m \
        -f otus-microservice-architecture/lesson_4/helm/ingress/otus-lesson-4-nginx-ingress.yaml
    ```

3. Create namespaces:

    ```shell
    # create the 'otus' namespace:
    kubectl create namespace otus
   
    # create operators namespaces:
    kubectl apply -f otus-microservice-architecture/lesson_4/istio/namespaces.yaml
    ```

4. Deploy [Jaeger](https://www.jaegertracing.io/):
   
    1. Add a repository to helm:
    
        ```shell
        helm repo add jaegertracing https://jaegertracing.github.io/helm-charts
        helm repo update
        ```

    2. Install operator that deploys Jaeger:

        ```shell
        helm install --version "2.19.0" -n \
            jaeger-operator -f jaeger/operator-values.yaml \
            jaeger-operator jaegertracing/jaeger-operator
        ```
       
    3. Deploy Jaeger:

        ```shell
        kubectl apply -f jaeger/jaeger.yaml
        ```
       
    4. Check Jaeger:

        ```shell
        kubectl get pod -n jaeger -l app.kubernetes.io/instance=jaeger
        ```
       
    5. Open the Jaeger web interface:

        ```shell
        minikube service -n jaeger jaeger-query-nodeport
        ```
       
See more: https://www.jaegertracing.io/docs/1.24/operator/
