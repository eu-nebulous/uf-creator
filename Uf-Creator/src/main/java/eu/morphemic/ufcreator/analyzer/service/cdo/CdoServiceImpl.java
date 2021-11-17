package eu.morphemic.ufcreator.analyzer.service.cdo;

import camel.core.CamelModel;
import camel.core.NamedElement;
import eu.paasage.mddb.cdo.client.CDOClient;
import eu.paasage.mddb.cdo.client.exp.CDOClientX;
import eu.paasage.mddb.cdo.client.exp.CDOSessionX;
import eu.paasage.upperware.metamodel.cp.CpPackage;
import eu.paasage.upperware.metamodel.types.TypesPackage;
import eu.passage.upperware.commons.model.tools.CdoTool;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.cdo.transaction.CDOTransaction;
import org.eclipse.emf.cdo.view.CDOQuery;
import org.eclipse.emf.cdo.view.CDOView;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.net4j.connector.ConnectorException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CdoServiceImpl implements CdoService{


    private CDOClient getCdoClient() {
        CDOClient client = new CDOClient();
        registerPackages(client);
        return client;
    }

    private void registerPackages(CDOClient cdoClient) {
        cdoClient.registerPackage(CpPackage.eINSTANCE);
        cdoClient.registerPackage(TypesPackage.eINSTANCE);
    }

    public List<String> getAllCdoModels() {
        List<String> allXmiModels = this.getAllXmi();
        allXmiModels.sort(String::compareTo);
        return allXmiModels;
    }

    public List<String> getAllXmi() {
        List<String> result = new ArrayList<>();
        CDOView cdoView = null;
        try {
            CDOClient client = getCdoClient();
            cdoView = client.openView();

            CDOQuery sql = cdoView.createQuery("sql", "select * from repo1.camel_core_camelmodel;");

            result = sql.getResult().stream()
                    .map(o -> (CamelModel) o)
                    .map(NamedElement::getName)
                    .collect(Collectors.toList());
        } catch (ConnectorException ex) {
            log.error("Error by getting uploaded models. CDO is not responding", ex);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error by getting uploaded models. CDO does not respond.");
        } catch (RuntimeException ex) {
            log.debug("List of available models is empty:", ex);
        } finally {
            if (cdoView != null) {
                cdoView.close();
            }
        }
        return result;
    }

    private CDOClientX cdoClientX;

    static {
        XMIResToResFact();
    }

    private static void XMIResToResFact(){
        Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap( ).put("*",
                new XMIResourceFactoryImpl() {
                    public Resource createResource(URI uri) {
                        return new XMIResourceImpl(uri);
                    }
                }
        );
    }

    @Override
    public CamelModel getCamelModel(String resourceName, CDOTransaction tr) {
        EList<EObject> contents = tr.getOrCreateResource(resourceName).getContents();

        return CdoTool.getLastCamelModel(contents)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Cannot load Camel Model for resourceName=%s. " +
                        "Check the value is valid and the model is available in CDO Server.", resourceName)));
    }

    @Override
    public CDOSessionX openSession() {
        return cdoClientX.getSession();
    }

}
