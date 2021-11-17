package eu.morphemic.ufcreator.analyzer.service.cdo;

import camel.core.CamelModel;
import eu.paasage.mddb.cdo.client.exp.CDOClientX;
import eu.paasage.mddb.cdo.client.exp.CDOSessionX;
import eu.passage.upperware.commons.model.tools.CdoTool;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

import java.util.List;

public interface CdoService {
    CamelModel getCamelModel(String name, CDOTransaction tr);

    CDOSessionX openSession();

    public List<String> getAllXmi();
}
