package com.ryan.deployment_tracker;

public class DeploymentResponse{

    private Long id;
    private String applicationName;
    private String enviornment;
    private String version;
    private String status;

    public DeploymentResponse(Long id, String applicationName, String enviornment, String version, String status){
        
        this.id = id;
        this.applicationName = applicationName;
        this.enviornment = enviornment;
        this.version = version;
        this.status = status;
    }

    public Long getId(){
        return id;
    }

    public String getApplicationName(){
        return applicationName;
    }

    public String getEnviornment(){
        return enviornment;
    }

    public String getVersion(){
        return version;
    }

    public String getStatus(){
        return status;
    }
}