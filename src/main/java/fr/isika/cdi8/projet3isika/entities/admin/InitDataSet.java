package fr.isika.cdi8.projet3isika.entities.admin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Component;
import fr.isika.cdi8.projet3isika.dao.ClientDao;
import fr.isika.cdi8.projet3isika.dao.FournisseurDao;
import fr.isika.cdi8.projet3isika.entities.common.Adresse;
import fr.isika.cdi8.projet3isika.entities.demandes_client.Client;
import fr.isika.cdi8.projet3isika.idao.ClientIDao;
import fr.isika.cdi8.projet3isika.idao.FournisseurIDao;

@Component
@Profile ("initData")
public class InitDataSet {
	@Autowired
	ClientIDao clientIDao ;
	@Autowired
	FournisseurIDao fournisseurIDao;
//	@Autowired
//	Adresse adresse;
	
@PostConstruct
public void initJeuxdeDonnees() throws ParseException {
	

	Adresse Adresse1 = new Adresse(1, "12", "alain made","paris","75010", "IDF", "Paris");
	Client client1 = clientIDao.save(new Client((long) 1,"michel","sardo","1234","michel@gmail.com","0129323245","photo1",Adresse1,"Michou"));
 
	
	Adresse Adresse2 = new Adresse(2, "15", "gerard michel","paris","75012", "IDF", "Paris");
	Client client2 = clientIDao.save(new Client((long) 2,"eric","daniel","1234","ericdaniel@gmail.com","0129323235","photo2",Adresse2,"eRIC12"));
	
	Adresse Adresse3 = new Adresse(3, "18", "Colonel Fabien","paris","75010", "IDF", "Paris");
	Client client3 = clientIDao.save(new Client((long) 3,"Stephane","George","1234","stephane@gmail.com","0129323250","photo3",Adresse3,"stephan10"));
	
	Adresse Adresse4 = new Adresse(4, "21", "Galieni","paris","75012", "IDF", "Paris");
	Client client4 = clientIDao.save(new Client((long) 4,"gill","cho","1234","gill@gmail.com","0129329867","photo4",Adresse4,"Gilou"));
	
	Adresse Adresse5 = new Adresse(5, "21", "Galieni","paris","75012", "IDF", "Paris");
	Client client5 = clientIDao.save(new Client( (long) 5,"all","dall","1234","ali@gmail.com","0129329834","photo5",Adresse5,"LILI"));
	
	Adresse Adresse6 = new Adresse(4, "21", "Galieni","paris","75012", "IDF", "Paris");
	Client client6 =clientIDao.save(new Client((long) 6,"PATRICK","DARO","1234","daro@gmail.com","0129329899","photo6",Adresse6,"Daro"));
}
}
	
	


