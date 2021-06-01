package fr.isika.cdi8.projet3isika.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import fr.isika.cdi8.projet3isika.entities.claim.Claim;
import fr.isika.cdi8.projet3isika.idao.ClaimIDao;

@Repository
@Transactional
public class ClaimDao  implements  ClaimIDao{
	
	@PersistenceContext
	private EntityManager em ;
	
	
	
	@Override
	public Claim saveOrUpdate(Claim claim) {
		if (claim.getIdClaim() == null) {
			em.persist(claim);
		} else
			em.merge(claim);
	return claim;
	}
	
	
	public Claim findById(Long idClaim) {
			return em.find(Claim.class, idClaim);
	
	
	}


@Override
public List<Claim> findByTitle(Claim claim) {
	return em.createNamedQuery("Claim.findByTitle", Claim.class)
			.setParameter("title", claim.getTitle())
			.setParameter("client", claim.getClient())
			.getResultList();
}


@Override
public List<Claim> findByStatusClaim(Claim claim) {
	return em.createNamedQuery("Claim.findByStatusClaim", Claim.class)
			.setParameter("statusClaim", claim.getStatusClaim())
			.setParameter("client", claim.getClient())
			.getResultList();
}



@Override
public List<Claim> findAll() {
	return em.createNamedQuery("Claim.findAll", Claim.class).getResultList()
	;
}


}



