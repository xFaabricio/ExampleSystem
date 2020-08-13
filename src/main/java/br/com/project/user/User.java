package br.com.project.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Named;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.function.SQLFunctionRegistry;
import org.hibernate.mapping.Selectable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="User")
@Named	
public class User implements Selectable, UserDetails, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="LOGIN", length=512)
	@NotNull
	private String login;
	
	@Column(name="EMAIL", length=4000)
	@NotNull
	private String email;
	
	@Column(name="PASSWORD", length=4000)
	@NotNull
	private String password;
	
	@Column(name="NAME", length=4000)
	@NotNull
	private String name;
	
	@Column(name="LAST_NAME", length=4000)
	@NotNull
	@Transient
	private String lastName;
	
	
	@ManyToMany(targetEntity = Group.class, cascade = CascadeType.ALL)
	@JoinTable(name="USER_GROUP",joinColumns= 
	@JoinColumn(name="USER_ID", referencedColumnName="ID"),	
	inverseJoinColumns= 
	@JoinColumn(name="GRUPO_ID", referencedColumnName="ID"))
	private List<Group> groups = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}
	
	public User(Long id, String login, String email, String password, String name, String lastName,
			List<Group> groups) {
		super();
		this.id = id;
		this.login = login;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.groups = groups;
	}

	public User() {
		super();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getAlias(Dialect arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlias(Dialect arg0, org.hibernate.mapping.Table arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTemplate(Dialect arg0, SQLFunctionRegistry arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getText(Dialect arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFormula() {
		// TODO Auto-generated method stub
		return false;
	}

}
