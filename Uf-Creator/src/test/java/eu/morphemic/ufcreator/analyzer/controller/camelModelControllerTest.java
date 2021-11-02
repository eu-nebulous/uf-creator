package eu.morphemic.ufcreator.analyzer.controller;

import camel.core.CamelModel;
import camel.core.impl.CamelModelImpl;
import camel.metric.CompositeMetric;
import camel.metric.RawMetric;
import camel.metric.impl.MetricVariableImpl;
import camel.metric.impl.RawMetricImpl;
import eu.morphemic.ufcreator.analyzer.service.camel.CamelModelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class camelModelControllerTest {
    @Autowired
    private camelModelController camelModelController;

    @MockBean
    private CamelModelService camelModelService;

    @Autowired
    private MockMvc mvc;
    @MockBean
    private CamelModelService service;

    @Test
    void testGetRawMetrics() throws Exception {
        when(this.camelModelService.getRawMetrics((String) any())).thenReturn(new ArrayList<RawMetric>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/camel-model/{resourceName}/rawMetrics",
                "Resource Name");
        MockMvcBuilders.standaloneSetup(this.camelModelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetRawMetrics2() throws Exception {
        when(this.camelModelService.getRawMetrics((String) any())).thenReturn(new ArrayList<RawMetric>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/camel-model/{resourceName}/rawMetrics",
                "Resource Name");
        MockMvcBuilders.standaloneSetup(this.camelModelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetCompositeMetrics() throws Exception {
        when(this.camelModelService.getCompositeMetrics((String) any())).thenReturn(new ArrayList<CompositeMetric>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/camel-model/{resourceName}/compositeMetrics", "Resource Name");
        MockMvcBuilders.standaloneSetup(this.camelModelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetVariables() throws Exception {
        when(this.camelModelService.getVariables((String) any())).thenReturn(new ArrayList<MetricVariableImpl>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/camel-model/{resourceName}/variables",
                "Resource Name");
        MockMvcBuilders.standaloneSetup(this.camelModelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }




    @Test
    void testGetAllCamelModels() throws Exception {
        when(this.camelModelService.getCamelModelNames()).thenReturn(new ArrayList<String>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/camel-model/");
        MockMvcBuilders.standaloneSetup(this.camelModelController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


}