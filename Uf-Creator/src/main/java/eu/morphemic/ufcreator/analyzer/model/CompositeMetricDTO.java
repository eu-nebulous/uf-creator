package eu.morphemic.ufcreator.analyzer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CompositeMetricDTO {
    String name;
    String component;
    String formula;

}
