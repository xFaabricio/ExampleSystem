package br.com.project.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

@Named
@ApplicationScoped
public class UserService implements Serializable{
	
	@Inject
	private EntityManager manager;
		
	@Inject
	UserRepositoryBean userRepositoryBean;
			
	@Inject
	GroupRepositoryBean groupRepositoryBean;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	
	private Group group;
	
	public void createNewAccount(String name, String login, String email, String password){
		
		EntityTransaction entityTransaction = manager.getTransaction();
		
		try {
		
			entityTransaction.begin();
			
			user = new User();
			group = new Group();
			List<Group> groups = new ArrayList<>();	
		
			user.setName(name);
			user.setLogin(login);
			user.setEmail(email);
			user.setPassword(password);
			
			group = groupRepositoryBean.findGroupByName("USER");
			
			if(group==null){
				
				entityTransaction.begin();
				
				group.setDescription("Usuario do Sistema");
				group.setName("USER");
				
				this.manager.persist(group);
				
				entityTransaction.commit();
				
			}
			
			group = groupRepositoryBean.findGroupByName("USER");
			groups.add(group);
			
//			user.getGroups().add(group);
			user.setGroups(groups);
			
			this.manager.persist(this.user);
			
			entityTransaction.commit();
			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "User Created", "User Create. Please try logging in.");
		    FacesContext.getCurrentInstance().addMessage(null, msg);	
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "User Create Error", "User not Create. Try Again");
		    FacesContext.getCurrentInstance().addMessage(null, msg);
		}

	}
	
	public List<User> createViewToUser(String login){
		return userRepositoryBean.findUsersBasedOnGroup(login);
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}	
	
}
