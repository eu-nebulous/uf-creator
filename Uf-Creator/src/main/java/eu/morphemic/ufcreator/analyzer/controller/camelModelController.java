package eu.morphemic.ufcreator.analyzer.controller;

import eu.morphemic.ufcreator.analyzer.model.CompositeMetricDTO;
import eu.morphemic.ufcreator.analyzer.model.RawMetricDTO;
import eu.morphemic.ufcreator.analyzer.model.VariableDTO;
import eu.morphemic.ufcreator.analyzer.service.camel.CamelModelService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.cdo.util.CommitException;
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

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllCamelModels() {
        log.info("GET request for all uploaded camel models");
        return camelModelService.getCamelModelNames();
    }

    @GetMapping("/{resourceName}/rawMetrics")
    @ResponseStatus(HttpStatus.OK)
    public List<RawMetricDTO> getCamelModel(@PathVariable(value = "resourceName") String resourceName) {
        log.info("GET request for camel model {}", resourceName);
        return camelModelService.getRawMetricsFromCDO(resourceName);
    }

    @GetMapping("/{resourceName}/compositeMetrics")
    @ResponseStatus(HttpStatus.OK)
    public List<CompositeMetricDTO> getCompositeMetrics(@PathVariable(value = "resourceName") String resourceName) {
        log.info("GET request for raw metrics for {}", resourceName);
        return camelModelService.getCompositeMetricsFromCDO(resourceName);
    }

    @GetMapping("/{resourceName}/variables")
    @ResponseStatus(HttpStatus.OK)
    public List<VariableDTO> getVariables(@PathVariable(value = "resourceName") String resourceName) {
        log.info("GET request for raw metrics for {}", resourceName);
        return camelModelService.getVariablesFromCDO(resourceName);
    }

    @PostMapping("/{resourceName}/saveUtility/{formula}")
    @ResponseStatus(HttpStatus.OK)
    public void saveUtility(@PathVariable(value = "resourceName") String resourceName, @PathVariable(value = "formula") String formula) throws CommitException {
        camelModelService.saveUtility(resourceName, formula);
    }
}
