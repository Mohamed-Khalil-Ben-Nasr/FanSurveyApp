package edu.lawrence.quiz_server;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/survey")
@CrossOrigin(origins="*")

public class SurveyController {
    private SurveyDAO surveyDAO;
    
    public SurveyController(SurveyDAO dao) {
        this.surveyDAO = dao;
    }
    
    @GetMapping
    public List<Survey> findQuestionOfTheDay() {
        return surveyDAO.findByDate();
    }    
}
