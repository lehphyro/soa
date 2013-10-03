package gp.discovery.zookeeper;

public class Nomeador {

	private Nomeador() {
	}

	public static String montarNomeComVersao(String nome, String versao) {
		return nome + "/" + versao;
	}
}
