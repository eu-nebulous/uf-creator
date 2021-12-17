package eu.morphemic.ufcreator.function_creator.function;

import eu.morphemic.ufcreator.function_creator.model.CompositeMetricList;
import eu.morphemic.ufcreator.function_creator.model.ConstantsList;
import eu.morphemic.ufcreator.function_creator.model.RawMetricList;
import eu.morphemic.ufcreator.function_creator.model.VariableList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

public abstract class PredefinedFunction {
    public String name;
    public List<VariableList> variableList;
    public List<ConstantsList> constantsList;
    public List<RawMetricList> rawMetricList;
    public List<CompositeMetricList> compositeMetricList;
    public double weight;

}
