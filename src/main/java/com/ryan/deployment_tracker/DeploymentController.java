package com.ryan.deployment_tracker;

import java.util.List;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Deployment createDeployment(@Valid @RequestBody Deployment deployment){
        return deploymentService.createDeployment(deployment);
    }
}