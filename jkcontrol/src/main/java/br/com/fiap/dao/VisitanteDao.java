package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Visitante;

public class VisitanteDao {

	EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("jkcontrol-persistence-unit");
	EntityManager manager = factory.createEntityManager();
	//private EntityManager manager;
	
	public void create(Visitante user) {
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		
		manager.clear();
		
	}
	
	public List<Visitante> listAll(Visitante user) {
		String jpql = "SELECT u FROM Visitante u";
		TypedQuery<Visitante> query = manager.createQuery(jpql , Visitante.class);
		return query.getResultList();
	}
	
	public List<Visitante> list(Visitante user) {
		String jpql = "SELECT u FROM Visitante u WHERE email=:email";
		TypedQuery<Visitante> query = manager.createQuery(jpql , Visitante.class);
		query.setParameter("email", user.getEmail());
		return query.getResultList();
	}


	public Visitante exist(Visitante user) {
		String jpql = "SELECT u FROM Visitante u WHERE email=:email AND password=:password";
		TypedQuery<Visitante> query = manager.createQuery(jpql , Visitante.class);
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());
		
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
		
	}

}
