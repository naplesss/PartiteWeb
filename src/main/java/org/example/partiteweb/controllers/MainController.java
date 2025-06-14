package org.example.partiteweb.controllers;

import org.example.partiteweb.pojos.Partita;
import org.example.partiteweb.repositories.RepPartite;
import org.example.partiteweb.repositories.RepSquadre;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController

public class MainController {
    private final RepPartite repPartite;
    private final RepSquadre repSquadre;

    public MainController(RepPartite repPartite, RepSquadre repSquadre) {
        this.repPartite = repPartite;
        this.repSquadre = repSquadre;
    }
    @PostMapping("/matchList")
    public List<Partita> getMatches(@RequestParam String sport, @RequestParam String giorno){
        return repPartite.getPartite(sport,giorno);
    }

    @PostMapping("/matchCalendar")
    public  List<Partita> getAllMatches(@RequestParam String sport){
        return repPartite.getAllPartite(sport);
    }

    @PostMapping("/getTeams")
    public  List<Partita> getTeams(@RequestParam String sport){
        return repPartite.getAllPartite(sport);
    }

   // @PostMapping("/createMatches")
   // public  void createMatches(@RequestBody String sport);

    @PostMapping("/getResults")
    public  List<Integer> getResults(@RequestParam String sport,
                                             @RequestParam String giorno,
                                             @RequestParam List<Integer> predictions){
        return repPartite.getResults(sport,giorno,predictions);
    }
}
