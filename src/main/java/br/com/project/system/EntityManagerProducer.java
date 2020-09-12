package br.com.project.system;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProducer {

	private EntityManagerFactory entityManagerFactory;
	
	public EntityManagerProducer(){
		entityManagerFactory = Persistence.createEntityManagerFactory("ambulance");
	}
	
	@Produces @RequestScoped
	public EntityManager createEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes EntityManager entityManager){
		entityManager.close();
	}
	
}
