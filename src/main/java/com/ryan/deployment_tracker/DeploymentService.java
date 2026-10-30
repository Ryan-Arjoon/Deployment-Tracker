package com.ryan.deployment_tracker;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
//Test
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

    public Optional<Deployment> getDeploymentById(Long id){
        return deploymentRepository.findById(id);
    }

    public boolean deleteDeployment(Long id){

        if (!deploymentRepository.existsById(id)){
            return false;
        }

        deploymentRepository.deleteById(id);
        return true;
    }

    public Optional<Deployment> updateDeployment(Long id, Deployment updatedDeployment){
        
        Optional<Deployment> existingDeployment = deploymentRepository.findById(id);

        if (existingDeployment.isEmpty()){
            return Optional.empty();
        }

        updatedDeployment.setId(id);
        return Optional.of(deploymentRepository.save(updatedDeployment));
    }
}