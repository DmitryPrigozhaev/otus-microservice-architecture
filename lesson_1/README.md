# Lesson 1

Docker basics.

## Task

1. Create a minimal service that meets the requirements:

   1. Service responds on port `8000`;
   2. Service has an HTTP method `GET /health/` with a response:

       ```json
       {
         "status": "OK"
       }
       ```

2. Wrap the application in a docker image and push it to **[DockerHub](https://hub.docker.com/)**.

### Provide output:

1. Repository name and tag on **[DockerHub](https://hub.docker.com/)**;
2. Link to [GitHub](https://github.com/) with Dockerfile.

## Solution

The solution is a simple project, raised on port `8000`, with a single endpoint `GET /health`.

Docker image location: [Lesson 1](https://hub.docker.com/repository/docker/dmitryprigozhaev/lesson_1/general)

## Guide

### Preconditions

1. Docker installed;
2. Gradle installed.

### Build project

1. Download project to a pre-created directory:

   ```shell
   git clone https://github.com/DmitryPrigozhaev/otus-microservice-architecture.git .
   ```

2. Build project:

   ```shell
   gradle build
   ```
   
### Publish Docker image

1. Create local docker image with name "lesson_1" in repository "dmitryprigozhaev":

   ```shell
   docker build -t dmitryprigozhaev/lesson_1 .
   ```

2. Set image tag:
   
   1. Get target image ID from "dmitryprigozhaev/lesson_1" images list:

      ```shell
      docker images lesson_1 -q
      ```
      
   2. Set tag for the target image version:

      ```shell
      docker tag $(docker images dmitryprigozhaev/lesson_1:latest -q) dmitryprigozhaev/lesson_1:v1
      ```
   
3. Push image to repository:
   
   ```shell
   docker push dmitryprigozhaev/lesson_1:v1
   ```
