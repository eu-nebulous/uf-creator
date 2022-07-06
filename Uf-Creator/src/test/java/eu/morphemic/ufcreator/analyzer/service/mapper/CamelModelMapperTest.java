package eu.morphemic.ufcreator.analyzer.service.mapper;

import camel.deployment.Component;
import camel.metric.CompositeMetric;
import camel.metric.MetricVariable;
import camel.metric.impl.MetricImpl;
import camel.metric.impl.MetricVariableImpl;
import eu.morphemic.ufcreator.analyzer.model.CompositeMetricDTO;
import eu.morphemic.ufcreator.analyzer.model.VariableDTO;
import eu.morphemic.ufcreator.analyzer.service.camel.CamelModelServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CamelModelMapperTest {
    @Mock
    CompositeMetric mockCompositeMetric;

    @Mock
    MetricVariableImpl mockMetricVariable;


    @Test
    public void shouldMapCompositeMetricToCompositeMetricDTO() {
        //given
        when(mockCompositeMetric.getName()).thenReturn("testName");
        when(mockCompositeMetric.getFormula()).thenReturn("x^2");

        //when
        CompositeMetricDTO result = CamelModelMapper.compositeMetricToCompositeMetricDTO(mockCompositeMetric);

        //then
        assertThat(result).isInstanceOf(CompositeMetricDTO.class);
        assertThat(result.getName()).isEqualTo("testName");
        assertThat(result.getFormula()).isEqualTo("x^2");
    }

    @Test
    public void shouldMapMetricVariableToVariableDTO() {
        //given
        try (MockedStatic<CamelModelServiceImpl> utilities = Mockito.mockStatic(CamelModelServiceImpl.class)) {
            utilities.when(() -> CamelModelServiceImpl.getAnnotationOfMetricVariable(mockMetricVariable))
                    .thenReturn("testType");

            when(mockMetricVariable.getFormula()).thenReturn("x^2");
            when(mockMetricVariable.getName()).thenReturn("testName");
            when(mockMetricVariable.isCurrentConfiguration()).thenReturn(true);

            //when
            VariableDTO result = CamelModelMapper.variableToVariableDTO(mockMetricVariable);

            //then
            assertThat(result.getName()).isEqualTo("testName");
            assertThat(result.getFormula()).isEqualTo("x^2");
            assertThat(result.getType()).isEqualTo("testType");
            assertThat(result.getIsCurrentConfiguration()).isEqualTo(true);
        }
    }
}
