package eu.morphemic.ufcreator.function_creator;

import eu.morphemic.ufcreator.function_creator.model.ByTemplateFunctionDTO;
import eu.morphemic.ufcreator.function_creator.model.PredefinedFunctionDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static eu.morphemic.ufcreator.function_creator.FormulaProvider.*;

@AllArgsConstructor
@Slf4j
public class PredefinedFunctionProvider {

    public static String getFunctionFormulaByPredefined(List<PredefinedFunctionDTO> predefinedFunctions) {
        List<String> utilityFunction = new ArrayList<>();
        for (PredefinedFunctionDTO predefinedFunction : predefinedFunctions) {
            log.info("{}", predefinedFunction);
            String formula = getPredefinedFunctionFormula(predefinedFunction);
            utilityFunction.add(multiply(formula, String.valueOf(predefinedFunction.weight)));
        }
        return getSum(utilityFunction);
    }

    public static String getFunctionFormulaByTemplate(List<ByTemplateFunctionDTO> byTemplateFunctionDTOList) {
        List<String> utilityFunction = new ArrayList<>();
        for (ByTemplateFunctionDTO byTemplateFunctionDTO : byTemplateFunctionDTOList) {
            log.info("{}", byTemplateFunctionDTO);
            String formula = getByTemplateFunctionFormula(byTemplateFunctionDTO);
            utilityFunction.add(multiply(formula, String.valueOf(byTemplateFunctionDTO.weight)));
        }
        return getSum(utilityFunction);
    }

    private static String getByTemplateFunctionFormula(ByTemplateFunctionDTO byTemplateFunctionDTO) {
        switch (byTemplateFunctionDTO.getShape()) {
            case "S-Shaped":
                return FormulaProvider.getSShaped(byTemplateFunctionDTO.getMetricName(),String.valueOf(byTemplateFunctionDTO.getA()),String.valueOf(byTemplateFunctionDTO.getB()));
            case "U-Shaped":
                return FormulaProvider.getUShaped(byTemplateFunctionDTO.getMetricName(),String.valueOf(byTemplateFunctionDTO.getA()),String.valueOf(byTemplateFunctionDTO.getB()));
            case "Reverse S-Shaped":
                return FormulaProvider.getReversedSShaped(byTemplateFunctionDTO.getMetricName(),String.valueOf(byTemplateFunctionDTO.getA()),String.valueOf(byTemplateFunctionDTO.getB()));
            case "Reverse U-Shaped":
                return FormulaProvider.getReversedUShaped(byTemplateFunctionDTO.getMetricName(),String.valueOf(byTemplateFunctionDTO.getA()),String.valueOf(byTemplateFunctionDTO.getB()));
            case "Constant Shaped":
                return FormulaProvider.getConstantShaped(byTemplateFunctionDTO.getMetricName(),String.valueOf(byTemplateFunctionDTO.getA()),String.valueOf(byTemplateFunctionDTO.getB()));
        }
        throw new RuntimeException("function type with shape:" + byTemplateFunctionDTO.getShape() + " is not supported yet");
    }

    private static String getPredefinedFunctionFormula(PredefinedFunctionDTO predefinedFunction) {
        switch (predefinedFunction.getName()) {
            case "ExpectedResponseTime":
                return "2";
            case "FinishOnTime":
                return FormulaProvider.getFinishOnTime(
                        predefinedFunction.getConstantsList().get(0).value,
                        predefinedFunction.getConstantsList().get(1).value,
                        predefinedFunction.getMetricList().get(0).value,
                        predefinedFunction.getMetricList().get(1).value,
                        predefinedFunction.getMetricList().get(2).value,
                        predefinedFunction.getVariableList().get(0).value,
                        predefinedFunction.getVariableList().get(1).value
                        );
            case "LocalityUtility":
                return FormulaProvider.getLocalityUtility(
                        predefinedFunction.getConstantsList().get(0).value,
                        predefinedFunction.getConstantsList().get(1).value,
                        predefinedFunction.getConstantsList().get(2).value,
                        predefinedFunction.getConstantsList().get(3).value
                        );
            case "RamUsage":

            case "CpuUsage":

            case "CoreCostUtility":

            case "CostPerUser":
        }
        throw new RuntimeException("function type " + predefinedFunction.getName() + " is not supported yet");
    }

}
