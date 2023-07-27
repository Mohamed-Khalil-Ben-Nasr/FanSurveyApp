/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.lawrence.quiz_server;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author MohamedTheGoat
 */
public class SurveyRowMapper implements RowMapper<Survey> {
    @Override
    public Survey mapRow(ResultSet row, int rowNum) throws SQLException {
        Survey s = new Survey();
        s.setQuestion(row.getString("question"));
        s.setAnswers(row.getString("answers"));
        s.setDay(row.getString("day"));
        return s;
    }
    
}
