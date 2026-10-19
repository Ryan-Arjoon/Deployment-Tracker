package com.ryan.deployment_tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class DeploymentService{
    
    private final List<Deployment> deployments = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public List<Deployment> getAllDeployments(){
        return deployments;
    }

    public Deployment createDeployment(Deployment deployment){
        deployment.setId(nextId.getAndIncrement());
        deployments.add(deployment);
        return deployment;
    }
}