# Deployment-Tracker
Tracks application deployment stages

# Run Docker
```
docker build -t deployment-tracker:1.0 .
docker run -d -p 8081:8081 --name deployment-tracker deployment-tracker:1.0
docker stop deployment-tracker
docker rm deployment tracker
```
Clean Up Image
```
docker images
docker rmi deployment-trackerl:1.0
```