package eu.morphemic.ufcreator.function_creator.function;

import java.util.Arrays;
import java.util.List;

public class ExpectedResponseTime implements PredefinedFunction {

    List<String> requirements;

    public ExpectedResponseTime() {
        this.requirements = Arrays.asList("first", "second");
    }

    @Override
    public boolean checkIfAvailable(List<String> variableNames) {
        return variableNames.containsAll(requirements);
    }
}
