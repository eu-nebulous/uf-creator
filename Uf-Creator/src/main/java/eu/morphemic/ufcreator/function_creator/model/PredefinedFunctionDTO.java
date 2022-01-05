package eu.morphemic.ufcreator.function_creator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PredefinedFunctionDTO {
    @JsonProperty("name")
    public String name;
    @JsonProperty("src")
    public String src;
    @JsonProperty("variableList")
    public List<VariableList> variableList;
    @JsonProperty("constantsList")
    public List<ConstantsList> constantsList;
    @JsonProperty("metricList")
    public List<MetricList> metricList;
    @JsonProperty("weight")
    public double weight;

}
