package edu.lawrence.quiz_server;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class VoteRowMapper implements RowMapper<Vote>{
    @Override
    public Vote mapRow(ResultSet row, int rowNum) throws SQLException {
        Vote v = new Vote();
        v.setAnswer(row.getString("answer"));
        v.setNumberOfVotes(row.getInt("numberOfVotes"));
        return v;
    }
}
