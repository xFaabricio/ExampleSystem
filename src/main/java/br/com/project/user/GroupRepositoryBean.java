package br.com.project.user;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class GroupRepositoryBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;
	
	public Group porId(Long id) {
		return this.manager.find(Group.class, id);
	}
	
	public List<Group> groups() {
		return this.manager.createQuery("from Group", Group.class)
				.getResultList();
	}
	
	public Group findGroupByName(String nameGroup) {
		Group group = null;
		
		try {
			group = this.manager.createQuery("from Group where lower(name) = :nameGroup", Group.class)
				.setParameter("nameGroup", nameGroup.toLowerCase()).getSingleResult();
		} catch (NoResultException e) {
			// Nenhum grupo encontrado com o nome especificado
		}
		
		return group;
	}
	
}
