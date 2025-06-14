package org.example.partiteweb.repositories;

import jdk.jfr.Registered;
import org.example.partiteweb.pojos.Squadra;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepSquadre {
    private final JdbcTemplate jdbc;
    public RepSquadre(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public List<Squadra> getSquadra(String sport){
        String sql = "SELECT * FROM squadre WHERE sport = ?";
        RowMapper<Squadra> rowMapper = (rs, rowNum) ->
                new Squadra(
                        rs.getInt("id"),
                        rs.getString("nomesquadra"),
                        rs.getString("sport")
                );
        return jdbc.query(sql, rowMapper, sport);
    }

}
