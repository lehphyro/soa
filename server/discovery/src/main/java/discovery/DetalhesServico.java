package discovery;

import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("detalhes")
class DetalhesServico {

    private final String descricao;

    public DetalhesServico() {
        this("Sem descricao");
    }

    public DetalhesServico(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
