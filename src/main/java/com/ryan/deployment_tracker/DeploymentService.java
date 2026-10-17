package com.ryan.deployment_tracker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DeploymentService{
    
    private final List<Deployment> deployments = new ArrayList<>();

    public List<Deployment> getAllDeployments(){
        return deployments;
    }
}