package pl.lukasik.teamstatistic.service;


import org.springframework.stereotype.Service;
import pl.lukasik.teamstatistic.model.Team;
import pl.lukasik.teamstatistic.repository.StatisticRepository;

@Service
public class StatisticService {

    private final StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public void saveStatistic(Team team) {
        
    }
}
