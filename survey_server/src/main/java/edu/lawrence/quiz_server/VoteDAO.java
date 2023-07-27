package edu.lawrence.quiz_server;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class VoteDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    public List<Vote> getVotes() {
	String sql = "select answer, COUNT(answer) AS numberOfVotes FROM response GROUP BY answer";
        RowMapper<Vote> rowMapper = new VoteRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }
}
