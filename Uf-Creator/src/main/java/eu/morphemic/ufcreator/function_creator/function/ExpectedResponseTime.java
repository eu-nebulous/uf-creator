package eu.morphemic.ufcreator.function_creator.function;

import eu.morphemic.ufcreator.function_creator.model.CompositeMetricList;
import eu.morphemic.ufcreator.function_creator.model.ConstantsList;
import eu.morphemic.ufcreator.function_creator.model.RawMetricList;
import eu.morphemic.ufcreator.function_creator.model.VariableList;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter

public class ExpectedResponseTime extends PredefinedFunction {


    public ExpectedResponseTime(String functionName, List<VariableList> variableList, List<ConstantsList> constantsList, List<RawMetricList> rawMetricList, List<CompositeMetricList> compositeMetricList) {
        super(functionName, variableList, constantsList, rawMetricList, compositeMetricList);
    }
}
