package eu.morphemic.ufcreator.analyzer.service.camel;

import camel.core.CamelModel;
import camel.metric.CompositeMetric;
import camel.metric.Metric;
import camel.metric.MetricModel;
import camel.metric.RawMetric;
import camel.metric.impl.MetricTypeModelImpl;
import camel.metric.impl.MetricVariableImpl;
import com.google.errorprone.annotations.Var;
import eu.morphemic.ufcreator.analyzer.model.CompositeMetricDTO;
import eu.morphemic.ufcreator.analyzer.model.RawMetricDTO;
import eu.morphemic.ufcreator.analyzer.model.VariableDTO;
import eu.morphemic.ufcreator.analyzer.service.cdo.CdoService;
import eu.morphemic.ufcreator.analyzer.service.mapper.CamelModelMapper;
import eu.morphemic.ufcreator.communication.cdo.CdoServerApi;
import eu.paasage.mddb.cdo.client.exp.CDOSessionX;
import eu.passage.upperware.commons.model.tools.CamelModelTool;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CamelModelServiceImpl implements CamelModelService {

    private CdoServerApi cdoServerApi;
    private CdoService cdoService;

    public List<String> getCamelModelNames() {
        List<String> allXmiModels = cdoService.getAllXmi();
        allXmiModels.sort(String::compareTo);
        return allXmiModels;
    }


    @Override
    public MetricVariableImpl getVariable(CamelModel camelModel, String variableName) {
        return CamelModelTool.getVariable(camelModel, variableName).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public RawMetric getRawMetric(CamelModel camelModel, String metricName) {
        return CamelModelTool.getRawMetric(camelModel, metricName).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public CompositeMetric getCompositeMetric(CamelModel camelModel, String metricName) {
        return CamelModelTool.getCompositeMetric(camelModel, metricName).orElseThrow(ResourceNotFoundException::new);

    }

    @Override
    public List<VariableDTO> getVariables(CamelModel camelModel) {

        return getAllMetrics(camelModel)
                .stream()
                .filter(metricModel -> metricModel instanceof MetricVariableImpl)
                .map(metricModel -> (MetricVariableImpl) metricModel)
                .map(CamelModelMapper::variableToVariableDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<RawMetricDTO> getRawMetrics(CamelModel camelModel) {
        return getAllMetrics(camelModel)
                .stream()
                .filter(metric -> metric instanceof RawMetric)
                .map(metricModel -> (RawMetric) metricModel)
                .map(CamelModelMapper::rawMetricToRawMetricDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CompositeMetricDTO> getCompositeMetrics(CamelModel camelModel) {
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


    public List<CompositeMetricDTO> getCompositeMetricsFromCDO(String resourceName) {
        CDOSessionX cdoSessionX = cdoServerApi.openSession();
        CDOTransaction cdoTransaction = cdoServerApi.openTransaction(cdoSessionX);
        log.info("Loading camel model {}", resourceName);
        CamelModel camelModel;
        camelModel = cdoService.getCamelModel(resourceName, cdoTransaction);
        List<CompositeMetricDTO> CompositeMetricDTOs = getCompositeMetrics(camelModel);
        cdoSessionX.closeTransaction(cdoTransaction);
        cdoSessionX.closeSession();
        return CompositeMetricDTOs;
    }

    public List<RawMetricDTO> getRawMetricsFromCDO(String resourceName) {
        CDOSessionX cdoSessionX = cdoServerApi.openSession();
        CDOTransaction cdoTransaction = cdoServerApi.openTransaction(cdoSessionX);
        log.info("Loading camel model {}", resourceName);
        CamelModel camelModel;
        camelModel = cdoService.getCamelModel(resourceName, cdoTransaction);
        List<RawMetricDTO> rawMetricDTOs = getRawMetrics(camelModel);
        cdoSessionX.closeTransaction(cdoTransaction);
        cdoSessionX.closeSession();
        return rawMetricDTOs;
    }

    public List<VariableDTO> getVariablesFromCDO(String resourceName) {
        CDOSessionX cdoSessionX = cdoServerApi.openSession();
        CDOTransaction cdoTransaction = cdoServerApi.openTransaction(cdoSessionX);
        log.info("Loading camel model {}", resourceName);
        CamelModel camelModel;
        camelModel = cdoService.getCamelModel(resourceName, cdoTransaction);
        List<VariableDTO> VariableDTOs = getVariables(camelModel);
        cdoSessionX.closeTransaction(cdoTransaction);
        cdoSessionX.closeSession();
        return VariableDTOs;
    }
}
