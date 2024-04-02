package com.api.controller;

import java.util.List;

import com.api.service.EndpointService;
import com.api.model.Endpoint;

public class EndpointController {

    private final EndpointService endpointService;

     public EndpointController(EndpointService endpointService) {
        this.endpointService = endpointService;
    }

    public List<Endpoint> getAllEndpoints() {
        try {
            return endpointService.getAllEndpoints();
        } catch (Exception e) {
             e.printStackTrace();
            return null;  
        }
    }

    public Endpoint createEndpoint(Endpoint endpoint) {
        try {
            return endpointService.createEndpoint(endpoint);
        } catch (Exception e) {
            
            e.printStackTrace();
            return null; 
        }
    }
}
