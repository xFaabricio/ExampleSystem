
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.project.user.Group;
import br.com.project.user.User;

public class Teste {


	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("ambulance");
		EntityManager manager = factory.createEntityManager();
		
		EntityTransaction trx = manager.getTransaction();
		
		trx.begin();
		
		User user = new User();
		Group group = new Group();
		
		group.setName("ADMINISTRATOR");
		group.setDescription("Administrador do sistema");
		manager.persist(group);
		
		group.setName("USER");
		group.setDescription("Usuario");
		manager.persist(group);

		user.setLogin("MASTER");
		user.setLastName("MASTER");
		user.setPassword("MASTER");
		user.setEmail("master@ambulance.com");
		user.setName("Sr. Master");
//		user.setSalary((long) 8000);
		manager.persist(user);
		
		trx.commit();
	}
	
}
