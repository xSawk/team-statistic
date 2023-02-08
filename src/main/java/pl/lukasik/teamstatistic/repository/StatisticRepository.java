package pl.lukasik.teamstatistic.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukasik.teamstatistic.model.Team;

public interface StatisticRepository extends JpaRepository<Team, Long> {
}
