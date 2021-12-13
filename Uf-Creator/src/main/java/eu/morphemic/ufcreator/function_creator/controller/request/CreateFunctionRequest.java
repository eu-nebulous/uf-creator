package eu.morphemic.ufcreator.function_creator.controller.request;

import eu.morphemic.ufcreator.function_creator.function.PredefinedFunction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateFunctionRequest {
   List<PredefinedFunction> predefinedFunctions;
}
