# Deployment-Tracker
Tracks application deployment stages

# Run Docker
```
docker build -t deployment-tracker:1.0 .
docker run -d -p 8081:8081 --name deployment-tracker deployment-tracker:1.0
docker stop deployment-tracker
docker rm deployment-tracker
```
Clean Up Image
```
docker images
docker rmi deployment-tracker:1.0
```

# Kubernetes Commands
```
kubectl apply -f k8s/deployment.yml
kubectl get deployments
kubectl get pods
kubectl get pods -o wide
kubectl describe pods <podName>
kubectl describe deployment deployment-tracker
kubectl logs <podName>

kubectl apply -f k8s/service.yml
kubectl get services
kubectl get endpoints deployment-tracker-service

kubectl apply -f k8s/configmap.yml
kubectl get configmaps
kubectl describe configmap <configmapName>
kubectl exec <podName> -- printenv

kubectl apply -f .\k8s\secret.yml
kubectl get secrets
kubectl exec <podName> -- printenv <secretName>

kubectl rollout status deployment/deployment-tracker
kubectl rollout history deployment/deployment-tracker
kubectl rollout undo deployment/deployment-tracker


kubectl scale deployment deployment-tracker --replicas=3

kubectl get deployment metrics-server -n kube-system
kubectl rollout status deployment/metrics-server -n kube-system
kubectl get pods -n kube-system

kubectl top nodes
kubectl top pods

kubectl apply -f k8s/hpa.yml
kubectl get hpa
kubectl describe hpa <hpaName>
kubectl get hpa --watch
```