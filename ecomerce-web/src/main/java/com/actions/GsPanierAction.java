package com.actions;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.bo.Client;
import com.bo.Commande;
import com.bo.LignePanier;
import com.bo.Panier;
import com.bo.Produit;
import com.services.GsCommmandeService;
import com.services.GsProduitService;

@ResultPath("/pages/")
public class GsPanierAction extends BaseAction implements SessionAware {

	private Long idProduit;
	private int quantite = 1;

	private Map<String, Object> session;

	@Autowired
	private GsProduitService gsProduitService;

	@Autowired
	private GsCommmandeService gsCommandes;

	private Panier panier;

	@Action(value = "addProduitToPanier", results = { @Result(name = "success", type="redirectAction", location = "getAllProducts") })
	public String addProduitToPanier() {

		Produit produit = gsProduitService.getProduitById(idProduit);

		if (produit != null) {

			LignePanier lp = new LignePanier(produit, quantite);

			Panier panier = getPanierFormSession();

			panier.addToPanier(lp);

		} else {

			// On ajoute un message dans la requete
			System.out.println("debug");
		}

		return SUCCESS;
	}

	private Panier getPanierFormSession() {

		if (!session.containsKey("panier")) {
			session.put("panier", new Panier());
		}

		return (Panier) session.get("panier");
	}

	@Action(value = "monPanier", results = { @Result(name = "success", location = "monPanier.jsp") })
	public String monPanier() {

		panier = getPanierFormSession();
		
		System.out.println(panier);

		// on redirige vers la vue associé à la clé sucess
		return SUCCESS;

	}

	@Action(value = "validerPanier", results = { @Result(name = "success", type="redirectAction", location = "monPanier") })

	public String validerPanier() {

		Panier panier = getPanierFormSession();
		Client client = (Client) session.get("client");


		// On valide la commande

		gsCommandes.validerCommande(panier, client);

		return SUCCESS;

	}

	public void setSession(Map<String, Object> pSession) {
		session = pSession;

	}

	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

}
