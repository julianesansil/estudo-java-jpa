package cursojpa.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import cursojpa.model.Conta;

public class ContaDAOAntigo {
	private EntityManager em;

	//injeção de dependência
	public ContaDAOAntigo(EntityManager em) {
		this.em = em;
	}

	public void cadastrar(Conta conta) {
//		EntityManager em = JPAUtil.getEntityManager();
//		em.getTransaction().begin();
		em.persist(conta);
//		em.getTransaction().commit();
//		em.close();
	}
	
	public void excluir (Conta conta) {
		em.remove(conta);
	}

	public Conta consultar(long id) {
		return em.getReference(Conta.class, id);
	}
	
	public List<Conta> listar() {
		String jpql = "SELECT c FROM Conta c";
		Query query = em.createQuery(jpql);
		return query.getResultList();
	}
	
}
