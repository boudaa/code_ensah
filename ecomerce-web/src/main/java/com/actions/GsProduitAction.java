package com.actions;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.springframework.beans.factory.annotation.Autowired;

import com.bo.Produit;
import com.services.GsProduitService;

@ResultPath("/pages/")
public class GsProduitAction extends BaseAction {

	/**
	 * utilisée pour stocker la liste des produits destinés à afficher dans la vue
	 */
	private List<Produit> listProduits;

	/** utilisé pour stocker les données du produit saisies dans la vue */
	private Produit produit;

	/** injection du service métier */
	@Autowired
	private GsProduitService gsProduitService;

	@Action(value = "addProduit", results = { @Result(name = "success", location = "addProduit.jsp") })
	public String addProduit() {

		// On utilise le service métier pour sauvegarder en base de données le produit
		// rempli par les données copiées automatiquement par struts de la vue vers
		// l'objet produit
		gsProduitService.addProduit(produit);

		// On retoune la page associée à success
		return SUCCESS;

	}

	@Action(value = "getAllProducts", results = { @Result(name = "success", location = "listProducts.jsp") })
	public String getAllProducts() {

		// En utiliant le service métier on charge la liste des produit et on la stocke
		// dans l'attribut qui sera lu depuis une vue
		listProduits = gsProduitService.getAllProducts();

		// on redirige vers la vue associé à la clé sucess
		return SUCCESS;

	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public GsProduitService getGsProduitService() {
		return gsProduitService;
	}

	public void setGsProduitService(GsProduitService gsProduitService) {
		this.gsProduitService = gsProduitService;
	}

	public List<Produit> getListProduits() {
		return listProduits;
	}

	public void setListProduits(List<Produit> listProduits) {
		this.listProduits = listProduits;
	}

}
