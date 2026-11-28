
package com.ryan.deployment_tracker;

import jakarta.validation.constraints.NotBlank;

public class CreateDeploymentRequest {

    @NotBlank
    private String applicationName;

    @NotBlank
    private String enviornment;

    @NotBlank
    private String version;

    @NotBlank
    private String status;

    public String getApplicationName() {
        return applicationName;
    }

    public String getEnviornment() {
        return enviornment;
    }

    public String getVersion() {
        return version;
    }

    public String getStatus() {
        return status;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setEnviornment(String enviornment) {
        this.enviornment = enviornment;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}