package eu.morphemic.ufcreator.function_creator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.morphemic.ufcreator.function_creator.model.ByTemplateFunctionDTO;
import eu.morphemic.ufcreator.function_creator.model.PredefinedFunctionDTO;
import eu.morphemic.ufcreator.function_creator.service.UtilityFunctionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("utility-function")
public class utilityFunctionController {

    UtilityFunctionService utilityFunctionService;

    @PostMapping(value = "/predefined")
    @ResponseStatus(HttpStatus.OK)

    public String createUtilityFunctionPredefined(@RequestBody String createFunctionRequest) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        log.info("dev log createFunctionRequest: {}", createFunctionRequest);
        List<PredefinedFunctionDTO> predefinedFunctions = Arrays.asList(om.readValue(createFunctionRequest, PredefinedFunctionDTO[].class));
        log.info("dev log predefinedFunction dto: {}", predefinedFunctions);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("utilityFunction", utilityFunctionService.createUtilityFunctionPredefined(predefinedFunctions));
        return jsonObject.toString();
    }

    @PostMapping(value = "/byTemplate")
    @ResponseStatus(HttpStatus.OK)
    public String createUtilityFunctionByTemplate(@RequestBody String createFunctionRequest) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        log.info("dev log createFunctionRequest: {}", createFunctionRequest);
        List<ByTemplateFunctionDTO> byTemplateFunctions = Arrays.asList(om.readValue(createFunctionRequest, ByTemplateFunctionDTO[].class));
        log.info("dev log predefinedFunction dto: {}", byTemplateFunctions);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("utilityFunction", utilityFunctionService.createUtilityFunctionByTemplate(byTemplateFunctions));
        return jsonObject.toString();
    }
}
