package eu.morphemic.ufcreator.function_creator.service;

import eu.morphemic.ufcreator.function_creator.controller.request.CreateFunctionRequest;

import java.util.List;

public interface UtilityFunctionService {

     List<String> getAvailableFunctions(List<String> variableDtoList);

//     void createUtilityFunction(CreateFunctionRequest createFunctionRequest);
}
