package com.api.service;
import java.util.List;

import com.DatabaseConnection.DatabaseConnection;
import com.api.model.Endpoint;
import java.sql.*;
import java.util.ArrayList;


public class EndpointService {
   

    public List<Endpoint> getAllEndpoints() {
        List<Endpoint> endpoints = new ArrayList<>();
        try {Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM endpoint_table");
            while (resultSet.next()) {
                Endpoint endpoint = new Endpoint();
                endpoint.setId(resultSet.getInt("id"));
                endpoint.setEndpoint(resultSet.getString("endpoint"));
                endpoints.add(endpoint);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endpoints;
    }

    public Endpoint createEndpoint(Endpoint endpoint) {
        try {Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO endpoint_table (endpoint) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, endpoint.getEndpoint());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating endpoint failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    endpoint.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating endpoint failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return endpoint;
    }
}