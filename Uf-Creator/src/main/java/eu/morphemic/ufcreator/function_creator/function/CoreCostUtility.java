package eu.morphemic.ufcreator.function_creator.function;

import eu.morphemic.ufcreator.function_creator.model.CompositeMetricList;
import eu.morphemic.ufcreator.function_creator.model.ConstantsList;
import eu.morphemic.ufcreator.function_creator.model.RawMetricList;
import eu.morphemic.ufcreator.function_creator.model.VariableList;

import java.util.List;

public class CoreCostUtility extends PredefinedFunction {


    public CoreCostUtility(String name, List<VariableList> variableList, List<ConstantsList> constantsList, List<RawMetricList> rawMetricList, List<CompositeMetricList> compositeMetricList, double weight) {
        super(name, variableList, constantsList, rawMetricList, compositeMetricList, weight);
    }
}
