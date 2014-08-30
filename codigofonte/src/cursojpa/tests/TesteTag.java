package cursojpa.tests;

import java.util.Calendar;

import javax.persistence.EntityManager;

import cursojpa.JPAUtil;
import cursojpa.model.Conta;
import cursojpa.model.Movimentacao;
import cursojpa.model.Tag;
import cursojpa.model.TipoMovimentacao;
import cursojpa.model.dao.ContaDAO;
import cursojpa.model.dao.MovimentacaoDAO;
import cursojpa.model.dao.TagDAO;

public class TesteTag {
	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();

		ContaDAO contaDAO = new ContaDAO(em);
		Conta conta = new Conta();
		conta.setId(1);
		
		MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO(em);
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setDescricao("movimentacao2");
		movimentacao.setData(Calendar.getInstance());
		movimentacao.setTipo(TipoMovimentacao.SAIDA);

		TagDAO tagDAO = new TagDAO(em);
		Tag tag = tagDAO.cadastrarOuBuscar("casa");
		movimentacao.getTags().add(tag);
		tag = tagDAO.cadastrarOuBuscar("água");
		movimentacao.getTags().add(tag);
		tag = tagDAO.cadastrarOuBuscar("luz");
		movimentacao.getTags().add(tag);
		
		movimentacaoDAO.cadastrar(movimentacao);
		
		em.getTransaction().commit();
		em.close();
	}
}
