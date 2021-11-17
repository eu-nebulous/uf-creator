package eu.morphemic.ufcreator.analyzer.service.camel;

import camel.core.CamelModel;
import camel.metric.CompositeMetric;
import camel.metric.Metric;
import camel.metric.MetricModel;
import camel.metric.RawMetric;
import camel.metric.impl.CompositeMetricImpl;
import camel.metric.impl.MetricTypeModelImpl;
import camel.metric.impl.MetricVariableImpl;
import eu.morphemic.ufcreator.analyzer.model.CompositeMetricDTO;
import eu.morphemic.ufcreator.analyzer.model.RawMetricDTO;
import eu.morphemic.ufcreator.analyzer.model.VariableDTO;
import eu.morphemic.ufcreator.analyzer.service.cdo.CdoService;
import eu.morphemic.ufcreator.analyzer.service.mapper.CamelModelMapper;
import eu.morphemic.ufcreator.communication.cdo.CdoServerApi;
import eu.paasage.mddb.cdo.client.exp.CDOSessionX;
import eu.passage.upperware.commons.model.tools.CamelModelTool;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.common.util.EList;
import org.jclouds.rest.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CamelModelServiceImpl implements CamelModelService {

    private CdoServerApi cdoServerApi;
    private CdoService cdoService;

    public List<String> getCamelModelNames() {
        List<String> allXmiModels = cdoService.getAllXmi();
        allXmiModels.sort(String::compareTo);
        return allXmiModels;
    }

    public CamelModel getCamelModel(String resourceName) {
        CDOSessionX cdoSessionX = cdoServerApi.openSession();
        CDOTransaction transaction = cdoServerApi.openTransaction(cdoSessionX);
        return cdoServerApi.getCamelModel(resourceName, transaction);
    }

    @Override
    public MetricVariableImpl getVariable(String resourceName, String variableName) {
        CamelModel camelModel = getCamelModel(resourceName);
        return CamelModelTool.getVariable(camelModel, variableName).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public RawMetric getRawMetric(String resourceName, String metricName) {
        CamelModel camelModel = getCamelModel(resourceName);
        return CamelModelTool.getRawMetric(camelModel, metricName).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CompositeMetric getCompositeMetric(String resourceName, String metricName) {
        CamelModel camelModel = getCamelModel(resourceName);
        return CamelModelTool.getCompositeMetric(camelModel, metricName).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<VariableDTO> getVariables(String resourceName) {
        CamelModel camelModel = getCamelModel(resourceName);
        return  getAllMetrics(camelModel)
                .stream()
                .filter(metricModel -> metricModel instanceof MetricVariableImpl)
                .map(metricModel -> (MetricVariableImpl) metricModel)
                .map(CamelModelMapper::variableToVariableDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<RawMetricDTO> getRawMetrics(String resourceName) {
        CamelModel camelModel = getCamelModel(resourceName);
        return getAllMetrics(camelModel)
                .stream()
                .filter(metric -> metric instanceof RawMetric)
                .map(metricModel -> (RawMetric) metricModel)
                .map(CamelModelMapper::rawMetricToRawMetricDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CompositeMetricDTO> getCompositeMetrics(String resourceName) {
        CamelModel camelModel = getCamelModel(resourceName);
        return getAllMetrics(camelModel)
                .stream()
                .filter(metric -> metric instanceof CompositeMetric)
                .map(metricModel -> (CompositeMetric) metricModel)
                .map(CamelModelMapper::compositeMetricToCompositeMetricDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<Metric> getAllMetrics(CamelModel camelModel) {
        EList<MetricModel> metricModels = camelModel.getMetricModels();
        if (CollectionUtils.isEmpty(metricModels)) {
            return Collections.emptyList();
        }

        return metricModels.stream()
                .map(metricModel -> ((MetricTypeModelImpl) metricModel).getMetrics())
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}