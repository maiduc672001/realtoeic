package vn.myclass.core.data.daoimpl;

import org.hibernate.*;
import org.hibernate.query.Query;
import vn.myclass.core.common.constant.CoreConstant;
import vn.myclass.core.common.utils.HibernateUtils;
import vn.myclass.core.data.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<ID extends Serializable,T> implements GenericDao<ID,T> {

    private Class<T> persistenceClass;
    public AbstractDao(){
        this.persistenceClass=(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
    public String getPersistenceName(){
        return persistenceClass.getSimpleName();
    }
    public List<T> findAll() {
        List<T> list=new ArrayList<T>();
        Session session=HibernateUtils.getSessionFactory().openSession();
        Transaction transaction=null;
        try {
            transaction=session.beginTransaction();
            StringBuilder hql = new StringBuilder("from ");
            hql.append(this.getPersistenceName());
            Query query = session.createQuery(hql.toString());
            list=query.list();
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return list;
    }

    public T upDate(T entity) {
    T result=null;
    Session session=HibernateUtils.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try{
            Object o=session.merge(entity);
            result=(T) o;
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }

        return result;
    }

    public void save(T entity) {
        Session session=HibernateUtils.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try {
            session.persist(entity);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public T findById(ID id) {
        T result=null;
        Session session=HibernateUtils.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        try {
            result=(T) session.get(persistenceClass,id);
            if(result==null){
                throw new ObjectNotFoundException("NOT FOUND"+id, (Object) null);
            }
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return result;
    }

    public Object[] findByProperty(String property, String sortDirection, String sortExpression, Object value) {
        Session session=HibernateUtils.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        List<T> list=new ArrayList<T>();
        Object total=0;
        try {

            StringBuilder sql=new StringBuilder("from ");
            sql.append(this.getPersistenceName());
            if(property!=null&& value!=null) {
                sql.append(" where ");
                sql.append(property).append(" =: value");
            }
            if(sortDirection!=null&&sortExpression!=null){
                sql.append(" order by ").append(sortExpression);
                sql.append(" "+(sortDirection.equals(CoreConstant.SORT_ASC)?"asc":"desc"));
            }
            Query query1=session.createQuery(sql.toString());
            if(value!=null){
                query1.setParameter("value",value);
            }
            list=query1.list();
            StringBuilder sql2=new StringBuilder("count  from ");
            sql2.append(this.getPersistenceName());
            if(property!=null&&value!=null) {
                sql2.append(" where ").append(property).append(" = :value");
            }
            Query query2=session.createQuery(sql2.toString());
            if(value!=null){
                query2.setParameter("value",value);
            }
            total=query2.list().get(0);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return new Object[]{total,list};
    }

    public Integer delete(List<ID> ids) {
        Session session=HibernateUtils.getSessionFactory().openSession();
        Transaction transaction=session.beginTransaction();
        Integer count=0;
        try {
            for (ID id:ids) {
                T t=session.get(this.persistenceClass,id);
                session.delete(t);
                count++;
            }
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            throw e;
        }finally {
            session.close();
        }
        return count;
    }


}
