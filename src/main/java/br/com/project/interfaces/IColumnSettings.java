package br.com.project.interfaces;

import java.util.List;

import br.com.project.models.ColumnSettings;

/**
 *
 * @author Omer Faruk KURT e-mail:kurtomerfaruk@gmail.com
 * @param <T>
 */
public interface IColumnSettings<T> extends IGenericDAO<T> {

    public List<ColumnSettings> findByTableName(String simpleName);

}
