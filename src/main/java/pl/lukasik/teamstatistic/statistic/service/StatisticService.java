package pl.lukasik.teamstatistic.statistic.service;

import org.springframework.stereotype.Service;
import pl.lukasik.teamstatistic.statistic.model.MatchResult;
import pl.lukasik.teamstatistic.statistic.model.Result;
import pl.lukasik.teamstatistic.statistic.model.Team;
import pl.lukasik.teamstatistic.statistic.repository.StatisticRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class StatisticService {

    private final StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }


    public List<Team> saveMatchStatistic(MatchResult matchResult) {
        Result result = matchResult.getResult();
        saveTeamHome(result);
        saveTeamAway(result);
        Team homeTeamStatistic =
                statisticRepository.findByTeamName(result.getHome_team()).orElseThrow();
        Team awayTeamStatistic =
                statisticRepository.findByTeamName(result.getAway_team()).orElseThrow();

        return Arrays.asList(homeTeamStatistic, awayTeamStatistic);
    }

    private void saveTeamHome(Result result) {
        Team teamHome = statisticRepository.findByTeamName(result.getHome_team()).orElseGet(() -> Team.builder()
                .teamName(result.getHome_team())
                .eventNumber(1)
                .points(calculateGainedPoints(result.getHome_score(), result.getAway_score()))
                .goalsScored(result.getHome_score())
                .goalsConceded(result.getAway_score())
                .build());
        if (teamHome.getId() != null) {
            statisticRepository.save(Team.builder()
                    .id(teamHome.getId())
                    .teamName(teamHome.getTeamName())
                    .eventNumber(teamHome.getEventNumber() + 1)
                    .points(teamHome.getPoints() + calculateGainedPoints(result.getHome_score(), result.getAway_score()))
                    .goalsScored(teamHome.getGoalsScored() + result.getHome_score())
                    .goalsConceded(teamHome.getGoalsConceded() + result.getAway_score())
                    .build());
        }
        statisticRepository.save(teamHome);
    }
    private void saveTeamAway(Result result) {
        Team teamAway = statisticRepository.findByTeamName(result.getAway_team()).orElseGet(() -> Team.builder()
                .teamName(result.getAway_team())
                .eventNumber(1)
                .points(calculateGainedPoints(result.getAway_score(), result.getHome_score()))
                .goalsScored(result.getAway_score())
                .goalsConceded(result.getHome_score())
                .build());

        if (teamAway.getId() != null) {
            statisticRepository.save(Team.builder()
                    .id(teamAway.getId())
                    .teamName(teamAway.getTeamName())
                    .eventNumber(teamAway.getEventNumber() + 1)
                    .points(teamAway.getPoints() + calculateGainedPoints(result.getAway_score(), result.getHome_score()))
                    .goalsScored(teamAway.getGoalsScored() + result.getAway_score())
                    .goalsConceded(teamAway.getGoalsConceded() + result.getHome_score())
                    .build());
        }
        statisticRepository.save(teamAway);
    }


    private int calculateGainedPoints(int goalsScored, int goalsConceded) {
        if (isDraw(goalsScored, goalsConceded)) {
            return 1;
        }
        if (isWin(goalsScored, goalsConceded)) {
            return 3;
        }
        return 0;
    }

    private boolean isDraw(int goalsScored, int goalsConceded) {
        return goalsScored - goalsConceded == 0;
    }

    private boolean isWin(int goalsScored, int goalsConceded) {
        return goalsScored > goalsConceded;
    }

}
