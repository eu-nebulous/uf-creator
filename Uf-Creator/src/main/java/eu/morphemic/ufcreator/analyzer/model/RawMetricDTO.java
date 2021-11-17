package eu.morphemic.ufcreator.analyzer.model;

import camel.metric.impl.MetricTemplateImpl;
import lombok.Builder;

@Builder
public class RawMetricDTO {
    String Name;
    String Component;
    MetricTemplateImpl metricTemplate;

}
