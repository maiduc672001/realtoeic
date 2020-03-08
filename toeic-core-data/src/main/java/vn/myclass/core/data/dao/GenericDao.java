package vn.myclass.core.data.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<ID extends Serializable,T> {
    List<T> findAll();
    T upDate(T entity);
    void save(T entity);
    T findById(ID id);
    Object[] findByProperty(String property,String sortDirection,String sortExpression,Object value);
    Integer delete(List<ID> ids);
}
