package eu.morphemic.ufcreator.function_creator.service;

import eu.morphemic.ufcreator.function_creator.PredefinedFunctionProvider;
import eu.morphemic.ufcreator.function_creator.model.ByTemplateFunctionDTO;
import eu.morphemic.ufcreator.function_creator.model.PredefinedFunctionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class UtilityFunctionServiceImpl implements UtilityFunctionService {
    public String createUtilityFunctionPredefined(List<PredefinedFunctionDTO> predefinedFunctionDTOList) {
        log.info("{}", predefinedFunctionDTOList);
        return PredefinedFunctionProvider.getFunctionFormulaByPredefined(predefinedFunctionDTOList);
    }

    public String createUtilityFunctionByTemplate(List<ByTemplateFunctionDTO> byTemplateFunctionDTOList) {

        log.info("{}", byTemplateFunctionDTOList);
        return PredefinedFunctionProvider.getFunctionFormulaByTemplate(byTemplateFunctionDTOList);
    }
}
