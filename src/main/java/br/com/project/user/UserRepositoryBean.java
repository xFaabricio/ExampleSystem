package br.com.project.user;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UserRepositoryBean implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	@Inject
	GroupRepositoryBean groupRepositoryBean;
	
	public User byId(Long id) {
		return this.manager.find(User.class, id);
	}
	
	public List<User> usuarios() {
		return this.manager.createQuery("from User", User.class)
				.getResultList();
	}

	public User putLogin(String login) {
		User user = null;
		
		try {
			user = this.manager.createQuery("from User where lower(login) = :login", User.class)
				.setParameter("login", login.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// Nenhum usuário encontrado com o login
		}
		
		return user;
	}

	public List<User> findUsersBasedOnGroup(String login) {
		User user = null;
		Group group = new Group();
	
		user = this.manager.createQuery("from User where lower(login) = :login", User.class)
				.setParameter("login", login.toLowerCase()).getSingleResult();
		
		group = groupRepositoryBean.findGroupByName("USER");
		
		if(user.getGroups().contains(group)){
			return this.manager.createQuery("from User where lower(login) = :login", User.class)
					.setParameter("login", login.toLowerCase()).getResultList();
		}else{
			return usuarios();
		}		
	}	
}
