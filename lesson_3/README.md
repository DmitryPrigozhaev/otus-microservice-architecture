# Lesson 3

Basic Kubernetes entities: `Service`, `Ingress`

### Task

1. Create a simple RESTful CRUD to create, delete, view and update users.
   API example: https://app.swaggerhub.com/apis/otus55/users/1.0.0
2. Add a database for the application;
3. Application configuration must be stored in `Configmaps`;
4. Database access must be stored in `Secrets`;
5. Initial migrations should be documented as `Jobs`, if required.
6. `Ingresses` should also lead to the url `arch.homework/`.

### Provide output:

1. Link to [GitHub](https://github.com/) with manifests;
2. Application launch manual;
3. Command to install the database from helm, along with the `values.yaml` file;
4. Command to apply initial migrations the `kubectl apply -f` command, which runs the kubernetes manifests
   in the correct order;
5. Postman collection, which will provide examples of requests to the service to create, get,
   change and delete a user. Important: in the postman collection, use the base `arch.homework` url.

### Task with a star (+5 points)

+5 points for app templating in helm charts.