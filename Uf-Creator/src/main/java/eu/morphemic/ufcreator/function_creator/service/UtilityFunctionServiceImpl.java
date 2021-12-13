package eu.morphemic.ufcreator.function_creator.service;

import eu.melodic.upperware.utilitygenerator.utility_function.utility_templates_provider.TemplateProvider;
import eu.morphemic.ufcreator.function_creator.PredefinedFunctionProvider;
import eu.morphemic.ufcreator.function_creator.controller.request.CreateFunctionRequest;
import eu.morphemic.ufcreator.function_creator.function.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UtilityFunctionServiceImpl implements UtilityFunctionService{

    public List<String> getAvailableFunctions(List<String> variableDtoList) {
        List<String> listOfAvailableFunctions = new ArrayList<>();
        ExpectedResponseTime expectedResponseTime = new ExpectedResponseTime();
        RamUsage ramUsage = new RamUsage();
        LocalityUtility localityUtility = new LocalityUtility();
        FinishSimulationOnTime finishSimulationOnTime = new FinishSimulationOnTime();
        CostPerUser costPerUser = new CostPerUser();
        CoreCostUtility coreCostUtility = new CoreCostUtility();
        CpuUsage cpuUsage = new CpuUsage();

        if(expectedResponseTime.checkIfAvailable(variableDtoList)) {
            listOfAvailableFunctions.add("expectedResponseTime");
        }
        if(ramUsage.checkIfAvailable(variableDtoList)){
            listOfAvailableFunctions.add("ramUsage");
        }
        if(localityUtility.checkIfAvailable(variableDtoList)){
            listOfAvailableFunctions.add("localityUtility");
        }
        if(finishSimulationOnTime.checkIfAvailable(variableDtoList)){
            listOfAvailableFunctions.add("finishSimulationOnTime");
        }
        if(cpuUsage.checkIfAvailable(variableDtoList)){
            listOfAvailableFunctions.add("cpuUsage");
        }
        if(coreCostUtility.checkIfAvailable(variableDtoList)){
            listOfAvailableFunctions.add("coreCostUtility");
        }
        if(costPerUser.checkIfAvailable(variableDtoList)){
            listOfAvailableFunctions.add("costPerUser");
        }
        return listOfAvailableFunctions;
    }

//    @Override
//    public void createUtilityFunction(CreateFunctionRequest createFunctionRequest) {
//        String formula = PredefinedFunctionProvider.getTemplate(createFunctionRequest.getVariableDTOList(), createFunctionRequest.getDimensions());
//
//    }
}
