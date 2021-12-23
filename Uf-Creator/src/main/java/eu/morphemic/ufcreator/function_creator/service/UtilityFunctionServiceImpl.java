package eu.morphemic.ufcreator.function_creator.service;

import eu.morphemic.ufcreator.function_creator.PredefinedFunctionProvider;
import eu.morphemic.ufcreator.function_creator.function.FunctionFactory;
import eu.morphemic.ufcreator.function_creator.function.PredefinedFunction;
import eu.morphemic.ufcreator.function_creator.model.ByTemplateFunctionDTO;
import eu.morphemic.ufcreator.function_creator.model.PredefinedFunctionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilityFunctionServiceImpl implements UtilityFunctionService {

    public List<PredefinedFunction> createPredefinedFunctionList(List<PredefinedFunctionDTO> predefinedFunctionsDTOList) {
        return predefinedFunctionsDTOList
                .stream()
                .map(FunctionFactory::createFunctionObj)
                .collect(Collectors.toList());
    }

    public String createUtilityFunctionPredefined(List<PredefinedFunctionDTO> predefinedFunctionDTOList) {
        List<PredefinedFunction> predefinedFunctions = createPredefinedFunctionList(predefinedFunctionDTOList);
        log.info("{}", predefinedFunctions);
        return PredefinedFunctionProvider.getTemplate((ArrayList<PredefinedFunction>) predefinedFunctions);
    }

    public String createUtilityFunctionByTemplate(List<ByTemplateFunctionDTO> byTemplateFunctionDTOList) {

        log.info("{}", byTemplateFunctionDTOList);
        return PredefinedFunctionProvider.getTemplate(byTemplateFunctionDTOList);
    }
}
