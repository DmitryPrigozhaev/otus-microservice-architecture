# Lesson 2

Basic Kubernetes entities: `Pod`, `ReplicaSet`, `Deployment`

### Task

1. Create a minimal service that meets the requirements:

   1. Service responds on port `8000`;
   2. Service has an HTTP method `GET /health/` with a response:

   ```json
   {
     "status": "OK"
   }
   ```

2. The service must be packaged in a **Docker** container and push the image
   to **[DockerHub](https://hub.docker.com/)**;

3. Write deployment manifests in **k8s** for the service:
   1. Manifests must describe entities **Deployment**, **Service**, **Ingress**;
   2. **Deployment** can contain **Liveness**, **Readiness** probes;
   3. Number of replicas must be at least 2;
   4. **Image** of the container must be specified with **[DockerHub](https://hub.docker.com/)**;
   5. Host in **Ingress** must be **arch.homework**.
   6. As a result, after applying the manifests, a GET request to http://arch.homework/health should return JSON:

      ```json
      {
        "status": "OK"
      }
      ```

### Provide output:

1. Link to [GitHub](https://github.com/) with manifests. The manifests must be in the same
   directory so that they can all be applied with a single `kubectl apply -f` command;
2. URL to get service response.

### Task with a star (+5 points)

**Ingress** should have a rule that forwards all requests from **/otusapp/{student name}/*** to
service with path rewrite, where **{student name}** is the name of the student.
