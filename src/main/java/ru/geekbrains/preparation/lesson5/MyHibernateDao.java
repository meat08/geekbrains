package ru.geekbrains.preparation.lesson5;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public abstract class MyHibernateDao<T, ID>{
    //тут @Autowired, но я не стал подключать зависимости, чтобы не перегружать проект
    private SessionFactory sessionFactory;
    private final Class<T> genericType;

    public MyHibernateDao(Class<T> type) {
        this.genericType = type;
    }

    public T create(T entity) {
        getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    public T findByID(ID id) {
        return getCurrentSession().get(genericType, id);
    }

    @SuppressWarnings("rawtypes")
    public List findAll() {
        return getCurrentSession().createQuery("FROM " + genericType.getName()).list();
    }

    @SuppressWarnings("unchecked")
    public T update(T entity) {
        return (T) getCurrentSession().merge(entity);
    }

    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    public void deleteById(ID id) {
        T entity = findByID(id);
        delete(entity);
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

}
