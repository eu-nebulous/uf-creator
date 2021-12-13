package eu.morphemic.ufcreator.function_creator;

import eu.melodic.upperware.utilitygenerator.cdo.cp_model.DTO.VariableDTO;
import eu.paasage.upperware.metamodel.cp.VariableType;
import lombok.AllArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static eu.morphemic.ufcreator.function_creator.FormulaProvider.*;

@AllArgsConstructor
public class PredefinedFunctionProvider {

    public static String getTemplate(Collection<VariableDTO> variablesFromCamelModel, List<Map.Entry<PredefinedUtilityFunctions, Double>> dimensions) {
        return getSum(dimensions.stream().map((template) ->
                multiply(template.getValue().toString(), getPredefinedFunctionFormula(variablesFromCamelModel, template.getKey()))
        ).collect(Collectors.toList()));
    }

    private static String getPredefinedFunctionFormula(Collection<VariableDTO> variablesFromCamelModel, PredefinedUtilityFunctions type) {
        switch (type) {
            case ExpectedResponseTime:

            case FinishSimulationOnTime:

            case LocalityUtility:

            case RamUsage:

            case CpuUsage:

            case CoreCostUtility:

            case CostPerUser:
        }
        throw new RuntimeException("function type " + type.name() + " is not supported yet");
    }

    private static VariableType templateToVariableType(PredefinedUtilityFunctions type) {
        switch (type) {
            // case ExpectedResponseTime:
            // case FinishSimulationOnTime:
            case LocalityUtility:
                return VariableType.LOCATION;
            case RamUsage:
                return VariableType.RAM;
            case CpuUsage:
                return VariableType.CPU;
            case CoreCostUtility:
                // case CostPerUser:
        }
        throw new RuntimeException("Can't covert template " + type.name() + " to variable type");
    }

    private static String getOnlyCostUtility(Collection<VariableDTO> variablesFromConstraintProblem) {
        return inverse(getCostFormula(variablesFromConstraintProblem));
    }

    private static String getDistance(Collection<VariableDTO> variablesFromConstraintProblem) {
        Collection<String> distances = getDistancesBetweenConsecutiveComponents(variablesFromConstraintProblem);
        distances = distances.stream().map(FormulaProvider::invExp).collect(Collectors.toList());
        return multiply(getComponentsCount(variablesFromConstraintProblem).toString(), String.join("+", distances));
    }

    private static String getMinMaxPenalty(Collection<VariableDTO> variablesFromConstraintProblem, VariableType type) {
        String maxOfType = getMax(getVariablesOfGivenType(type, variablesFromConstraintProblem));
        String minOfType = getMin(getVariablesOfGivenType(type, variablesFromConstraintProblem));
        return normalizedMinusArcTangens(minus(maxOfType, minOfType));
    }
}
