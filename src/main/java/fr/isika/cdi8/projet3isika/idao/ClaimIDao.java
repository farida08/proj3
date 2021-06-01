package fr.isika.cdi8.projet3isika.idao;


import java.util.List;

import fr.isika.cdi8.projet3isika.entities.claim.Claim;

public interface ClaimIDao {

	Claim saveOrUpdate(Claim claim);

	Claim findById(Long idClaim);

	List<Claim> findByTitle(Claim claim);

	List<Claim> findByStatusClaim(Claim claim);

	List<Claim> findAll();
	
	
	
	

}
