# Microservice Architecture

Lesson repository for the Microservice Architecture course from OTUS.

## Useful tricks

1. Automatic input completion for `kubectl`:

* BASH

   ```shell
   source <(kubectl completion bash) # set up autocompletion in the current bash session, the bash-completion package must first be installed.
   echo "source <(kubectl completion bash)" >> ~/.bashrc # add autocomplete permanently to the bash shell.
   ```

* ZSH

   ```shell
   source <(kubectl completion zsh) # set up autocompletion in the current zsh session
   echo "[[ $commands[kubectl] ]] && source <(kubectl completion zsh)" >> ~/.zshrc # add autocomplete permanently to the zsh shell.
   ```

See more: https://kubernetes.io/ru/docs/reference/kubectl/cheatsheet/

2. Use `minikube` dashboards for system monitoring:

   ```shell
   minikube dashboard & disown
   ```

3. To switch the context, run the command:

   ```shell
   kubectl config use-context minikube
   ```

4. Use `otus` namespace by default:

   ```shell
   kubectl config set-context --current --namespace=otus
   ```

5. Connecting to the database terminal:

   ```shell
   kubectl exec -it ["database pod name"] -- mysql --user=application_user --password=application_password
   ```

