package eu.morphemic.ufcreator.communication.cdo;

import camel.core.CamelModel;
import eu.paasage.mddb.cdo.client.exp.CDOSessionX;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.view.CDOView;

public interface CdoServerApi {

    CDOSessionX openSession();

    CDOView openView(CDOSessionX sessionX);

    CDOTransaction openTransaction(CDOSessionX sessionX);

    CamelModel getCamelModel(String resourceName, CDOTransaction tr);

    void closeSession(CDOSessionX sessionX);
}
