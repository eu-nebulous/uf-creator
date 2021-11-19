package eu.morphemic.ufcreator.analyzer.model;

import camel.deployment.impl.ComponentImpl;
import camel.metric.impl.MetricTemplateImpl;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class VariableDTO {
    String name;
    Boolean isCurrentConfiguration;
//    ComponentImpl component;
//    MetricTemplateImpl metricTemplate;
    String formula;

}
