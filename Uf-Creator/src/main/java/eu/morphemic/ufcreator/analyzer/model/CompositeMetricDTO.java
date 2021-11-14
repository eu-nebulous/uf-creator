package eu.morphemic.ufcreator.analyzer.model;

import camel.metric.impl.MetricTemplateImpl;
import camel.metric.impl.MetricVariableImpl;
import lombok.Builder;

@Builder
public class CompositeMetricDTO {
    String Name;
    String Component;
    String Formula;
    MetricTemplateImpl metricTemplate;

}
