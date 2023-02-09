package pl.lukasik.teamstatistic.statistic.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Result {
    private String home_team;
    private String away_team;
    private int home_score;
    private int away_score;


}
