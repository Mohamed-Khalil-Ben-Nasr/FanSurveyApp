package edu.lawrence.quiz_server;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SurveyDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    
    public List<Survey> findByDate() {
	String sql = "select * from survey where day = CURDATE()";
        RowMapper<Survey> rowMapper = new SurveyRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }
}
