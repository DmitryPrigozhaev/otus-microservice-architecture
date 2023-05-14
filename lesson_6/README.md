# Lesson 6

Backend for frontends. API Gateway.

### Task

Add user authentication and registration to the application.

Implement the scenario "Changing and viewing data in the client profile":

1. The user registers;
2. The user login under himself and receives data about his profile at a specific URL;
3. The user can change the data in the profile.

Profile data for reading and editing should not be available to other users (authenticated or not).

### Provide output

1. A description of the architectural solution and a diagram of the interaction of
services (in the form of a picture) an application installation command (from
helm or from manifests). Be sure to specify in which namespace you want to install.
2. API Gateway installation command if it is different from `nginx-ingress`.
3. Postman scripted tests:
   1. User 1 registration;
   2. Checking that changing and getting a user profile is not available without a login;
   3. User 1 login;
   4. Change user 1 profile;
   5. Checking that the profile has changed;
   6. User 1 logout (if any);
   7. User 2 registration;
   8. User 2 login;
   9. Checking that User 2 does not have access to read and edit User 1 profile.

Must be in tests:

1. Presence of `{{baseUrl}}` for URL;
2. Using `arch.homework` domain as initial value for `{{baseUrl}}`;
3. Using randomly generated data in a script;
4. Display request data and response data when run from the command line with `newman`.

### Solution

TODO
