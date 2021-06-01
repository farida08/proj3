package fr.isika.cdi8.projet3isika.presentation;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.isika.cdi8.projet3isika.entities.demandes_client.Client;
import fr.isika.cdi8.projet3isika.entities.prestations_fournisseur.Fournisseur;
import fr.isika.cdi8.projet3isika.idao.ClientIDao;
import fr.isika.cdi8.projet3isika.idao.FournisseurIDao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ManagedBean
@RequestScoped



public class InfosFournisseurMBean {

	@Autowired
	private UserMBean userMBean;
	
	@Inject
	private FournisseurIDao fournisseurIDao;

	private Fournisseur newfournisseur;

	public Fournisseur getFournisseur() {
		String mail = userMBean.connectedMail();
		newfournisseur = fournisseurIDao.findByMail(mail);
		return newfournisseur;
	}}