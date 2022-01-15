package eu.morphemic.ufcreator.analyzer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class VariableDTO {
    String name;
    Boolean isCurrentConfiguration;
    String formula;
    String type;
    String componentName;

}
