package pl.lukasik.teamstatistic.statistic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MatchResult {
    private String type;
    private Result result;

}