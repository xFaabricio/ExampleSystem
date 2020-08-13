package br.com.project.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.project.user.User;
import br.com.project.user.UserRepositoryBean;
import br.com.project.user.UserService;
import br.com.project.util.jsf.FacesUtil;

@Named
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private FacesContext facesContext;
	
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private HttpServletResponse response;
	
	private User user;
	
	private String name;
	
	private String lastName;
	
	private String password;
		
	private String email;
	
	private String confirmatePassword;
	
	@Inject
	UserService userService;
	
	@Inject
	UserRepositoryBean userRepositoryBean;
	
	private String login;

	public void preRender() {
		if ("true".equals(request.getParameter("accessInvalid"))) {
			FacesUtil.addErrorMessage("Usuário ou senha inválido!");
		}
	}
	
	public void loginAccess() throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
		dispatcher.forward(request, response);
		
		facesContext.responseComplete();
	}
	
	public void loadUser(){
		this.user = new User();
	}
	
	public void newAccount(){
		
		String name = this.name;
		
		String lastName = this.lastName;
		
		String password = this.password;
		
		String login = this.login;
		
		String email = this.email;
		
		String confirmatePassword = this.confirmatePassword;
		
		String nameFormat = name;
		
		if(password.equals(confirmatePassword)){
		
			if(userRepositoryBean.putLogin(login)==null){
			
				if(lastName!=null && !lastName.equals("")){
					nameFormat = nameFormat + " " + lastName;
				}
				
				userService.createNewAccount(nameFormat, login, email, password);
			
			}else{
				FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "User already exists", "Change Login");
			    FacesContext.getCurrentInstance().addMessage(null, msg);
			}
			
		}else{
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Confirmate Password Error !", "Password Error");
		    FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public User getUser() {
		return user;
	}

	public String getConfirmatePassword() {
		return confirmatePassword;
	}

	public void setConfirmatePassword(String confirmatePassword) {
		this.confirmatePassword = confirmatePassword;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}