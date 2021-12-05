package eu.morphemic.ufcreator.function_creator.function;

import java.util.Arrays;
import java.util.List;

public class CostPerUser implements PredefinedFunction{
    List<String> requirements;



    public CostPerUser() {
        this.requirements = Arrays.asList("first", "second");
    }

    @Override
    public boolean checkIfAvailable(List<String> variableNames) {
        return variableNames.containsAll(requirements);
    }
}
