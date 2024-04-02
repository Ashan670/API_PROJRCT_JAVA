package com.api.service;
import java.util.List;
import com.api.model.Student;
import java.sql.*;
import java.util.ArrayList;
import com.DatabaseConnection.DatabaseConnection;

public class StudentService {
  

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try {Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM student_table");
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGrade(resultSet.getString("grade"));
                // Assuming you have a method to set endpointId
                student.setEndpointId(resultSet.getInt("endpoint_id"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> getStudentsByEndpoint(String endpointName) {
        List<Student> students = new ArrayList<>();
        try {
            String sql = "SELECT s.* FROM student_table s " +
                         "JOIN endpoint_table e ON s.endpoint_id = e.id " +
                         "WHERE e.endpoint = ?";
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, endpointName);
            
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGrade(resultSet.getString("grade"));
                student.setEndpointId(resultSet.getInt("endpoint_id"));
                students.add(student);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }


    public Student createStudent(Student student) {
        try {Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO student_table (name, age, grade, endpoint_id) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getGrade());
            statement.setInt(4, student.getEndpointId());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating student failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating student failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}