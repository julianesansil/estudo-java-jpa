package cursojpa.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cursojpa.model.Conta;
import cursojpa.model.Movimentacao;
import cursojpa.model.Movimentacao_;
import cursojpa.model.TipoMovimentacao;
import cursojpa.model.ValorPorMes;

public class MovimentacaoDAO {
	private final GenericDAO<Movimentacao> dao;
	private final EntityManager em;
	
	public MovimentacaoDAO(EntityManager em) {
		this.dao = new GenericDAO<Movimentacao>(em, Movimentacao.class);
		this.em = em;
	}

	public void cadastrar(Movimentacao t) {
		dao.cadastrar(t);
	}

	public Movimentacao consultar(long id) {
		return dao.consultar(id);
	}

	public boolean equals(Object obj) {
		return dao.equals(obj);
	}

	public void excluir(Movimentacao t) {
		dao.excluir(t);
	}

	public List<Movimentacao> listar() {
		return dao.listar();
	}
	
	public List<Movimentacao> filtrar(Conta conta) {
//		String teste = "1 or 1 = 1";
//		String jpql = "SELECT t1 FROM Movimentacao t1 WHERE t1.conta = " + teste;
		String jpql = "SELECT t1 FROM Movimentacao t1 WHERE t1.conta = :pConta";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		
		return query.getResultList();
	}
	
	public List<Movimentacao> filtrar(Conta conta, TipoMovimentacao tipo) {
		String jpql = "SELECT t1 from Movimentacao t1 WHERE t1.conta = :pConta AND t1.tipo = :pTipo";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", tipo);
		
		return query.getResultList();
	}

	public List<Movimentacao> filtrarComCriteria(Conta conta, TipoMovimentacao tipo) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Movimentacao> criterio = cb.createQuery(Movimentacao.class);
		Root<Movimentacao> root = criterio.from(Movimentacao.class);
		
		Predicate condicao = cb.conjunction();
		if (conta != null)
			condicao = cb.and(condicao, cb.equal(root.get(Movimentacao_.conta), conta));
			
		if (tipo != null)
			condicao = cb.and(condicao, cb.equal(root.get(Movimentacao_.tipo), tipo));
		
		criterio.where(condicao);
		
		TypedQuery<Movimentacao> query = em.createQuery(criterio);
		List<Movimentacao> movimentacoes = query.getResultList();
		
		return movimentacoes;
	}
	
	public List<Movimentacao> filtrarPorMesComCriteria(Conta conta, TipoMovimentacao tipo, Integer mes) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Movimentacao> criterio = cb.createQuery(Movimentacao.class);
		Root<Movimentacao> root = criterio.from(Movimentacao.class);
		
		Predicate condicao = cb.conjunction();
		if (conta != null)
			condicao = cb.and(condicao, cb.equal(root.get(Movimentacao_.conta), conta));
		
		if (mes != null) {
			Expression<Integer> expression = cb.function("MONTH", Integer.class, root.get(Movimentacao_.data));
			condicao = cb.and(condicao, cb.equal(expression, mes));
		}
		
		if (tipo != null)
			condicao = cb.and(condicao, cb.equal(root.get(Movimentacao_.tipo), tipo));
		
		criterio.where(condicao);
		
		TypedQuery<Movimentacao> query = em.createQuery(criterio);
		List<Movimentacao> movimentacoes = query.getResultList();
		
		return movimentacoes;
	}
	
	public double calcularTotalMovimento(Conta conta, TipoMovimentacao tipo) {
		String jpql = "SELECT SUM(t1.valor) FROM Movimentacao t1"
				+ " WHERE t1.conta = :pConta AND t1.tipo = :pTipo";
//		Query query = em.createQuery(jpql);
		
		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", tipo);
				
		return query.getSingleResult();
	}

	public List<Movimentacao> listarMovimentacaoPorTitular(String titular) {
		String jpql = "SELECT m FROM Movimentacao m WHERE m.conta.titular = :pTitular";
		
		TypedQuery<Movimentacao> query = em.createQuery(jpql, Movimentacao.class);
		query.setParameter("pTitular", titular);
		
		return query.getResultList();
	}
	
	public List<ValorPorMes> listarMesesComMovimentacao(Conta conta, TipoMovimentacao tipo) {
		String jpql = "SELECT new cursojpa.model.ValorPorMes(MONTH(m.data), SUM(m.valor)) "
					+ "FROM Movimentacao m "
					+ "GROUP BY MONTH(m.data), m.conta, m.tipo "
					+ "HAVING m.conta = :pConta AND m.tipo = :pTipo "
					+ "ORDER BY SUM(m.valor) DESC";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", tipo);
		
		return query.getResultList();
	}
	
}
