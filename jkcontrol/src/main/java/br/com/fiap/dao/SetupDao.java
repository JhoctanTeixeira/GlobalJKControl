package br.com.fiap.dao;

import java.util.List;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Setup;
import br.com.fiap.model.Visitante;

public class SetupDao {

	EntityManagerFactory factory = 
			Persistence.createEntityManagerFactory("jkcontrol-persistence-unit");
	EntityManager manager = factory.createEntityManager();
	//private EntityManager manager;
	
	public void create(Setup setup) {
		manager.getTransaction().begin();
		manager.persist(setup);
		manager.getTransaction().commit();
		
		manager.clear();
		
	}
	
	public List<Setup> listAll() {
		String jpql = "SELECT s FROM Setup s WHERE s.user=:user";
		TypedQuery<Setup> query = manager.createQuery(jpql , Setup.class);
		Visitante user = 
				(Visitante) FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getSessionMap()
				.get("user");
		query.setParameter("user", user);
		return query.getResultList();
	}

	public void remove(Setup setup) {
		manager.getTransaction().begin();
		manager.remove(setup);
		manager.getTransaction().commit();
	}

	public void update(Setup setup) {
		manager.getTransaction().begin();
		manager.merge(setup);
		manager.getTransaction().commit();
	}

}
