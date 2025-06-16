package org.example.partiteweb.controllers;

import org.example.partiteweb.pojos.Partita;
import org.example.partiteweb.pojos.Squadra;
import org.example.partiteweb.repositories.RepPartite;
import org.example.partiteweb.repositories.RepSquadre;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
@RestController
@RequestMapping("/partite")

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

    @PostMapping(value = "/getTeams",produces = MediaType.APPLICATION_JSON_VALUE)
    public  List<Partita> getTeams(@RequestParam String sport){
        return repPartite.getAllPartite(sport);
    }

   // @PostMapping("/createMatches")
   // public  void createMatches(@RequestBody String sport);

    @PostMapping(value = "/prendiRisultati",produces = MediaType.APPLICATION_JSON_VALUE)
    public  List<Integer> getResults(@RequestParam String sport,
                                             @RequestParam String giorno,
                                             @RequestParam List<Integer> predictions){
        return repPartite.getResults(sport,giorno,predictions);
    }

    @GetMapping("/listaSquadre")
    public ResponseEntity<List<Squadra>> getSquadreBySport(@RequestParam String sport) {
        List<Squadra> squadre = repSquadre.getSquadra(sport);
        return ResponseEntity.ok(squadre);
    }
}
