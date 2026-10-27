package com.ryan.deployment_tracker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DeploymentService{
    
    private final DeploymentRepository deploymentRepository;

    public DeploymentService(DeploymentRepository deploymentRepository){
        this.deploymentRepository = deploymentRepository;
    }

    public List<Deployment> getAllDeployments(){
        return deploymentRepository.findAll();
    }

    public Deployment createDeployment(Deployment deployment){
        return deploymentRepository.save(deployment);
    }
}