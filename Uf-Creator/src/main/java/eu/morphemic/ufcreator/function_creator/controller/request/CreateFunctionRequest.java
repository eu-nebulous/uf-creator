package eu.morphemic.ufcreator.function_creator.controller.request;

import eu.morphemic.ufcreator.analyzer.model.VariableDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFunctionRequest {
    String predefinedFunction;
    Map<String, Double> dimensions;
    List<VariableDTO> variableDTOList;
}
