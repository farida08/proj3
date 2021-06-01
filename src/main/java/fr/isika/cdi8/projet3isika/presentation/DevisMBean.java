package fr.isika.cdi8.projet3isika.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


import fr.isika.cdi8.projet3isika.entities.common_client_fournisseur.Devis;
import fr.isika.cdi8.projet3isika.entities.common_client_fournisseur.Transaction;
import fr.isika.cdi8.projet3isika.entities.demandes_client.Demande;
import fr.isika.cdi8.projet3isika.services_metier.DevisService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@RequestScoped
public class DevisMBean {
	@Inject
	DevisService devisService;
	@Inject
	DemandeClientMBean demandeClientMBean;

	Demande demande = demandeClientMBean.getDemande();
	List<Devis> listDevis = new ArrayList();

	public List<Devis> validateDevis() {
		devisService.validateListDevis(listDevis);

		return listDevis;

	}

	public List<Devis> annulerDevis() {
		devisService.annulerListDevis(listDevis);
		return listDevis;
	}

	public DevisMBean() {
		String choice = demandeClientMBean.getChoice();
		List<Transaction> listTransactions = demande.getTransactions();
		switch (choice) {
		case "PRIX":			
			for (Transaction t : listTransactions) {
				listDevis.add(devisService.getLowestCostPrestation(t.getSousType().getNom()));
			}			
			break;
			
		case "DATE":		
			for (Transaction t : listTransactions) {
				listDevis.add(devisService.getBetterPrestationByDate(t.getSousType().getNom(),
						t.getDemande().getDateSouhaitee()));
			}			
			break;

		default: 
			for (Transaction t : listTransactions) {
				listDevis.add(devisService.getEarlierPrestation(t.getSousType().getNom()));
			}
			break;
		}
		devisService.createListDevis(listDevis);
		
	}

}
