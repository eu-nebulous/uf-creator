package eu.morphemic.ufcreator.analyzer.controller;

import camel.core.CamelModel;
import camel.metric.CompositeMetric;
import camel.metric.RawMetric;
import camel.metric.impl.MetricVariableImpl;
import eu.morphemic.ufcreator.analyzer.service.camel.CamelModelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@RestController
@RequestMapping("camel-model")
public class camelModelController {

    private CamelModelService camelModelService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllCamelModels(){
        log.info("GET request for all uploaded camel models");
        return camelModelService.getCamelModelNames();
    }
    @GetMapping("/{resourceName}")
    @ResponseStatus(HttpStatus.OK)
    public CamelModel getCamelModel(@PathVariable(value = "resourceName") String resourceName) {
        log.info("GET request for camel model {}", resourceName);
        return camelModelService.getCamelModel(resourceName);
    }

    @GetMapping("/{resourceName}/rawMetrics")
    @ResponseStatus(HttpStatus.OK)
    public List<RawMetric> getRawMetrics(@PathVariable(value = "resourceName") String resourceName) {
        log.info("GET request for raw metrics for {}", resourceName);
        return camelModelService.getRawMetrics(resourceName);
    }

    @GetMapping("/{resourceName}/compositeMetrics")
    @ResponseStatus(HttpStatus.OK)
    public List<CompositeMetric> getCompositeMetrics(@PathVariable(value = "resourceName") String resourceName) {
        log.info("GET request for raw metrics for {}", resourceName);
        return camelModelService.getCompositeMetrics(resourceName);
    }

    @GetMapping("/{resourceName}/variables")
    @ResponseStatus(HttpStatus.OK)
    public List<MetricVariableImpl> getVariables(@PathVariable(value = "resourceName") String resourceName) {
        log.info("GET request for raw metrics for {}", resourceName);
        return camelModelService.getVariables(resourceName);
    }
}
