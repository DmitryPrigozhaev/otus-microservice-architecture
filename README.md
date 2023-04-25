# Microservice Architecture

Lesson repository for the Microservice Architecture course from OTUS.

## Useful tricks

1. Automatic input completion for `kubectl`:

* `bash`

    ```shell
    # Set up autocompletion in the current bash session, the bash-completion package must first be installed:
    source <(kubectl completion bash)

    # Add autocomplete permanently to the bash shell:
    echo "source <(kubectl completion bash)" >> ~/.bashrc
    ```

* `zsh`

    ```shell
    # Set up autocompletion in the current zsh session:
    source <(kubectl completion zsh)
  
    # Add autocomplete permanently to the zsh shell:
    echo "[[ $commands[kubectl] ]] && source <(kubectl completion zsh)" >> ~/.zshrc
    ```

See more: https://kubernetes.io/ru/docs/reference/kubectl/cheatsheet/

2. Automatic input completion for `istioctl`:

    ```shell
    # Generate autocomplete instructions:
    istioctl collateral --zsh -o ~/.istioctl
   
    # Set up autocompletion in the current zsh session:
    source ~/.istioctl/_istioctl
   
    # Add autocomplete permanently to the zsh shell:
    echo "source ~/.istioctl/_istioctl" >> ~/.zshrc
    ```

See more: https://istio.io/latest/docs/ops/diagnostic-tools/istioctl/#istioctl-auto-completion

3. Use `minikube` dashboards for system monitoring:

   ```shell
   minikube dashboard & disown
   ```

4. To switch the context, run the command:

   ```shell
   kubectl config use-context minikube
   ```

5. Use `otus` namespace by default:

   ```shell
   kubectl config set-context --current --namespace=otus
   ```

6. Connecting to the database terminal:

   ```shell
   kubectl exec -it ["database pod name"] -- mysql --user=application_user --password=application_password
   ```
