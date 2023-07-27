package edu.lawrence.quiz_server;

import java.util.List;
import javax.naming.spi.DirStateFactory.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
@CrossOrigin(origins="*")
public class ResponseController {
    private ResponseDAO responseDAO;
    
    public ResponseController(ResponseDAO dao) {
        responseDAO = dao;
    }
    
    @PostMapping
    public String save(@RequestBody Response response) {
        return "\"" + responseDAO.save(response) + "\"";
    }    
}
