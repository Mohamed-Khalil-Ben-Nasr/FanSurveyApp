package edu.lawrence.quiz_server;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ResponseRowMapper implements RowMapper<Response>  {
    @Override
    public Response mapRow(ResultSet row, int rowNum) throws SQLException {
        Response r = new Response();
        r.setSurvey_id(row.getInt("survey_id"));
        r.setAnswer(row.getString("answer"));
        return r;
    }
}
