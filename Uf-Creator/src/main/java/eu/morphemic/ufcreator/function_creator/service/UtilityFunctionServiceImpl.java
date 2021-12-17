package eu.morphemic.ufcreator.function_creator.service;

import eu.morphemic.ufcreator.function_creator.PredefinedFunctionProvider;
import eu.morphemic.ufcreator.function_creator.function.FunctionFactory;
import eu.morphemic.ufcreator.function_creator.function.PredefinedFunction;
import eu.morphemic.ufcreator.function_creator.model.PredefinedFunctionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilityFunctionServiceImpl implements UtilityFunctionService {

    public String createUtilityFunction(CreateFunctionRequest createFunctionRequest) {
//        return PredefinedFunctionProvider.getTemplate(createFunctionRequest);
        return "";
    }
}
