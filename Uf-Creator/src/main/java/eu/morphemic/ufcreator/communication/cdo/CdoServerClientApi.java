package eu.morphemic.ufcreator.communication.cdo;


import camel.core.CamelModel;
import eu.paasage.mddb.cdo.client.exp.CDOClientX;
import eu.paasage.mddb.cdo.client.exp.CDOClientXImpl;
import eu.paasage.mddb.cdo.client.exp.CDOSessionX;
import eu.passage.upperware.commons.model.tools.CdoTool;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.view.CDOView;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.springframework.stereotype.Service;

import static com.ibm.icu.text.MessageFormat.format;


@Service
@RequiredArgsConstructor
public class CdoServerClientApi implements CdoServerApi {

    private final CDOClientX cdoClient;

    @Override
    public CDOSessionX openSession() {
        return cdoClient.getSession();
    }

    @Override
    public CDOView openView(CDOSessionX sessionX) {
        return null;
    }

    @Override
    public CDOTransaction openTransaction(CDOSessionX sessionX) {
        return sessionX.openTransaction();
    }

    @Override
    public CamelModel getCamelModel(String resourceName, CDOTransaction tr) {
        EList<EObject> contents = tr.getOrCreateResource(resourceName).getContents();
        if (CollectionUtils.isEmpty(contents)) {
            throw new IllegalArgumentException(format("Cannot load Camel Model for resourceName=%s. " +
                    "Check the value is valid and the model is available in CDO Server.", resourceName));
        }

        return CdoTool.getLastCamelModel(contents)
                .orElseThrow(() -> new IllegalStateException(format("Could not find Camel Model for resourceName=%s", resourceName)));

    }


    @Override
    public void closeSession(CDOSessionX sessionX) {
        sessionX.closeSession();
    }

}
