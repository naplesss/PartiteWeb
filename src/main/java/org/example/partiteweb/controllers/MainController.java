package org.example.partiteweb.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class MainController {
    @PostMapping("/matchList")
    public abstract List<Match> getMatches(@RequestParam String sport, @RequestParam LocalDate date);

    @PostMapping("/matchCalendar")
    public abstract List<Match> getAllMatches(@RequestParam String sport);

    @PostMapping("/getTeams")
    public abstract List<Team> getTeams(@RequestParam String sport);

    @GetMapping("/getAllTeams")
    public abstract List<Team> getAllTeams();

    @PostMapping("/createMatches")
    public abstract void createMatches(@RequestBody String sport);

    @PostMapping("/getResults")
    public abstract List<Integer> getResults(@RequestParam String sport,
                                             @RequestParam Date date,
                                             @RequestParam List<Integer> predictions);
}
