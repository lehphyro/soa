package gp.discovery;

public class NenhumaInstanciaEncontradaException extends Exception {

	private static final long serialVersionUID = 8901841123626885388L;

	private final String nomeComVersao;

	public NenhumaInstanciaEncontradaException(String nomeComVersao) {
		this(nomeComVersao, null);
	}

	public NenhumaInstanciaEncontradaException(String nomeComVersao, Exception causa) {
		super(String.format("Nenhuma instancia encontrada do servico [%s]", nomeComVersao), causa);
		this.nomeComVersao = nomeComVersao;
	}

	public String getNomeComVersao() {
		return nomeComVersao;
	}
}
