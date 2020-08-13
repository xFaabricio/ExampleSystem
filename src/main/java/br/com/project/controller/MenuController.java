package br.com.project.controller;

import br.com.project.dao.impl.MenuDAOImpl;

import br.com.project.interfaces.IMenuDAO;
import br.com.project.models.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Omer Faruk KURT e-mail:kurtomerfaruk@gmail.com
 */
@ManagedBean
@ViewScoped
public class MenuController extends AbstractController<Menu> implements java.io.Serializable {

    private static final long serialVersionUID = 7481372634818437093L;
    private IMenuDAO menuDao;

    private String pageLink;
    private String pageName;

    private List<Menu> menuList;

    private String searhText;

    /**
     *
     */
    public MenuController() {
        super(Menu.class);
        menuDao = new MenuDAOImpl();
        pageLink = "blankPage";
        pageName = "Main Page";

    }

    /**
     *
     * @return
     */
    public String getPageLink() {
        return pageLink;
    }

    /**
     *
     * @param pageLink
     */
    public void setPageLink(String pageLink) {
        this.pageLink = pageLink;
    }

    /**
     *
     * @return
     */
    public String getPageName() {
        return pageName;
    }

    /**
     *
     * @param pageName
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public List<Menu> getMenuList() {
        if (menuList == null) {
            menuList = menuDao.findAll();
        }

        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

//    /**
//     *
//     * @return
//     */
//    public List<Menu> menuList() {
//        return menuDao.findAll();
//    }
    public String getSearhText() {
        return searhText;
    }

    public void setSearhText(String searhText) {
        this.searhText = searhText;
    }

    /**
     *
     * @param link
     * @param name
     */
    public void setPage(String link, String name) {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, Object> map = context.getViewRoot().getViewMap();
        List<String> list = new ArrayList<>();

        for (String key : map.keySet()) {
            if (!key.equals("menuController")) {
                list.add(key);
            }
        }

        if (list != null && !list.isEmpty()) {
            for (String get : list) {
                map.remove(get);
            }
        }

        setPageLink(link);
        setPageName(name);
    }

    public void menuSearchValueChange(ValueChangeEvent event) {
        System.out.println("Menu Search Value Change");
        if (event.getOldValue() == null || !event.getOldValue().equals(event.getNewValue())) {
            menuList = menuDao.searchMenuList(event.getNewValue().toString());
            System.out.println("Event getOldValue");
            System.out.println("menuListSize:"+menuList.size());
        }
        
        System.out.println("size:"+menuList.size());

        for (int i = 0; i < menuList.size(); i++) {
            Menu get = menuList.get(i);
            if (get.getTopMenuId() != null) {
                Menu topMenu = menuDao.getTopMenu(get.getTopMenuId());

                if (topMenu != null) {
                    if (!menuList.contains(topMenu)) {
                        menuList.add(topMenu);
                    }
                }
            }
        }
    }

}
