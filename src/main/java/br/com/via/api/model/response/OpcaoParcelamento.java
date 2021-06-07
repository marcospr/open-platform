
package br.com.via.api.model.response;

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
    "idFormaPagamento",
    "quantidadeParcelas",
    "taxaJurosAoMes",
    "valorParcela",
    "valorTotal"
})
public class OpcaoParcelamento {

    @JsonProperty("idFormaPagamento")
    private Integer idFormaPagamento;
    @JsonProperty("quantidadeParcelas")
    private Integer quantidadeParcelas;
    @JsonProperty("taxaJurosAoMes")
    private Integer taxaJurosAoMes;
    @JsonProperty("valorParcela")
    private Integer valorParcela;
    @JsonProperty("valorTotal")
    private Integer valorTotal;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("idFormaPagamento")
    public Integer getIdFormaPagamento() {
        return idFormaPagamento;
    }

    @JsonProperty("idFormaPagamento")
    public void setIdFormaPagamento(Integer idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    @JsonProperty("quantidadeParcelas")
    public Integer getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    @JsonProperty("quantidadeParcelas")
    public void setQuantidadeParcelas(Integer quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }

    @JsonProperty("taxaJurosAoMes")
    public Integer getTaxaJurosAoMes() {
        return taxaJurosAoMes;
    }

    @JsonProperty("taxaJurosAoMes")
    public void setTaxaJurosAoMes(Integer taxaJurosAoMes) {
        this.taxaJurosAoMes = taxaJurosAoMes;
    }

    @JsonProperty("valorParcela")
    public Integer getValorParcela() {
        return valorParcela;
    }

    @JsonProperty("valorParcela")
    public void setValorParcela(Integer valorParcela) {
        this.valorParcela = valorParcela;
    }

    @JsonProperty("valorTotal")
    public Integer getValorTotal() {
        return valorTotal;
    }

    @JsonProperty("valorTotal")
    public void setValorTotal(Integer valorTotal) {
        this.valorTotal = valorTotal;
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
        result = ((result* 31)+((this.valorParcela == null)? 0 :this.valorParcela.hashCode()));
        result = ((result* 31)+((this.valorTotal == null)? 0 :this.valorTotal.hashCode()));
        result = ((result* 31)+((this.taxaJurosAoMes == null)? 0 :this.taxaJurosAoMes.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.quantidadeParcelas == null)? 0 :this.quantidadeParcelas.hashCode()));
        result = ((result* 31)+((this.idFormaPagamento == null)? 0 :this.idFormaPagamento.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OpcaoParcelamento) == false) {
            return false;
        }
        OpcaoParcelamento rhs = ((OpcaoParcelamento) other);
        return (((((((this.valorParcela == rhs.valorParcela)||((this.valorParcela!= null)&&this.valorParcela.equals(rhs.valorParcela)))&&((this.valorTotal == rhs.valorTotal)||((this.valorTotal!= null)&&this.valorTotal.equals(rhs.valorTotal))))&&((this.taxaJurosAoMes == rhs.taxaJurosAoMes)||((this.taxaJurosAoMes!= null)&&this.taxaJurosAoMes.equals(rhs.taxaJurosAoMes))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.quantidadeParcelas == rhs.quantidadeParcelas)||((this.quantidadeParcelas!= null)&&this.quantidadeParcelas.equals(rhs.quantidadeParcelas))))&&((this.idFormaPagamento == rhs.idFormaPagamento)||((this.idFormaPagamento!= null)&&this.idFormaPagamento.equals(rhs.idFormaPagamento))));
    }

}
