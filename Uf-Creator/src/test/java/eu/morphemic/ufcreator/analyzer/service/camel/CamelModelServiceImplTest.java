//package eu.morphemic.ufcreator.analyzer.service.camel;
//
//import camel.core.Application;
//import camel.core.CamelModel;
//import camel.metric.CompositeMetric;
//import camel.metric.Metric;
//import camel.metric.MetricModel;
//import camel.metric.RawMetric;
//import camel.metric.impl.MetricVariableImpl;
//import eu.morphemic.ufcreator.analyzer.service.cdo.CdoService;
//import eu.morphemic.ufcreator.communication.cdo.CdoServerApi;
//import eu.morphemic.ufcreator.communication.cdo.CdoServerClientApi;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import eu.paasage.mddb.cdo.client.exp.CDOSessionX;
//import eu.passage.upperware.commons.model.tools.CamelModelTool;
//import org.eclipse.emf.cdo.transaction.CDOTransaction;
//import org.eclipse.emf.common.util.EList;
//import org.jclouds.rest.ResourceNotFoundException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//import org.mockito.internal.matchers.Null;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.test.context.event.annotation.BeforeTestClass;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.*;
//
//class CamelModelServiceImplTest {
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//    }
//    @InjectMocks
//    CamelModelServiceImpl camelModelService;
//    @Mock
//    CdoService cdoService;
//    @Mock
//    CDOSessionX cdoSessionX;
//    @Mock
//    CDOTransaction transaction;
//    @Mock
//    CdoServerApi cdoServerApi;
//    @Mock
//    CamelModel camelModel;
//    @Mock
//    EList<MetricModel> metricModelEList;
//
//
//
//    @Test
//    void testGetCamelModelNames() {
//        ArrayList<String> stringList = new ArrayList<>();
//        when(cdoService.getAllXmi()).thenReturn(stringList);
//        List<String> actualCamelModelNames = (new CamelModelServiceImpl(cdoServerApi, cdoService))
//                .getCamelModelNames();
//        assertSame(stringList, actualCamelModelNames);
//        assertTrue(actualCamelModelNames.isEmpty());
//        verify(cdoService).getAllXmi();
//    }
//
//    @Test
//    void testGetCamelModelResourceNotExists() {
//        String resourceName= "";
//        when(cdoServerApi.openSession()).thenReturn(cdoSessionX);
//        when(cdoServerApi.openTransaction(cdoSessionX)).thenReturn(transaction);
//
//        CamelModel actualCamelModel = (new CamelModelServiceImpl(cdoServerApi, cdoService))
//                .getCamelModel(resourceName);
//        assertSame(null, actualCamelModel);
//        verify(cdoServerApi).openSession();
//        verify(cdoServerApi).openTransaction(cdoSessionX);
//        verify(cdoServerApi).getCamelModel(resourceName,transaction);
//
//    }
//
//
//    @Test
//    void testGetVariable() {
//        String resourceName="";
//        String variableName="";
//        when(camelModelService.getCamelModel(resourceName)).thenReturn(camelModel);
//        Exception exception = assertThrows(ResourceNotFoundException.class, () -> camelModelService.getVariable(resourceName,variableName));
//        }
//
//    @Test
//    void testGetRawMetric() {
//        String resourceName="";
//        String metricName="";
//        when(camelModelService.getCamelModel(resourceName)).thenReturn(camelModel);
//        Exception exception = assertThrows(ResourceNotFoundException.class, () -> camelModelService.getRawMetric(resourceName,metricName));
//    }
//    @Test
//    void testGetCompositeMetric() {
//        String resourceName="";
//        String metricName="";
//        when(camelModelService.getCamelModel(resourceName)).thenReturn(camelModel);
//        Exception exception = assertThrows(ResourceNotFoundException.class, () -> camelModelService.getCompositeMetric(resourceName,metricName));
//    }
//
//    @Test
//    void testGetVariables() {
//        String resourceName="";
//        when(camelModelService.getCamelModel(resourceName)).thenReturn(camelModel);
//        List<MetricVariableImpl> actualMetricVariableList=camelModelService.getVariables(resourceName);
//        assertTrue(actualMetricVariableList.isEmpty());
//
//    }
//
//    @Test
//    void testGetRawMetrics() {
//        String resourceName="";
//        when(camelModelService.getCamelModel(resourceName)).thenReturn(camelModel);
//        List<RawMetric> actualRawMetricList=camelModelService.getRawMetrics(resourceName);
//        assertTrue(actualRawMetricList.isEmpty());
//
//    }
//
//    @Test
//    void testGetCompositeMetrics() {
//        String resourceName="";
//        when(camelModelService.getCamelModel(resourceName)).thenReturn(camelModel);
//        List<CompositeMetric> actualCompositeMetricList=camelModelService.getCompositeMetrics(resourceName);
//        assertTrue(actualCompositeMetricList.isEmpty());
//
//    }
//
//    @Test
//    void testGetAllMetrics() {
//        when(camelModel.getMetricModels()).thenReturn(metricModelEList);
//        List<Metric> actualMetricList=camelModelService.getAllMetrics(camelModel);
//        assertTrue(actualMetricList.isEmpty());
//
//    }
//
//
//
//
//
//

}