
package br.com.viavarejo.api.model.response;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "codigo",
    "nome",
    "quantidade",
    "valor",
    "frete",
    "valorAdicional",
    "valorTotal",
    "idLojista"
})
public class ProdutoEntrega {

    @JsonProperty("codigo")
    private Integer codigo;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("quantidade")
    private Integer quantidade;
    @JsonProperty("valor")
    private Integer valor;
    @JsonProperty("frete")
    private Integer frete;
    @JsonProperty("valorAdicional")
    private Integer valorAdicional;
    @JsonProperty("valorTotal")
    private Integer valorTotal;
    @JsonProperty("idLojista")
    private Integer idLojista;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("codigo")
    public Integer getCodigo() {
        return codigo;
    }

    @JsonProperty("codigo")
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    @JsonProperty("nome")
    public String getNome() {
        return nome;
    }

    @JsonProperty("nome")
    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonProperty("quantidade")
    public Integer getQuantidade() {
        return quantidade;
    }

    @JsonProperty("quantidade")
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @JsonProperty("valor")
    public Integer getValor() {
        return valor;
    }

    @JsonProperty("valor")
    public void setValor(Integer valor) {
        this.valor = valor;
    }

    @JsonProperty("frete")
    public Integer getFrete() {
        return frete;
    }

    @JsonProperty("frete")
    public void setFrete(Integer frete) {
        this.frete = frete;
    }

    @JsonProperty("valorAdicional")
    public Integer getValorAdicional() {
        return valorAdicional;
    }

    @JsonProperty("valorAdicional")
    public void setValorAdicional(Integer valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    @JsonProperty("valorTotal")
    public Integer getValorTotal() {
        return valorTotal;
    }

    @JsonProperty("valorTotal")
    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
    }

    @JsonProperty("idLojista")
    public Integer getIdLojista() {
        return idLojista;
    }

    @JsonProperty("idLojista")
    public void setIdLojista(Integer idLojista) {
        this.idLojista = idLojista;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.codigo == null)? 0 :this.codigo.hashCode()));
        result = ((result* 31)+((this.idLojista == null)? 0 :this.idLojista.hashCode()));
        result = ((result* 31)+((this.valorTotal == null)? 0 :this.valorTotal.hashCode()));
        result = ((result* 31)+((this.valor == null)? 0 :this.valor.hashCode()));
        result = ((result* 31)+((this.frete == null)? 0 :this.frete.hashCode()));
        result = ((result* 31)+((this.nome == null)? 0 :this.nome.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.quantidade == null)? 0 :this.quantidade.hashCode()));
        result = ((result* 31)+((this.valorAdicional == null)? 0 :this.valorAdicional.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProdutoEntrega) == false) {
            return false;
        }
        ProdutoEntrega rhs = ((ProdutoEntrega) other);
        return ((((((((((this.codigo == rhs.codigo)||((this.codigo!= null)&&this.codigo.equals(rhs.codigo)))&&((this.idLojista == rhs.idLojista)||((this.idLojista!= null)&&this.idLojista.equals(rhs.idLojista))))&&((this.valorTotal == rhs.valorTotal)||((this.valorTotal!= null)&&this.valorTotal.equals(rhs.valorTotal))))&&((this.valor == rhs.valor)||((this.valor!= null)&&this.valor.equals(rhs.valor))))&&((this.frete == rhs.frete)||((this.frete!= null)&&this.frete.equals(rhs.frete))))&&((this.nome == rhs.nome)||((this.nome!= null)&&this.nome.equals(rhs.nome))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.quantidade == rhs.quantidade)||((this.quantidade!= null)&&this.quantidade.equals(rhs.quantidade))))&&((this.valorAdicional == rhs.valorAdicional)||((this.valorAdicional!= null)&&this.valorAdicional.equals(rhs.valorAdicional))));
    }

}
