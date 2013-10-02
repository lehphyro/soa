package gp.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@JsonPropertyOrder({ "id", "nome", "cobrancas", "contatos", "endereco" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Gp {

    @JsonProperty
    private final long id;

    @JsonProperty
    @NotEmpty
    private final String nome;

    @JsonProperty
    private final List<Contato> contatos;

    @JsonProperty
    private final List<WebsitePessoal> websitesPessoais;

    @JsonProperty
    private final List<WebsiteDivulgacao> websitesDivulgacao;

    @JsonProperty
    private final List<WebsiteForum> foruns;

    @JsonProperty
    private final List<Cobranca> cobrancas;

    @JsonProperty
    private final Endereco endereco;

    public Gp(@JsonProperty("nome") String nome) {
        this(new Builder().nome(nome));
    }

    private Gp(Builder builder) {
        id = builder.id;
        nome = builder.nome;
        contatos = ImmutableList.copyOf(builder.contatos);
        websitesPessoais = ImmutableList.copyOf(builder.websitesPessoais);
        websitesDivulgacao = ImmutableList.copyOf(builder.websitesDivulgacao);
        foruns = ImmutableList.copyOf(builder.foruns);
        cobrancas = ImmutableList.copyOf(builder.cobrancas);
        endereco = builder.endereco;
    }

    public static class Builder {
        long id;
        String nome;
        List<Contato> contatos = new ArrayList<>();
        List<WebsitePessoal> websitesPessoais = new ArrayList<>();
        List<WebsiteDivulgacao> websitesDivulgacao = new ArrayList<>();
        List<WebsiteForum> foruns = new ArrayList<>();
        List<Cobranca> cobrancas = new ArrayList<>();
        Endereco endereco;

        public Builder() {
        }

        public Builder(Gp gp) {
            id = gp.id;
            nome = gp.nome;
            contatos = new ArrayList<>(gp.contatos);
            websitesPessoais = new ArrayList<>(gp.websitesPessoais);
            websitesDivulgacao = new ArrayList<>(gp.websitesDivulgacao);
            foruns = new ArrayList<>(gp.foruns);
            cobrancas = new ArrayList<>(gp.cobrancas);
            endereco = gp.endereco;
        }

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder addContato(Contato v) {
            this.contatos.add(v);
            return this;
        }

        public Builder addWebsitePessoal(WebsitePessoal v) {
            this.websitesPessoais.add(v);
            return this;
        }

        public Builder addWebsiteDivulgacao(WebsiteDivulgacao v) {
            this.websitesDivulgacao.add(v);
            return this;
        }

        public Builder addForum(WebsiteForum v) {
            this.foruns.add(v);
            return this;
        }

        public Builder cobrancas(Collection<? extends Cobranca> v) {
            this.cobrancas.addAll(v);
            return this;
        }

        public Builder contatos(Collection<? extends Contato> v) {
            this.contatos.addAll(v);
            return this;
        }

        public Builder endereco(Endereco endereco) {
            this.endereco = endereco;
            return this;
        }

        public Builder websitesDivulgacao(Collection<? extends WebsiteDivulgacao> v) {
            this.websitesDivulgacao.addAll(v);
            return this;
        }

        public Builder websitesForum(Collection<? extends WebsiteForum> v) {
            this.foruns.addAll(v);
            return this;
        }

        public Builder websitesPessoais(Collection<? extends WebsitePessoal> v) {
            this.websitesPessoais.addAll(v);
            return this;
        }

        public Gp build() {
            return new Gp(this);
        }
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public List<WebsitePessoal> getWebsitesPessoais() {
        return websitesPessoais;
    }

    public List<WebsiteDivulgacao> getWebsitesDivulgacao() {
        return websitesDivulgacao;
    }

    public List<WebsiteForum> getForuns() {
        return foruns;
    }

    public List<Cobranca> getCobrancas() {
        return cobrancas;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("nome", nome)
                .add("contatos", contatos)
                .add("websitesPessoais", websitesPessoais)
                .add("websitesDivulgacao", websitesDivulgacao)
                .add("foruns", foruns)
                .add("cobrancas", cobrancas)
                .add("endereco", endereco)
                .toString();
    }
}
