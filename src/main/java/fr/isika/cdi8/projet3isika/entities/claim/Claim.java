package fr.isika.cdi8.projet3isika.entities.claim;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import fr.isika.cdi8.projet3isika.entities.demandes_client.Client;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQueries({

		@NamedQuery(name = "Claim.findByTitle", query = "SELECT c FROM Claim c WHERE c.title LIKE CONCAT('%',:title,'%') AND c.client= :client"),
		@NamedQuery(name = "Claim.findClaimByClient", query = "Select c From Claim c WHERE c.client= :client"),
		@NamedQuery(name = "Claim.findAll", query = "Select c FROM Claim c"),
		@NamedQuery(name = "Claim.findById", query = "SELECT c FROM Claim c WHERE c.idClaim = :idClaim"), })
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idClaim;
	
	private String title;
	private String subject;

	@Enumerated(EnumType.STRING)
	private StatusClaim statusClaim;
	
	@OneToOne
	private Client client;

	public Claim(Long idClaim, String title, String subject, StatusClaim statusClaim, Client client) {
		super();
		this.idClaim = idClaim;
		this.title = title;
		this.subject = subject;
		this.statusClaim = statusClaim;
		this.client = client;
	}
}
