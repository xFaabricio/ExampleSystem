package br.com.project.user;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.project.system.Security;

@Named
@ViewScoped
public class UserView implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	Security security;
	
	@Inject
	User user;
	
	@Inject
	UserRepositoryBean userRepositoryBean;
	
	@Inject
	private EntityManager manager;
	
	private String name;
	
	private String login;
	
	private String email;
	
	private Long balance;
	
	private Long salary;
	
	private String groups;

	private List<User> users;
	
	@Inject
    private UserService service;
	
	@PostConstruct
    public void init() {
        users = service.createViewToUser(security.getLoginUser());
    }
	
	public void save(){	
		
		EntityTransaction entityTransaction = manager.getTransaction();
		
		entityTransaction.begin();
		
		for(User user : users){
			this.manager.merge(user);
		} 
		
		entityTransaction.commit();
		
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Updated Modifications", "Updated Modifications");
	    FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}
	
}
