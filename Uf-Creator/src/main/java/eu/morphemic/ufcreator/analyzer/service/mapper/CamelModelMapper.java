package eu.morphemic.ufcreator.analyzer.service.mapper;

import camel.metric.CompositeMetric;
import camel.metric.MetricVariable;
import camel.metric.RawMetric;
import camel.metric.impl.MetricVariableImpl;
import eu.morphemic.ufcreator.analyzer.model.CompositeMetricDTO;
import eu.morphemic.ufcreator.analyzer.model.RawMetricDTO;
import eu.morphemic.ufcreator.analyzer.model.VariableDTO;

import static eu.morphemic.ufcreator.analyzer.service.camel.CamelModelServiceImpl.getAnnotationOfMetricVariable;

public class CamelModelMapper {

    public static CompositeMetricDTO compositeMetricToCompositeMetricDTO(CompositeMetric compositeMetric) {
        return CompositeMetricDTO.builder()
                .name(compositeMetric.getName())
                .formula(compositeMetric.getFormula())
                .build();

    }

    public static RawMetricDTO rawMetricToRawMetricDTO(RawMetric rawMetric) {
        return RawMetricDTO.builder()
                .name(rawMetric.getName())
                .build();
    }

    public static String getComponentName(MetricVariable metricVariable) {
        if (metricVariable.getComponent() == null) {
            return "";
        } else {
            return metricVariable.getComponent().getName();
        }
    }

    public static VariableDTO variableToVariableDTO(MetricVariable metricVariable) {
        return VariableDTO.builder()
                .name(metricVariable.getName())
                .type(getAnnotationOfMetricVariable((MetricVariableImpl) metricVariable))
                .formula(metricVariable.getFormula())
                .componentName(getComponentName(metricVariable))
                .isCurrentConfiguration(metricVariable.isCurrentConfiguration())
                .build();
    }
}
