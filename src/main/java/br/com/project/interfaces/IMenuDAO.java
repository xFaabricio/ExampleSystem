package br.com.project.interfaces;

import java.util.List;

import br.com.project.models.Menu;

public interface IMenuDAO<T> extends IGenericDAO<T> {

    List<Menu> searchMenuList(String text);
    Menu getTopMenu(Integer topMenuId);
}