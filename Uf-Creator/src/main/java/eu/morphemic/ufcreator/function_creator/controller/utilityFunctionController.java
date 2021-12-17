package eu.morphemic.ufcreator.function_creator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @PostMapping(value = "/function")
    @ResponseStatus(HttpStatus.OK)

    public String createUtilityFunction(@RequestBody String createFunctionRequest) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        log.info("dev log createFunctionRequest: {}", createFunctionRequest);
        List<PredefinedFunctionDTO> predefinedFunctions = Arrays.asList(om.readValue(createFunctionRequest, PredefinedFunctionDTO[].class));
        log.info("dev log predefinedFunction dto: {}", predefinedFunctions);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("utilityFunction", utilityFunctionService.createUtilityFunction(predefinedFunctions));
        return jsonObject.toString();
    }
}
