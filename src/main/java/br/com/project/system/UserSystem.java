package br.com.project.system;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import br.com.project.user.User;

public class UserSystem extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;
	
	private User user;
	
	public UserSystem(User user, Collection<? extends GrantedAuthority> authorities) {
		super(user.getLogin(), user.getPassword(), authorities);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
