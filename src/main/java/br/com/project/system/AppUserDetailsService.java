package br.com.project.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.com.project.user.Group;
import br.com.project.user.User;
import br.com.project.user.UserRepositoryBean;
import br.com.project.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UserRepositoryBean userRepositoryBean = CDIServiceLocator.getBean(UserRepositoryBean.class);
		User usuario = userRepositoryBean.putLogin(login);
		
		UserSystem user = null;
		
		if (usuario != null) {
			user = new UserSystem(usuario, getGroups(usuario));
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getGroups(User user) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Group group : user.getGroups()) {
			authorities.add(new SimpleGrantedAuthority(group.getName().toUpperCase()));
		}
		
		return authorities;
	}

}
