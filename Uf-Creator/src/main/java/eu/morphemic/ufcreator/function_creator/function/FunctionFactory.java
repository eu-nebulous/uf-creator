package eu.morphemic.ufcreator.function_creator.function;

import eu.morphemic.ufcreator.function_creator.model.PredefinedFunctionDTO;

public class FunctionFactory {

    public static PredefinedFunction createFunctionObj(PredefinedFunction function) {

        switch (function.getName()) {
            case "CoreCostUtility":
                return new CoreCostUtility(function.getName(), function.getVariableList(), function.getConstantsList(), function.getRawMetricList(), function.getCompositeMetricList(), function.getWeight());
            case "CostPerUser":
                return new CostPerUser(function.getName(), function.getVariableList(), function.getConstantsList(), function.getRawMetricList(), function.getCompositeMetricList(), function.getWeight());
            case "CpuUsage":
                return new CpuUsage(function.getName(), function.getVariableList(), function.getConstantsList(), function.getRawMetricList(), function.getCompositeMetricList(), function.getWeight());
            case "ExpectedResponseTime":
                return new ExpectedResponseTime(function.getName(), function.getVariableList(), function.getConstantsList(), function.getRawMetricList(), function.getCompositeMetricList(), function.getWeight());
            case "LocalityUtility":
                return new LocalityUtility(function.getName(), function.getVariableList(), function.getConstantsList(), function.getRawMetricList(), function.getCompositeMetricList(), function.getWeight());
            case "RamUsage":
                return new RamUsage(function.getName(), function.getVariableList(), function.getConstantsList(), function.getRawMetricList(), function.getCompositeMetricList(), function.getWeight());
            case "FinishSimulationOnTime":
                return new FinishSimulationOnTime(function.getName(), function.getVariableList(), function.getConstantsList(), function.getRawMetricList(), function.getCompositeMetricList(), function.getWeight());
            default:
                return null;
        }
    }
}
