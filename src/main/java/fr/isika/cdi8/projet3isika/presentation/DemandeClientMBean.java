package fr.isika.cdi8.projet3isika.presentation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import fr.isika.cdi8.projet3isika.entities.common_client_fournisseur.SousType;
import fr.isika.cdi8.projet3isika.entities.common_client_fournisseur.Transaction;
import fr.isika.cdi8.projet3isika.entities.demandes_client.Client;
import fr.isika.cdi8.projet3isika.entities.demandes_client.Demande;
import fr.isika.cdi8.projet3isika.entities.prestations_fournisseur.Prestation;
import fr.isika.cdi8.projet3isika.idao.DavisIDao;
import fr.isika.cdi8.projet3isika.idao.TransactionIDao;
import fr.isika.cdi8.projet3isika.services_metier.ClientService;
import fr.isika.cdi8.projet3isika.services_metier.DemandeClientService;
import fr.isika.cdi8.projet3isika.services_metier.DevisService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ManagedBean
@SessionScoped
public class DemandeClientMBean {

	private static Logger logger = LogManager.getLogger(DemandeClientMBean.class);

	@Autowired
	private DemandeClientService demandeClientService;

	private Prestation prestation = new Prestation();

	@Autowired
	private ClientMBean clientMBean;
	@Autowired
	private UserMBean userMBean;

	@Autowired
	private TransactionIDao transactionIDao;

	@Inject
	private ClientService clientService;

	private String choice;
	private Demande demande;

	private CreationDemandeClientDTO creationDemandeClientDTO = new CreationDemandeClientDTO();

	@Inject
	DevisService devisService;

	@Inject
	DavisIDao devisDAO;

	public Demande createDemandeClient() {

		// TODO validation technique de la demande
		logger.info("fetch client by email : " + userMBean.connectedMail());

		Client client = clientService.findByMail(userMBean.connectedMail());

		logger.info("fetched client from database : " + client);
		Demande newDemande = new Demande();
		newDemande.setDatedemande(java.sql.Date.valueOf(creationDemandeClientDTO.getDateCreation()));
		newDemande.setDateSouhaitee(creationDemandeClientDTO.getDateSouhaitee());
		newDemande.setClient(client);
		List<Transaction> listTransactions = new ArrayList<Transaction>();
		if (creationDemandeClientDTO.estUneReparation()) {
			Transaction transactionReparation = new Transaction();
			transactionReparation.setType("reparation");
			SousType sousType = new SousType();
			sousType.setNom(creationDemandeClientDTO.getNomReparation());
			transactionReparation.setSousType(sousType);
			sousType.setTransaction(transactionReparation);
			transactionReparation.setDemande(newDemande);
			listTransactions.add(transactionReparation);

		}

		if (creationDemandeClientDTO.estUneLocationOutillage()) {
			Transaction transactionLocationOutillage = new Transaction();
			transactionLocationOutillage.setType("locationOutillage");
			SousType sousType = new SousType();
			sousType.setNom(creationDemandeClientDTO.getNomOutillage());
			sousType.setTransaction(transactionLocationOutillage);
			transactionLocationOutillage.setSousType(sousType);
			transactionLocationOutillage.setDemande(newDemande);

			listTransactions.add(transactionLocationOutillage);
		}
		if (creationDemandeClientDTO.estUneLocationGarage()) {
			Transaction transactionLocationGarage = new Transaction();
			transactionLocationGarage.setType("locationGarage");
			SousType sousType = new SousType();
			sousType.setNom(creationDemandeClientDTO.getNomGarage());
			sousType.setTransaction(transactionLocationGarage);
			transactionLocationGarage.setSousType(sousType);
			transactionLocationGarage.setDemande(newDemande);
			listTransactions.add(transactionLocationGarage);
		}
		if (creationDemandeClientDTO.estUneMainOuvre()) {
			Transaction transactionMainOeuvre = new Transaction();
			transactionMainOeuvre.setType("mainOuvre");
			SousType sousType = new SousType();
			sousType.setNom(creationDemandeClientDTO.getNomMain());
			sousType.setTransaction(transactionMainOeuvre);
			transactionMainOeuvre.setSousType(sousType);
			transactionMainOeuvre.setDemande(newDemande);
			listTransactions.add(transactionMainOeuvre);
		}
		newDemande.setDatedemande(Calendar.getInstance().getTime());
		newDemande.setTransactions(listTransactions);
		logger.info("Création de la demande " + newDemande);
		demandeClientService.creerNouvelleDemande(newDemande);
		return newDemande;

	}

	public String getLowestCostPrestation() {
		choice = "PRIX";
		demande = createDemandeClient();
		return "Devis.xhtml";
	}

	public String getEarlierPrestation() {
		choice = "PLUSTOT";
		demande = createDemandeClient();
		return "Devis.xhtml";
	}

	public String getBetterPrestationByDate() {
		choice = "DATE";
		demande = createDemandeClient();
		return "Devis.xhtml";
	}

}
