package org.example.partiteweb.controllers;

import org.example.partiteweb.pojos.Partita;
import org.example.partiteweb.pojos.Squadra;
import org.example.partiteweb.repositories.RepPartite;
import org.example.partiteweb.repositories.RepSquadre;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
@RestController

public class MainController {

    private final RepPartite repPartite;
    private final RepSquadre repSquadre;

    public MainController(RepPartite repPartite, RepSquadre repSquadre) {
        this.repPartite = repPartite;
        this.repSquadre = repSquadre;
    }
    @PostMapping(value = "/listaPartite",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Partita> getMatches(@RequestParam String sport, @RequestParam String giorno){
        return repPartite.getPartite(sport,giorno);
    }

    @PostMapping(value = "/CalendarioPartite",produces = MediaType.APPLICATION_JSON_VALUE)
    public  List<Partita> getAllMatches(@RequestParam String sport){
        return repPartite.getAllPartite(sport);
    }

    @PostMapping(value = "/listaSquadre",produces = MediaType.APPLICATION_JSON_VALUE)
    public  List<Squadra> listaSquadre(@RequestParam String sport){
        return repSquadre.getSquadra(sport);
    }

   // @PostMapping("/createMatches")
   // public  void createMatches(@RequestBody String sport);

    @PostMapping(value = "/prendiRisultati",produces = MediaType.APPLICATION_JSON_VALUE)
    public  List<Integer> getResults(@RequestParam int pronostico1, @RequestParam int pronostico2){
        List<Integer> pronostici = new java.util.ArrayList<>();
        pronostici.add(pronostico1);
        pronostici.add(pronostico2);
        return repPartite.getResults("lacrosse", LocalDate.now().toString(),pronostici);
    }
}
