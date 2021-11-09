package eu.morphemic.ufcreator.analyzer.service.cdo;

import java.util.ArrayList;
import java.util.List;

import eu.paasage.mddb.cdo.client.CDOClient;
import org.eclipse.swt.internal.C;
import org.jclouds.rest.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {CdoServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CdoServiceImplTest {

    @Autowired
    private CdoServiceImpl cdoServiceImpl;


    @Test
    void testGetAllCdoModels() {
        List<String> actualAllCdoModels = this.cdoServiceImpl.getAllCdoModels();
        assertTrue(actualAllCdoModels.isEmpty());
        assertEquals(actualAllCdoModels, this.cdoServiceImpl.getAllXmi());
    }

    @Test
    void testGetAllXmi() {
        List<String> actualAllXmi = this.cdoServiceImpl.getAllXmi();
        assertTrue(actualAllXmi.isEmpty());
        assertEquals(actualAllXmi, this.cdoServiceImpl.getAllCdoModels());
    }

}