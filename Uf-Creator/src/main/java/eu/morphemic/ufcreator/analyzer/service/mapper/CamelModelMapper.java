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
                .Name(compositeMetric.getName())
                .Formula(compositeMetric.getFormula())
                .metricTemplate((MetricTemplateImpl) compositeMetric.getMetricTemplate()).build();

    }

    public static RawMetricDTO rawMetricToRawMetricDTO(RawMetric rawMetric){
        return RawMetricDTO.builder()
                .Name(rawMetric.getName())
                .metricTemplate((MetricTemplateImpl) rawMetric.getMetricTemplate()).build();
    }

    public static VariableDTO variableToVariableDTO(MetricVariable metricVariable){
        return VariableDTO.builder()
                .Name(metricVariable.getName())
                .Formula(metricVariable.getFormula())
                .component((ComponentImpl) metricVariable.getComponent())
                .isCurrentConfiguration(metricVariable.isCurrentConfiguration())
                .metricTemplate((MetricTemplateImpl) metricVariable.getMetricTemplate()).build();
    }
}
