package edu.lawrence.quiz_server;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/votes")
@CrossOrigin(origins="*")
public class VoteController {
    private VoteDAO voteDAO;
    
    public VoteController(VoteDAO dao) {
        this.voteDAO = dao;
    }
    
    @GetMapping
    public List<Vote> getVotes() {
        return voteDAO.getVotes();
    }    
}
