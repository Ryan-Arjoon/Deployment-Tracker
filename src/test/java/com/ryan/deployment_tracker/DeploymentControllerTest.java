package com.ryan.deployment_tracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class DeploymentControllerTest{

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldCreateDeployment() throws Exception{
        String deploymentJson = """
            {
                "applicationName": "deployment-tracker",
                "enviornment": "dev",
                "version": "1.0.0",
                "status": "SUCCESS"
            }
            """;

            mockMvc.perform(post("/deployments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(deploymentJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.applicationName").value("deployment-tracker"))
                .andExpect(jsonPath("$.enviornment").value("dev"));
    }

    @Test
    void shouldRejectDeploymentWithBlankApplicationName() throws Exception{

        String deploymentJson = """
            {
                "applicationName": "",
                "enviornment": "dev",
                "version": "1.0.0",
                "status": "SUCCESS"
            }""";

        mockMvc.perform(post("/deployments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(deploymentJson))
            .andExpect(status().isBadRequest());
        
    }
}