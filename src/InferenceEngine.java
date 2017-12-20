import java.util.List;

public class InferenceEngine {

	private List<String> premissas;
	private String conclusao;
	private int flag = 0;

	public InferenceEngine(List<String> premissas, String conclusao) {
		this.premissas = premissas;
		this.conclusao = conclusao;

		this.listarPremissas();
	}

	public void conclusaoNasPremissas() {

		for (int i = 0; i < premissas.size(); i++) {
			String temp = premissas.get(i);

			if (conclusao.equals(temp)) {
				System.out.println("\nA conclusão é verdadeira devido a premissa " + (i + 1) + ". " + temp);
				flag = 1;
			}
		}
	}

	public void listarPremissas() {

		System.out.println("===============");
		System.out.println("Premissas: ");
		for (int i = 0; i < premissas.size(); i++) {
			String temp = premissas.get(i);
			System.out.println((i + 1) + ". " + temp);
		}
		System.out.println();
		System.out.println("Conclusão: ");
		System.out.println(conclusao);
		System.out.println("===============");

		this.conclusaoNasPremissas();

	}

	public void adicionarPremissa(String novaPremissa) {
		if (!(premissas.contains(novaPremissa))) {
			premissas.add(novaPremissa);
		}
	}

	public void verificarAplicacaoRegras() {

		for (int i = 0; i < premissas.size() && flag == 0; i++) {
			String prem = premissas.get(i);

			String[] itens = prem.split(" ");

			for (int j = 0; j < itens.length; j++) {

				// Se um operador 'implies' for encontrado verifica se dá pra
				// aplicar Modus Ponens com os possíveis fatos da base
				if (itens[j].equals(Main.IMPLIES)) {

					String[] operandos = buscarOperandos(itens, j);
					modusPonens(operandos[0], operandos[1]);

				}
			}
		}
	}

	// Busca operandos de uma premissa qualquer
	public String[] buscarOperandos(String[] itens, int j) {
		String temp = "";

		for (int i = 0; i < j; i++) {
			if (i == j - 1) {
				temp = temp + itens[i];
			} else {
				temp = temp + itens[i] + " ";
			}

		}

		String operando1 = temp;

		temp = "";

		for (int i = j + 1; i < itens.length; i++) {
			if (i == itens.length - 1) {
				temp = temp + itens[i];
			} else {
				temp = temp + itens[i] + " ";
			}

		}

		String operando2 = temp;

		String[] operandos = { operando1, operando2 };

		return operandos;

	}

	/*
	 * Aplicação da regra Modus Ponens:
	 * 
	 * A implies B
	 * A
	 * 
	 * ou, trocando a ordem das premissas:
	 * 
	 * A
	 * A implies B
	 * 
	 * obtemos como conclusão:				
	 * 
	 * Conclusão: B
	 */
	public void modusPonens(String op1, String op2) {

		for (int j = 0; j < premissas.size() && flag == 0; j++) {

			if (premissas.get(j).equals(op1)) {
				System.out.println("Aplicando Modus Ponens com as premissas (" + op1 + " " + Main.IMPLIES + " " + op2
						+ ") e (" + premissas.get(j) + ") temos agora a seguinte base:");
				adicionarPremissa(op2);
				listarPremissas();
			}
		}

	}
}
