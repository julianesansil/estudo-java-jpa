package cursojpa.model;

public class ValorPorMes {
	private int mes;
	private double valor;
	
	public ValorPorMes(int mes, double valor) {
		this.mes = mes;
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "\nValorPorMes [mes=" + mes + ", valor=" + valor + "]";
	}
}
