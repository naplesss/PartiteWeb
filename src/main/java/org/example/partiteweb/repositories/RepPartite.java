package org.example.partiteweb.repositories;

import org.example.partiteweb.pojos.Partita;
import org.example.partiteweb.pojos.Squadra;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Repository
public class RepPartite {
    private final JdbcTemplate jdbc;
    private final RepSquadre repSquadre;

    public RepPartite(JdbcTemplate jdbc, RepSquadre repSquadre) {
        this.jdbc = jdbc;
        this.repSquadre = repSquadre;
    }

    public List<Partita> getPartite(String sport, String giorno) {
        String sql = "SELECT * FROM partite WHERE sport = ? and giorno = ?";
        RowMapper<Partita> rowMapper = (rs, rowNum) ->
                new Partita(
                        rs.getInt("id"),
                        rs.getString("id_squadracasa"),
                        rs.getString("id_squadrafuori"),
                        rs.getString("giorno"),
                        rs.getInt("risultato"),
                        rs.getString("sport")
                );
        return jdbc.query(sql, rowMapper, sport, giorno);
    }

    public List<Integer> getResults(String sport, String giorno, List<Integer> predictions) {
        String sql = "SELECT * FROM partite WHERE sport = ? and giorno = ?";
        RowMapper<Integer> rowMapper = (rs, rowNum) ->
        {
            return rs.getInt("risultato");
        };
        List<Integer> risultato = jdbc.query(sql, rowMapper, sport, giorno);
        int rowNum = 1, c = 0;
        for (Integer prediction : predictions) {
            if (Objects.equals(prediction, risultato.get(rowNum))) {
                c++;
            }
            rowNum++;
        }
        risultato.add(c);
        return risultato;
    }

    public void createPartite(String sport) {
        String sql = "INSERT INTO partite (sport,id_squadracasa,id_squadrafuori, giorno,risultato) VALUES (?,?,?,?,?)";
        List<Squadra> squadre = repSquadre.getSquadra(sport);
        Random random = new Random();
        int id1, id2;
        for (int i = 0; i < random.nextInt(5) + 5; i++) {
            id1 = random.nextInt(squadre.size());
            do {
                id2 = random.nextInt(squadre.size());
            } while (id1 == id2);
            jdbc.update(sql,
                    LocalDate.now().toString(),
                    squadre.get(id1).getNomesquadra(),
                    squadre.get(id2).getNomesquadra(),
                    sport,
                    random.nextInt(3)
            );
        }

    }

    public List<Partita> getAllPartite(String sport) {
        String sql = "SELECT * FROM partite WHERE sport=?";
        RowMapper<Partita> partitaRowMapper = (r, i) -> {
            Partita rowObject = new partita();
            rowObject.setId(r.getInt("ID"));
            rowObject.setGiorno(r.getString("Giorno").String());
            rowObject.setId_squadracasa(r.getString("squadracasa"));
            rowObject.setId_squadrafuori(r.getString("squadrafuori"));
            return rowObject;
        };
        return jdbc.query(sql, partitaRowMapper, sport);
    }

}

