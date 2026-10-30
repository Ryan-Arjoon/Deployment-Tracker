# Deployment-Tracker
Tracks application deployment stages

# Run Docker
```
docker build -t deployment-tracker:1.0 .
docker run -d 8081:8081 --name deployment-tracker deployment-tracker:1.0
``'