package eu.morphemic.ufcreator.communication.cdo;

import eu.paasage.mddb.cdo.client.exp.CDOSessionXImpl;
import org.eclipse.emf.cdo.internal.net4j.CDONet4jSessionImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@ContextConfiguration(classes = {CdoServerClientApi.class})
@ExtendWith(SpringExtension.class)
class CdoServerClientApiTest {

    @Autowired
    private CdoServerClientApi cdoServerClientApi;


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
    }

    @Test
    void testCloseSession() {
    }


}