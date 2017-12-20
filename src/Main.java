import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static final String IMPLIES = "implies";
	public static final String NOT = "not";
	
	static List<String> premissas = new ArrayList<String>();;
	static String conclusao = "";
	
	public static void carregarBase1() {
		String premissa1 = "not C";
		String premissa2 = "not C implies M";
		String premissa3 = "M implies E";
		
		premissas.add(premissa1);
		premissas.add(premissa2);
		premissas.add(premissa3);
		
		conclusao = "E";
	}
	
	public static void carregarBase2() {
		String premissa1 = "A";
		String premissa2 = "A implies B";
		
		premissas.add(premissa1);
		premissas.add(premissa2);
		
		conclusao = "B";
	}
	
	public static void carregarBase3() {
		String premissa1 = "not B";
		String premissa2 = "not B implies C";
		
		premissas.add(premissa1);
		premissas.add(premissa2);
		
		conclusao = "C";
	}

	
	public static void main(String[] args) {
		
		//Para testar o programa basta carregar cada base de dados uma por vez.
		
		//carregarBase1();
		
		//carregarBase2();
		
		carregarBase3();
		
		
		InferenceEngine inference = new InferenceEngine(premissas,conclusao);
		
		inference.conclusaoNasPremissas();
		inference.verificarAplicacaoRegras();
		
		
	}

}