package com.ryan.deployment_tracker;

import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Deployment{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String applicationName;

    @NotBlank
    private String enviornment;

    @NotBlank
    private String version;

    @NotBlank
    private String status;
    
    //Initalize object
    public Deployment(Long id, String applicationName, String enviornment, String version, String status){

        this.id = id;
        this.applicationName = applicationName;
        this.enviornment = enviornment;
        this.version = version;
        this.status = status;
    }

    public Deployment(){
    }

    //Getters
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

    //Setters
    public void setId(Long id){
        this.id = id;
    }

    public void setApplicationName(String applicationName){
        this.applicationName = applicationName;
    }

    public void setEnviornment(String enviornment){
        this.enviornment = enviornment;
    }

    public void setVersion(String version){
        this.version = version;
    }

    public void setStatus(String status){
        this.status = status;
    }
}