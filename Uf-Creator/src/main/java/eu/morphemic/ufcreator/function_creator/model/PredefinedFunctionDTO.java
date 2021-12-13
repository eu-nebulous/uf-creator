package eu.morphemic.ufcreator.function_creator.model;

import java.util.List;

public class PredefinedFunctionDTO {
    public String name;
    public String src;
    public List<VariableList> variableList;
    public List<ConstantsList> constantsList;
    public List<RawMetricList> rawMetricList;
    public List<CompositeMetricList> compositeMetricList;
}
