package eu.morphemic.ufcreator.function_creator;

import eu.melodic.upperware.utilitygenerator.cdo.cp_model.DTO.VariableDTO;
import eu.morphemic.ufcreator.function_creator.function.PredefinedFunction;
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

    public static String getTemplate(List<PredefinedFunction> predefinedFunctions) {
        List<String> utilityFunction = new ArrayList<>();
        for (PredefinedFunction predefinedfunction : predefinedFunctions) {
            log.info("{}", predefinedfunction);
            String formula = getPredefinedFunctionFormula(predefinedfunction);
            utilityFunction.add(multiply(formula, String.valueOf(predefinedfunction.weight)));
        }
        return getSum(utilityFunction);
    }

    private static String getPredefinedFunctionFormula(PredefinedFunction predefinedFunction) {
        switch (predefinedFunction.getName()) {
            case "ExpectedResponseTime":
                return "2";
            case "FinishSimulationOnTime":
                return "1";
            case "LocalityUtility":

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
