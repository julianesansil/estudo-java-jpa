package cursojpa;

import java.util.List;

import javax.persistence.EntityManager;

public class ContaDAO {
	private GenericDAO<Conta> dao;

	public ContaDAO(EntityManager em) {
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
	
}
