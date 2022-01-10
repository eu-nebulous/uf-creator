package eu.morphemic.ufcreator.communication.cdo;

import camel.core.CamelModel;
import eu.paasage.mddb.cdo.client.exp.CDOSessionXImpl;
import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.cdo.internal.net4j.CDONet4jSessionImpl;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CdoServerClientApi.class})
@ExtendWith(SpringExtension.class)
class CdoServerClientApiTest {

    @Autowired
    CdoServerClientApi cdoServerClientApi;

    @Mock
    EList<EObject> contents;
    @Mock
    CDOTransaction transaction;
    @Mock
    CamelModel camelModel;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testConstructor() {
        CdoServerClientApi actualCdoServerClientApi = new CdoServerClientApi();
        actualCdoServerClientApi.closeSession(new CDOSessionXImpl(new CDONet4jSessionImpl(), true));
    }

    @Test
    void testOpenSession() {
        assertNull(this.cdoServerClientApi.openSession());
    }

    @Test
    void testOpenView() {
        assertNull(this.cdoServerClientApi.openView(new CDOSessionXImpl(new CDONet4jSessionImpl(), true)));
    }

    @Test
    void testOpenTransaction() {
        assertNull(this.cdoServerClientApi.openTransaction(new CDOSessionXImpl(new CDONet4jSessionImpl(), true)));
    }
    @Test
    void testGetCamelModel() {
        String resourceName="";
        when(transaction.getOrCreateResource(resourceName)).thenReturn(mock(CDOResource.class));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> this.cdoServerClientApi.getCamelModel(resourceName,transaction));
    }

}