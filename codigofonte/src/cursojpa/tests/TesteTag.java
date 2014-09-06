package cursojpa.tests;

import java.util.Calendar;

import javax.persistence.EntityManager;

import cursojpa.JPAUtil;
import cursojpa.model.Movimentacao;
import cursojpa.model.Tag;
import cursojpa.model.TipoMovimentacao;
import cursojpa.model.dao.MovimentacaoDAO;
import cursojpa.model.dao.TagDAO;

public class TesteTag {
	public static void main(String[] args) {
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
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

		movimentacao = em.find(Movimentacao.class, new Long(18));
		
		System.out.println("pesquisa...");
		for (Tag tagObj : movimentacao.getTags())
			System.out.println(tagObj.toString());
		
		em.getTransaction().commit();
		em.close();
	}
	
}
