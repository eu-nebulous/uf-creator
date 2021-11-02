package eu.morphemic.ufcreator.analyzer.service.camel;

import eu.morphemic.ufcreator.analyzer.service.cdo.CdoService;
import eu.morphemic.ufcreator.communication.cdo.CdoServerClientApi;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CamelModelServiceImplTest {

    @Test
    void testGetCamelModelNames() {
        CdoService cdoService = mock(CdoService.class);
        ArrayList<String> stringList = new ArrayList<String>();
        when(cdoService.getAllXmi()).thenReturn(stringList);
        List<String> actualCamelModelNames = (new CamelModelServiceImpl(new CdoServerClientApi(), cdoService))
                .getCamelModelNames();
        assertSame(stringList, actualCamelModelNames);
        assertTrue(actualCamelModelNames.isEmpty());
        verify(cdoService).getAllXmi();
    }

}