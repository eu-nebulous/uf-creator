package eu.morphemic.ufcreator.function_creator;

import eu.melodic.upperware.utilitygenerator.cdo.cp_model.DTO.VariableDTO;
import eu.morphemic.ufcreator.function_creator.function.PredefinedFunction;
import eu.morphemic.ufcreator.function_creator.model.ByTemplateFunctionDTO;
import eu.paasage.upperware.metamodel.cp.VariableType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static eu.morphemic.ufcreator.function_creator.FormulaProvider.*;

@AllArgsConstructor
@Slf4j
public class PredefinedFunctionProvider {

    public static String getTemplate(ArrayList<PredefinedFunction> predefinedFunctions) {
        List<String> utilityFunction = new ArrayList<>();
        for (PredefinedFunction predefinedfunction : predefinedFunctions) {
            log.info("{}", predefinedfunction);
            String formula = getPredefinedFunctionFormula(predefinedfunction);
            utilityFunction.add(multiply(formula, String.valueOf(predefinedfunction.weight)));
        }
        return getSum(utilityFunction);
    }

    public static String getTemplate(List<ByTemplateFunctionDTO> byTemplateFunctionDTOList) {
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
                return "1-" + FormulaProvider.add("1", "exponentialValue^(" + "prod(" + FormulaProvider.minus(byTemplateFunctionDTO.getMetricName(), String.valueOf(byTemplateFunctionDTO.getB())) + "(lnValue(" + "(div(" + minus("1", "exponentialValue") + ",exponentialValue))" + FormulaProvider.minus(String.valueOf(byTemplateFunctionDTO.getA()), String.valueOf(byTemplateFunctionDTO.getB()))) + ")^-1";
            case "U-Shaped":
                return "1-exponentialValue^prod(-1,div(" + byTemplateFunctionDTO.getMetricName() + " + " + byTemplateFunctionDTO.getA() + ")," + byTemplateFunctionDTO.getB() + ")";
            case "Reverse S-Shaped":
                return FormulaProvider.add("1", "exponentialValue^(" + "prod(" + FormulaProvider.minus(byTemplateFunctionDTO.getMetricName(), String.valueOf(byTemplateFunctionDTO.getB())) + "(lnValue(" + "(div(exponentialValue," + minus("1", "exponentialValue") + "))" + FormulaProvider.minus(String.valueOf(byTemplateFunctionDTO.getA()), String.valueOf(byTemplateFunctionDTO.getB()))) + ")^-1";
            case "Reverse U-Shaped":
                return "exponentialValue^prod(lnValue(exponentialValue),(" + byTemplateFunctionDTO.getMetricName() + "," + byTemplateFunctionDTO.getA() + "^2/(" + byTemplateFunctionDTO.getB() + byTemplateFunctionDTO.getA();
            case "Linear":
                return "5";
            case "Constant Shaped":
                return "6";
        }
        throw new RuntimeException("function type with shape:" + byTemplateFunctionDTO.getShape() + " is not supported yet");
    }

    private static String getPredefinedFunctionFormula(PredefinedFunction predefinedFunction) {
        switch (predefinedFunction.getName()) {
            case "ExpectedResponseTime":
                return "2";
            case "FinishSimulationOnTime":
                return "1";
            case "LocalityUtility":
                return "1/(1 + acos(prod(sin(prod(" + predefinedFunction.getConstantsList().get(0).value + ",(π/180))), (sin(prod(" + predefinedFunction.getConstantsList().get(2).value + ",(π/180))) + (prod(cos(prod(" + predefinedFunction.getConstantsList().get(0).value + ",(π/180))),(cos(prod(" + predefinedFunction.getConstantsList().get(1).value + ",(π/180))))," +
                        "cos(" + predefinedFunction.getConstantsList().get(3).value + "π/180 -" + predefinedFunction.getConstantsList().get(1).value + "π/180)) ⋅ 6371000)";
            case "RamUsage":

            case "CpuUsage":

            case "CoreCostUtility":

            case "CostPerUser":
        }
        throw new RuntimeException("function type " + predefinedFunction.getName() + " is not supported yet");
    }

    private static String getOnlyCostUtility(Collection<VariableDTO> variablesFromConstraintProblem) {
        return inverse(getCostFormula(variablesFromConstraintProblem));
    }

    private static String getDistance(Collection<VariableDTO> variablesFromConstraintProblem) {
        Collection<String> distances = getDistancesBetweenConsecutiveComponents(variablesFromConstraintProblem);
        distances = distances.stream().map(FormulaProvider::invExp).collect(Collectors.toList());
        return multiply(getComponentsCount(variablesFromConstraintProblem).toString(), String.join("+", distances));
    }

    private static String getMinMaxPenalty(Collection<VariableDTO> variablesFromConstraintProblem, VariableType
            type) {
        String maxOfType = getMax(getVariablesOfGivenType(type, variablesFromConstraintProblem));
        String minOfType = getMin(getVariablesOfGivenType(type, variablesFromConstraintProblem));
        return normalizedMinusArcTangens(minus(maxOfType, minOfType));
    }
}
