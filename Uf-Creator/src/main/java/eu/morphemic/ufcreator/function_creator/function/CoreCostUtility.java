package eu.morphemic.ufcreator.function_creator.function;

import java.util.Arrays;
import java.util.List;

public class CoreCostUtility implements PredefinedFunction{

    List<String> requirements;

    public CoreCostUtility() {
        this.requirements = Arrays.asList("avrResponseTime", "second");
    }

    @Override
    public boolean checkIfAvailable(List<String> variableNames) {
        return variableNames.containsAll(requirements);
    }
}
