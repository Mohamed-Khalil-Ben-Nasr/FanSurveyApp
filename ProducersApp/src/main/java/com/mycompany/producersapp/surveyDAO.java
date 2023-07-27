
package com.mycompany.producersapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class surveyDAO {
    private Statement statement;
    
    public surveyDAO(){     
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection
        ("jdbc:mysql://localhost:3306/surveys?user=student&password=Cmsc250!");
        statement = connection.createStatement();
    
        insertStatement = connection.prepareStatement(insertSQL);
        } catch(Exception ex){
            System.out.println("Problem opening database connection.");
            ex.printStackTrace();
        }
    }
    
    private String insertSQL = "insert into survey(question,answers,day) values(?,?,?)";
    private PreparedStatement insertStatement;
    public void insertNewSurvey(String q, String a, LocalDate d ){
       try{
           insertStatement.setString(1, q);
            insertStatement.setString(2, a);
            insertStatement.setDate(3, java.sql.Date.valueOf(d));
            insertStatement.executeUpdate();     
       }catch(SQLException ex) {
                ex.printStackTrace();
        }
                
    }
    
    
    
}
