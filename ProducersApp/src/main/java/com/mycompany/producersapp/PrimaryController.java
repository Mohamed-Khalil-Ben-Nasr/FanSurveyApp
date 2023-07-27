package com.mycompany.producersapp;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController implements Initializable  {
    @FXML
    private Label message;
    @FXML
    private TextField questionField;
    @FXML
    private TextField answersField;
    @FXML
    private DatePicker datePicker;
    
    private surveyDAO dao;
    
    @FXML
    private void submitNewSurvey(ActionEvent event) {
        LocalDate date = datePicker.getValue();
        String question = questionField.getText();
        String answers = answersField.getText();
        
        dao.insertNewSurvey(question, answers, date);
        message.setText("New Survey Stored in Database.");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dao = new surveyDAO();
    }    
   
}
