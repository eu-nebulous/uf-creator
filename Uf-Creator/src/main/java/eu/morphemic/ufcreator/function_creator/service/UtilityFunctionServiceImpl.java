package eu.morphemic.ufcreator.function_creator.service;

import eu.melodic.upperware.utilitygenerator.utility_function.utility_templates_provider.TemplateProvider;
import eu.morphemic.ufcreator.function_creator.PredefinedFunctionProvider;
import eu.morphemic.ufcreator.function_creator.controller.request.CreateFunctionRequest;
import eu.morphemic.ufcreator.function_creator.function.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UtilityFunctionServiceImpl implements UtilityFunctionService{

    public String createUtilityFunction(CreateFunctionRequest createFunctionRequest) {
//        return PredefinedFunctionProvider.getTemplate(createFunctionRequest);
        return "";
    }
}
