package pl.lukasik.teamstatistic.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lukasik.teamstatistic.model.Team;
import pl.lukasik.teamstatistic.service.StatisticService;

@RestController
@RequestMapping("/api")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @PostMapping()
    public Team sendStatistic(@RequestBody Team team){
        statisticService.saveStatistic(team);


        return team;
    }


}
