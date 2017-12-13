package com.actions;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.bo.Client;
import com.services.GsClientService;

@ResultPath("/pages/")
public class GsClientAction extends BaseAction implements SessionAware {

	/**
	 * utilisée pour stocker la liste des produits destinés à afficher dans la
	 * vue
	 */
	private List<Client> listClients;

	/** utilisé pour stocker les données du produit saisies dans la vue */
	private Client client;

	private Map<String, Object> session;

	/** injection du service métier */
	@Autowired
	private GsClientService gsClientService;

	@Action(value = "addClient", results = { @Result(name = "success", location = "addClient.jsp") })
	public String addClient() {

		// On utilise le service métier pour sauvegarder en base de données le
		// produit
		// rempli par les données copiées automatiquement par struts de la vue
		// vers
		// l'objet produit
		gsClientService.addClient(client);

		// On retoune la page associée à success
		return SUCCESS;

	}

	@Action(value = "simuAuth", results = { @Result(name = "success", type="redirectAction", location = "getAllProducts") })
	public String simuAuth() {

		Client client = gsClientService.getClientById(Long.valueOf(1));

		if (client != null) {
			session.put("client", gsClientService.getClientById(Long.valueOf(1)));

		}

		return SUCCESS;

	}

	@Action(value = "getAllClients", results = { @Result(name = "success", location = "listClients.jsp") })
	public String getAllClients() {

		// En utiliant le service métier on charge la liste des produit et on la
		// stocke
		// dans l'attribut qui sera lu depuis une vue
		listClients = gsClientService.getAllClients();

		// on redirige vers la vue associé à la clé sucess
		return SUCCESS;

	}

	public List<Client> getListClients() {
		return listClients;
	}

	public void setListClients(List<Client> listClients) {
		this.listClients = listClients;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public GsClientService getGsClientService() {
		return gsClientService;
	}

	public void setGsClientService(GsClientService gsClientService) {
		this.gsClientService = gsClientService;
	}

	public void setSession(Map<String, Object> pSession) {
		session = pSession;

	}
}
