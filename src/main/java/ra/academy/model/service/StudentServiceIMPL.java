package ra.academy.model.service;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ra.academy.model.entity.Student;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


public class StudentServiceIMPL implements IStudentService {
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;
    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Student> findAll() {
        List<Student> list = new ArrayList<>();
        String hql = "Select S From Student AS S";
        TypedQuery<Student> query = entityManager.createQuery(hql, Student.class);
        list = query.getResultList();
        System.out.println(list);
        return  list;
    }

    @Override
    public Student findById(Long idSearch) {
        String hql ="Select s from Student as s where s.id= :id";
        TypedQuery<Student> query = entityManager.createQuery(hql, Student.class);
        query.setParameter("id",idSearch);
        return query.getSingleResult();
    }

    @Override
    public void deleteById(Long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(findById(id));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.isActive();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(Student s) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Student student = findById(s.getId());
            student.cloneStudent(s);
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.isActive();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void save(Student s) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(s);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.isActive();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }
}
