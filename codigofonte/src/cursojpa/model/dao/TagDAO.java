package cursojpa.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import cursojpa.model.Tag;

public class TagDAO {
	private final GenericDAO<Tag> dao;
	private final EntityManager em;
	
	public TagDAO(EntityManager em) {
		this.em = em;
		this.dao = new GenericDAO<Tag>(em, Tag.class);
	}

	public void cadastrar(Tag t) {
		dao.cadastrar(t);
	}

	public Tag cadastrarOuBuscar(String nome) {
		String jpql = "SELECT t FROM Tag t WHERE t.nome = :pNome";
	
		TypedQuery<Tag> query = em.createQuery(jpql, Tag.class);
		query.setParameter("pNome", nome);
		
		Tag tag = null;
		try {
			tag = query.getSingleResult();
		} catch (NoResultException e) {
			tag = new Tag();
			tag.setNome(nome);
			dao.cadastrar(tag);
		}
		
		return tag;
	}
}
