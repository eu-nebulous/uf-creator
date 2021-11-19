package eu.morphemic.ufcreator.analyzer.service.mapper;

import camel.deployment.impl.ComponentImpl;
import camel.metric.CompositeMetric;
import camel.metric.MetricVariable;
import camel.metric.RawMetric;
import camel.metric.impl.CompositeMetricImpl;
import camel.metric.impl.MetricTemplateImpl;
import camel.metric.impl.MetricVariableImpl;
import camel.metric.impl.RawMetricImpl;
import eu.morphemic.ufcreator.analyzer.model.CompositeMetricDTO;
import eu.morphemic.ufcreator.analyzer.model.RawMetricDTO;
import eu.morphemic.ufcreator.analyzer.model.VariableDTO;

public class CamelModelMapper {

    public static CompositeMetricDTO compositeMetricToCompositeMetricDTO(CompositeMetric compositeMetric){
        return CompositeMetricDTO.builder()
                .name(compositeMetric.getName())
                .formula(compositeMetric.getFormula())
                .build();

    }

    public static RawMetricDTO rawMetricToRawMetricDTO(RawMetric rawMetric){
        return RawMetricDTO.builder()
                .name(rawMetric.getName())
                .build();
    }

    public static VariableDTO variableToVariableDTO(MetricVariable metricVariable){
        return VariableDTO.builder()
                .name(metricVariable.getName())
                .formula(metricVariable.getFormula())
                .isCurrentConfiguration(metricVariable.isCurrentConfiguration())
                .build();
    }
}
