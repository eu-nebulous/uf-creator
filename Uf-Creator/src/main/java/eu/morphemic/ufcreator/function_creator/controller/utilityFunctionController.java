package eu.morphemic.ufcreator.function_creator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.morphemic.ufcreator.function_creator.controller.request.CreateFunctionRequest;
import eu.morphemic.ufcreator.function_creator.function.PredefinedFunction;
import eu.morphemic.ufcreator.function_creator.model.PredefinedFunctionDTO;
import eu.morphemic.ufcreator.function_creator.service.UtilityFunctionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
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
        log.info(createFunctionRequest);
      List<PredefinedFunctionDTO> predefinedFunctions = Arrays.asList(om.readValue(createFunctionRequest,PredefinedFunctionDTO[].class));
      log.info("{}",predefinedFunctions);
      return "";
    }
}
