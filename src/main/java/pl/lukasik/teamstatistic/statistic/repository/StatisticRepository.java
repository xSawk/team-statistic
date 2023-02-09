package pl.lukasik.teamstatistic.statistic.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lukasik.teamstatistic.statistic.model.Team;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatisticRepository extends JpaRepository<Team, Long> {

    Optional<Team> findByTeamName(String home_team);


}
