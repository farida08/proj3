package fr.isika.cdi8.projet3isika.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.isika.cdi8.projet3isika.entities.claim.Claim;
import fr.isika.cdi8.projet3isika.entities.demandes_client.Client;
import fr.isika.cdi8.projet3isika.idao.ClaimIDao;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@Component
@ManagedBean
@SessionScoped
public class ClaimMBean implements Serializable {
	
	@Autowired
	private ClaimIDao claimIDao;
	
	private Claim newclaim =  new Claim();
	Client client = new Client ();
	
	
	private List<Claim> claims= new ArrayList<Claim>();
	
	public void createClaim() {
		newclaim.setClient(client);
		claimIDao.saveOrUpdate(newclaim);
	}
	
	
	
	

}
