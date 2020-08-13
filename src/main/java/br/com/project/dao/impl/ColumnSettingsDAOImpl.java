package br.com.project.dao.impl;

import br.com.project.interfaces.IColumnSettings;
import br.com.project.models.ColumnSettings;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Omer Faruk KURT e-mail:kurtomerfaruk@gmail.com
 */
public class ColumnSettingsDAOImpl extends GenericDAOImpl<ColumnSettings> implements IColumnSettings<ColumnSettings> {

    private static final long serialVersionUID = -7924164482600457603L;

    public ColumnSettingsDAOImpl() {
        super(ColumnSettings.class);
    }

    @Override
    public List<ColumnSettings> findByTableName(String tableName) {
        try {
            return entityManager.createNamedQuery("ColumnSettings.findByTableName").setParameter("tableName", tableName).getResultList();
        } catch (NoResultException ne) {
            return null;
        }
    }
    
    
}
