package eu.morphemic.ufcreator.analyzer.model;

import camel.metric.impl.MetricTemplateImpl;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class RawMetricDTO {
    String name;
    String component;
//    MetricTemplateImpl metricTemplate;

}
