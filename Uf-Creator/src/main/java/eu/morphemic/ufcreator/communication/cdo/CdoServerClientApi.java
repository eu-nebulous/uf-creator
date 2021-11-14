package eu.morphemic.ufcreator.communication.cdo;


import camel.core.CamelModel;
import eu.paasage.mddb.cdo.client.exp.CDOClientX;
import eu.paasage.mddb.cdo.client.exp.CDOSessionX;
import eu.passage.upperware.commons.model.tools.CdoTool;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.view.CDOView;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.springframework.stereotype.Service;



@Service
public class CdoServerClientApi implements CdoServerApi {

    private CDOClientX cdoClient;

    @Override
    public CDOSessionX openSession() {
        return null;
    }

    @Override
    public CDOView openView(CDOSessionX sessionX) {
        return null;
    }

    @Override
    public CDOTransaction openTransaction(CDOSessionX sessionX) {
        return null;
    }

    @Override
    public CamelModel getCamelModel(String resourceName, CDOTransaction tr) {
        EList<EObject> contents = tr.getOrCreateResource(resourceName).getContents();

        return CdoTool.getLastCamelModel(contents)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Cannot load Camel Model for resourceName=%s. " +
                        "Check the value is valid and the model is available in CDO Server.", resourceName)));
    }

    @Override
    public void closeSession(CDOSessionX sessionX) {
    }

}