package eu.morphemic.ufcreator.analyzer.service.camel;

import camel.core.CamelModel;
import camel.metric.CompositeMetric;
import camel.metric.Metric;
import camel.metric.RawMetric;
import camel.metric.impl.MetricVariableImpl;
import eu.morphemic.ufcreator.analyzer.model.CompositeMetricDTO;
import eu.morphemic.ufcreator.analyzer.model.RawMetricDTO;
import eu.morphemic.ufcreator.analyzer.model.VariableDTO;

import java.util.List;

public interface CamelModelService {

    MetricVariableImpl getVariable(String resourceName, String variableName);

    RawMetric getRawMetric(String resourceName, String metricName);

    CompositeMetric getCompositeMetric(String resourceName, String metricName);

    List<VariableDTO> getVariables(String resourceName);

    List<RawMetricDTO> getRawMetrics(String resourceName);

    List<CompositeMetricDTO> getCompositeMetrics(String resourceName);

    List<Metric> getAllMetrics(CamelModel camelModel);

    CamelModel getCamelModel(String resourceName);

    List<String> getCamelModelNames();
}
