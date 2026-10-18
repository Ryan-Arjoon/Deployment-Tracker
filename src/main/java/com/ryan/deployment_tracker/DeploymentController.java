package com.ryan.deployment_tracker;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deployments")
public class DeploymentController{


    private final DeploymentService deploymentService;

    public DeploymentController(DeploymentService deploymentService){
        this.deploymentService = deploymentService;
    }

    @GetMapping
    public List<Deployment> getAllDeployments(){
        return deploymentService.getAllDeployments();
    }
}