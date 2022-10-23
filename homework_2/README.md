# Homework

Architecture and basic entities of Kubernetes: `ConfigMap`, `Job`.

### Task

Learn how to create a simple RESTful CRUD service.

### Description

Create the simplest RESTful CRUD for creating, deleting, viewing and updating users.

API: https://app.swaggerhub.com/apis/otus55/users/1.0.0

1. Add a database for the application;
2. Application configuration must be stored in `Configmaps`;
3. Database access must be stored in `Secrets`;
4. Initial migrations should be documented as `Jobs`, if required;
5. Ingresses should also lead to the url `arch.homework/` (as in the previous task).

Provide output:

1. The link to [GitHub](https://github.com/) with manifests. The manifests must be in the same directory
   so that they can all be applied with a single `kubectl apply -f` command;
2. Instructions for launching the application:
   1. Command to install the database from `helm`, along with the `values.yaml` file;
   2. Command to apply initial migrations;
   3. Command `kubectl apply -f`, which runs the kubernetes manifests in the correct order.
3. Postman collection, which will provide examples of requests to the service to create, get, change
and delete a user. Important: in the Postman collection, use the base url - `arch.homework`.

### Task with a star (+5 points)

Application templating in `helm` charts.
