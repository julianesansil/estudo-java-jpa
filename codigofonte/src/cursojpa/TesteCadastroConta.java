package cursojpa;

import javax.persistence.EntityManager;

public class TesteCadastroConta {
	public static void main(String[] args) {
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("controlefinancas");
//		EntityManager em = emf.createEntityManager();
		
		EntityManager em = JPAUtil.getEntityManager();
		
		Conta conta = new Conta();
		conta.setTitular("Juliane");
		conta.setSaldo(500);

		ContaDAOAntigo contaDAO = new ContaDAOAntigo(em);
		
		em.getTransaction().begin();

		contaDAO.cadastrar(conta);
		em.persist(conta);
		System.out.println("Cadastra " + conta.getTitular() + " com id " + conta.getId() + "\n");

		System.out.println("Consulta " + contaDAO.consultar(2).getTitular() + " que tem id " + contaDAO.consultar(2).getId());
		System.out.println("Consulta " + contaDAO.consultar(3).getTitular() + " que tem id " + contaDAO.consultar(3).getId());
		
		System.out.println("\nExclui " + conta.getTitular() + " com id " + contaDAO.consultar(2).getId());
		contaDAO.excluir(contaDAO.consultar(2));
		
		System.out.println("\nLista...");
		for (Conta conta_obj : contaDAO.listar()) {
			System.out.println(conta_obj.getId() + ". " + conta_obj.getTitular());
		}
		
		em.getTransaction().commit();
		em.close();
		
//		System.out.println("Código: " + conta.getId());
	}
}
