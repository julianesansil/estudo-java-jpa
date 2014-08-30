package cursojpa;

import javax.persistence.EntityManager;

import cursojpa.model.Conta;
import cursojpa.model.dao.ContaDAO;

public class TesteConta {
	public static void main(String[] args) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("controlefinancas");
//		EntityManager em = emf.createEntityManager();
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();

		ContaDAO contaDAO = new ContaDAO(em);
		Conta conta = new Conta();
		conta.setTitular("Juliane");
		conta.setSaldo(500);

		contaDAO.cadastrar(conta);
		System.out.println("Cadastra " + conta.getTitular() + " com id " + conta.getId() + "\n");

//		System.out.println("Consulta " + contaDAO.consultar(2).getTitular() + " que tem id " + contaDAO.consultar(2).getId());
		System.out.println("Consulta " + contaDAO.consultar(3).getTitular() + " que tem id " + contaDAO.consultar(3).getId());
		
//		System.out.println("\nExclui " + conta.getTitular() + " com id " + contaDAO.consultar(2).getId());
//		contaDAO.excluir(contaDAO.consultar(2));
		
		System.out.println("\nLista...");
		for (Conta conta_obj : contaDAO.listar()) {
			System.out.println(conta_obj.getId() + ". " + conta_obj.getTitular());
		}
		
		em.getTransaction().commit();
		em.close();
	}

}
