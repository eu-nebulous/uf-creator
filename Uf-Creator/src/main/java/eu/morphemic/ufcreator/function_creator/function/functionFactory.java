package eu.morphemic.ufcreator.function_creator.function;

public class functionFactory {

    public static PredefinedFunction createFunctionObj(PredefinedFunction function) {

        switch (function.getName()) {
            case "CoreCostUtility":
                return new CoreCostUtility(function.getName(), function.getVariableList(), function.getConstantsList(), function.getRawMetricList(), function.getCompositeMetricList());
            case "CostPerUser":
                return new CostPerUser(function.getName(), function.getVariableList(), function.getConstantsList(), function.getRawMetricList(), function.getCompositeMetricList());
            case "CpuUsage":
                return new CpuUsage(function.getName(), function.getVariableList(), function.getConstantsList(), function.getRawMetricList(), function.getCompositeMetricList());
            case "ExpectedResponseTime":
                return new ExpectedResponseTime(function.getName(), function.getVariableList(), function.getConstantsList(), function.getRawMetricList(), function.getCompositeMetricList());
            case "LocalityUtility":
                return new LocalityUtility(function.getName(), function.getVariableList(), function.getConstantsList(), function.getRawMetricList(), function.getCompositeMetricList());
            case "RamUsage":
                return new RamUsage(function.getName(), function.getVariableList(), function.getConstantsList(), function.getRawMetricList(), function.getCompositeMetricList());
            default:
                return null;
        }
    }
}
