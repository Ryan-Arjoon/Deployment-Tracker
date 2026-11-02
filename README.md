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
```