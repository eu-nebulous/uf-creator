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

    MetricVariableImpl getVariable(CamelModel camelModel, String variableName);

    RawMetric getRawMetric(CamelModel camelModel, String metricName);

    CompositeMetric getCompositeMetric(CamelModel camelModel, String metricName);

    List<VariableDTO> getVariables(CamelModel camelModel);

    List<RawMetricDTO> getRawMetrics(CamelModel camelModel);

    List<CompositeMetricDTO> getCompositeMetrics(CamelModel camelModel);

    List<Metric> getAllMetrics(CamelModel camelModel);

    List<String> getCamelModelNames();

    List<RawMetricDTO> getRawMetricsFromCDO(String resourceName);

    List<CompositeMetricDTO> getCompositeMetricsFromCDO(String resourceName);

    List<VariableDTO> getVariablesFromCDO(String resourceName);
}
