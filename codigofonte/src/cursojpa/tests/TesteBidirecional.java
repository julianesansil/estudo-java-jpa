package cursojpa.tests;

import javax.persistence.EntityManager;

import cursojpa.JPAUtil;
import cursojpa.model.Conta;
import cursojpa.model.Movimentacao;
import cursojpa.model.dao.ContaDAO;
import cursojpa.model.dao.MovimentacaoDAO;

public class TesteBidirecional {
	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();

		ContaDAO contaDAO = new ContaDAO(em);
		Conta conta = contaDAO.consultar(1);
		System.out.println(conta.getListaMovimentacao());
				
		MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(em);
		Movimentacao movimentacao = movimentacaoDAO.consultar(6);
		System.out.println(movimentacao.getConta());
		
		em.close();
	}
}
