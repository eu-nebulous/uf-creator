package eu.morphemic.ufcreator.function_creator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ByTemplateFunctionDTO {
    @JsonProperty("metricName")
    public String metricName;
    @JsonProperty("a")
    public double a;
    @JsonProperty("b")
    public double b;
    @JsonProperty("weight")
    public double weight;
    @JsonProperty("shape")
    public String shape;
}
