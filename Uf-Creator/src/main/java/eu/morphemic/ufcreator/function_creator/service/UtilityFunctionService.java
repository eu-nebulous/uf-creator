package eu.morphemic.ufcreator.function_creator.service;

import eu.morphemic.ufcreator.function_creator.model.PredefinedFunctionDTO;

import java.util.List;

public interface UtilityFunctionService {

    String createUtilityFunction(List<PredefinedFunctionDTO> predefinedFunctionDTOList);
}
