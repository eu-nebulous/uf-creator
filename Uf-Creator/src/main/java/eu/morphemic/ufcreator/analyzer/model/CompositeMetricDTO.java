package eu.morphemic.ufcreator.analyzer.model;

import camel.metric.impl.MetricTemplateImpl;
import camel.metric.impl.MetricVariableImpl;
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
//    MetricTemplateImpl metricTemplate;

}
