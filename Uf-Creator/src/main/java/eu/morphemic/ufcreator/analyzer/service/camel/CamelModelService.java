package eu.morphemic.ufcreator.analyzer.service.camel;

import camel.core.CamelModel;
import camel.metric.CompositeMetric;
import camel.metric.Metric;
import camel.metric.RawMetric;
import camel.metric.impl.MetricVariableImpl;

import java.util.List;

public interface CamelModelService {

    MetricVariableImpl getVariable(String resourceName, String variableName);

    RawMetric getRawMetric(String resourceName, String metricName);

    CompositeMetric getCompositeMetric(String resourceName, String metricName);

    List<MetricVariableImpl> getVariables(String resourceName);

    List<RawMetric> getRawMetrics(String resourceName);

    List<CompositeMetric> getCompositeMetrics(String resourceName);

    List<Metric> getAllMetrics(CamelModel camelModel);

    CamelModel getCamelModel(String resourceName);

    List<String> getCamelModelNames();
}
