package br.com.project.dao.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.NoResultException;

import br.com.project.interfaces.IMenuDAO;
import br.com.project.models.Menu;
import br.com.project.util.jsf.JsfUtil;


/**
 *
 * @author Omer Faruk KURT e-mail:kurtomerfaruk@gmail.com
 */
public class MenuDAOImpl extends GenericDAOImpl<Menu> implements IMenuDAO<Menu> {

    private static final long serialVersionUID = 3205537521092684220L;

    public MenuDAOImpl() {
        super(Menu.class);
    }

    @Override
    public List<Menu> findAll() {
        try {
            return this.entityManager.createNamedQuery("Menu.findAll").getResultList();
        } catch (NoResultException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
//            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/messages").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    @Override
    public List<Menu> searchMenuList(String text) {
        try {
            return this.entityManager.createNamedQuery("Menu.findByName").setParameter("menuName", text+"%").getResultList();
        } catch (NoResultException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
//            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/messages").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    @Override
    public Menu getTopMenu(Integer topMenuId) {
         try {
            return (Menu) this.entityManager.createNamedQuery("Menu.findById").setParameter("id", topMenuId).getSingleResult();
        } catch (NoResultException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, e);
//            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/messages").getString("PersistenceErrorOccured"));
            return null;
        }
    }

}
