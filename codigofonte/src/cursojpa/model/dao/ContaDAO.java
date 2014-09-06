package cursojpa.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import cursojpa.model.Conta;
import cursojpa.model.ContaPorGerente;
import cursojpa.model.Gerente;

public class ContaDAO {
	private final EntityManager em;
	private final GenericDAO<Conta> dao;
	
	public ContaDAO(EntityManager em) {
		this.em = em;
		this.dao = new GenericDAO<Conta>(em, Conta.class);
	}
	
	public void cadastrar(Conta conta) {
		dao.cadastrar(conta);
	}

	public void excluir(Conta t) {
		dao.excluir(t);
	}

	public Conta consultar(long id) {
		return dao.consultar(id);
	}

	public List<Conta> listar() {
		return dao.listar();
	}
	
	public Conta consultar(Gerente gerente) {
		String jpql = "SELECT c FROM Conta c WHERE c.gerente = :pGerente";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pGerente", gerente);
	
		return (Conta) query.getSingleResult();
	}
	
	public List<ContaPorGerente> listarContaPorGerente() {
		String jpql = "SELECT new cursojpa.model.ContaPorGerente(c, c.gerente)"
					+ "FROM Conta c"
					+ "GROUP BY c.gerente";

		Query query = em.createQuery(jpql);

		return query.getResultList();
	}
}
