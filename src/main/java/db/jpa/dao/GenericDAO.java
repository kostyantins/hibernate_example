package db.jpa.dao;


import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import java.io.Serializable;
import java.util.List;

public class GenericDAO<E> {

    private final Logger LOGGER = Logger.getLogger(this.getClass());

    private SessionFactory sessionFactory = JPAconfiguration.getInstance().getSessionFactory();
    private Session session = null;
    private Transaction transaction = null;
    private final Class<E> entityClass;

    public GenericDAO(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public E getEntityById(final Serializable id) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            E result = (E) session.get(entityClass, id);
            transaction.commit();
            return result;
        } catch (HibernateException e) {
            LOGGER.warn("Some problem with finding entity\n");
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public void addEntity(E entity) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.warn("Some problem with adding entity\n");
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void addMergeEntity(E entity) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.warn("Some problem with adding entity\n");
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Serializable saveEntity(E entity) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Serializable result = session.save(entity);
            transaction.commit();
            return result;
        } catch (HibernateException e) {
            LOGGER.warn("Some problem with save entity\n");
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public void updateEntity(E entity) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.warn("Some problem with update entity\n");
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteEntity(E entity) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.warn("Some problem with delete entity\n");
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void removeEntity(E entity) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.warn("Some problem with remove entity\n");
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<E> getAll() {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            List<E> listOfEntityTable = session.createCriteria(entityClass).list();
            transaction.commit();
            return listOfEntityTable;
        } catch (HibernateException e) {
            LOGGER.warn("Some problem with getting all entity\n");
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public void deleteAllEntity() {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            List<E> listOfEntityTable = session.createCriteria(entityClass).list();
            for (E element : listOfEntityTable) {
                session.delete(element);
            }
            transaction.commit();
        } catch (HibernateException e) {
            LOGGER.warn("Some problem with delete all entity\n");
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public int truncateEntities() {
        int numberOfEntity = 0;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            List<E> listOfEntityTable = session.createCriteria(entityClass).list();
            for (E element : listOfEntityTable) {
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaDelete<E> query = builder.createCriteriaDelete(entityClass);
                query.from(entityClass);
                numberOfEntity = session.createQuery(query).executeUpdate();
            }
            transaction.commit();
            return numberOfEntity;
        } catch (HibernateException e) {
            LOGGER.warn("Some problem with truncate entity\n");
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }finally {
            if (session != null){
                session.close();
            }
        }
        return 0;
    }
}
