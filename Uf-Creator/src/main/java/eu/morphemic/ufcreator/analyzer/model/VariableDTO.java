package eu.morphemic.ufcreator.analyzer.model;

import camel.deployment.impl.ComponentImpl;
import camel.metric.impl.MetricTemplateImpl;
import lombok.Builder;

@Builder
public class VariableDTO {
    String Name;
    Boolean isCurrentConfiguration;
    ComponentImpl component;
    MetricTemplateImpl metricTemplate;
    String Formula;

}
