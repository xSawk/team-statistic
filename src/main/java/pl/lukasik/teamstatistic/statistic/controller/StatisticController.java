package pl.lukasik.teamstatistic.statistic.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.lukasik.teamstatistic.statistic.model.MatchResult;
import pl.lukasik.teamstatistic.statistic.model.Team;
import pl.lukasik.teamstatistic.statistic.service.StatisticService;


import java.util.List;

@RestController
@RequestMapping("/api")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @PostMapping(value = "/match")
    public ResponseEntity<List<Team>> createMatch(@RequestBody MatchResult matchResult) {
        List<Team> teamStatistics = statisticService.saveMatchStatistic(matchResult);
        return new ResponseEntity<>(teamStatistics, HttpStatus.OK);
    }


}
