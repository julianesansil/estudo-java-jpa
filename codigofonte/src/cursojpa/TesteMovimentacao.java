package cursojpa;

import java.util.Calendar;

import javax.persistence.EntityManager;

import cursojpa.model.Conta;
import cursojpa.model.Movimentacao;
import cursojpa.model.TipoMovimentacao;
import cursojpa.model.dao.ContaDAO;
import cursojpa.model.dao.MovimentacaoDAO;

public class TesteMovimentacao {
	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		ContaDAO contaDAO = new ContaDAO(em);
		Conta conta = new Conta();
		conta.setId(1);

		MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(em);
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setConta(conta);
		movimentacao.setDescricao("movimentacao1");
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setTipo(TipoMovimentacao.ENTRADA);
		
		movimentacaoDAO.cadastrar(movimentacao);
		System.out.println("Cadastra " + movimentacao.getDescricao() + " com id " + movimentacao.getId() + "\n");

//		System.out.println("Consulta " + movimentacaoDAO.consultar(5).getDescricao() + " que tem id " + movimentacaoDAO.consultar(5).getId());
		System.out.println("Consulta " + movimentacaoDAO.consultar(15).getDescricao() + " que tem id " + movimentacaoDAO.consultar(15).getId());
		
//		System.out.println("\nExclui " + movimentacao.getDescricao() + " com id " + movimentacaoDAO.consultar(5).getId());
//		movimentacaoDAO.excluir(movimentacaoDAO.consultar(5));
		
		System.out.println("\nLista...");
		for (Movimentacao movimentacao_obj : movimentacaoDAO.listar()) {
			System.out.println(movimentacao_obj.getId() + ". " + movimentacao_obj.getDescricao());
		}
		
		em.getTransaction().commit();
		em.close();
	}
}
