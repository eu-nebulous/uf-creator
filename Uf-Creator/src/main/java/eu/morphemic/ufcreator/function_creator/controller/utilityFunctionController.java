package eu.morphemic.ufcreator.function_creator.controller;

import eu.morphemic.ufcreator.function_creator.controller.request.CreateFunctionRequest;
import eu.morphemic.ufcreator.function_creator.service.UtilityFunctionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RequestMapping("utility-function")
public class utilityFunctionController {

    UtilityFunctionService utilityFunctionService;

    @GetMapping("/availableFunctions")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllAvailableFunctions(@RequestParam("variableDto") List<String> listOfVariables){
        log.info("GET request for all available predefined functions");
        return utilityFunctionService.getAvailableFunctions(listOfVariables);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<String> createUtilityFunction(@RequestBody CreateFunctionRequest createFunctionRequest){
        return utilityFunctionService.createUtilityFunction(createFunctionRequest);
    }

}
