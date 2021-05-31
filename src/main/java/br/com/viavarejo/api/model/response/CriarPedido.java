
package br.com.viavarejo.api.model.response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "valorProduto",
    "valorTotalPedido",
    "codigoPedido",
    "pedidoParceiro",
    "idPedidoMktplc",
    "produtos",
    "parametrosExtras",
    "aguardandoConfirmacao",
    "dadosEntrega",
    "dadosPagamentoComplementar"
})
public class CriarPedido {

    @JsonProperty("valorProduto")
    private Integer valorProduto;
    @JsonProperty("valorTotalPedido")
    private Integer valorTotalPedido;
    @JsonProperty("codigoPedido")
    private Integer codigoPedido;
    @JsonProperty("pedidoParceiro")
    private Integer pedidoParceiro;
    @JsonProperty("idPedidoMktplc")
    private String idPedidoMktplc;
    @JsonProperty("produtos")
    private List<Produto> produtos = null;
    @JsonProperty("parametrosExtras")
    private String parametrosExtras;
    @JsonProperty("aguardandoConfirmacao")
    private Boolean aguardandoConfirmacao;
    @JsonProperty("dadosEntrega")
    private DadosEntrega dadosEntrega;
    @JsonProperty("dadosPagamentoComplementar")
    private DadosPagamentoComplementar dadosPagamentoComplementar;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("valorProduto")
    public Integer getValorProduto() {
        return valorProduto;
    }

    @JsonProperty("valorProduto")
    public void setValorProduto(Integer valorProduto) {
        this.valorProduto = valorProduto;
    }

    @JsonProperty("valorTotalPedido")
    public Integer getValorTotalPedido() {
        return valorTotalPedido;
    }

    @JsonProperty("valorTotalPedido")
    public void setValorTotalPedido(Integer valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }

    @JsonProperty("codigoPedido")
    public Integer getCodigoPedido() {
        return codigoPedido;
    }

    @JsonProperty("codigoPedido")
    public void setCodigoPedido(Integer codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    @JsonProperty("pedidoParceiro")
    public Integer getPedidoParceiro() {
        return pedidoParceiro;
    }

    @JsonProperty("pedidoParceiro")
    public void setPedidoParceiro(Integer pedidoParceiro) {
        this.pedidoParceiro = pedidoParceiro;
    }

    @JsonProperty("idPedidoMktplc")
    public String getIdPedidoMktplc() {
        return idPedidoMktplc;
    }

    @JsonProperty("idPedidoMktplc")
    public void setIdPedidoMktplc(String idPedidoMktplc) {
        this.idPedidoMktplc = idPedidoMktplc;
    }

    @JsonProperty("produtos")
    public List<Produto> getProdutos() {
        return produtos;
    }

    @JsonProperty("produtos")
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @JsonProperty("parametrosExtras")
    public String getParametrosExtras() {
        return parametrosExtras;
    }

    @JsonProperty("parametrosExtras")
    public void setParametrosExtras(String parametrosExtras) {
        this.parametrosExtras = parametrosExtras;
    }

    @JsonProperty("aguardandoConfirmacao")
    public Boolean getAguardandoConfirmacao() {
        return aguardandoConfirmacao;
    }

    @JsonProperty("aguardandoConfirmacao")
    public void setAguardandoConfirmacao(Boolean aguardandoConfirmacao) {
        this.aguardandoConfirmacao = aguardandoConfirmacao;
    }

    @JsonProperty("dadosEntrega")
    public DadosEntrega getDadosEntrega() {
        return dadosEntrega;
    }

    @JsonProperty("dadosEntrega")
    public void setDadosEntrega(DadosEntrega dadosEntrega) {
        this.dadosEntrega = dadosEntrega;
    }

    @JsonProperty("dadosPagamentoComplementar")
    public DadosPagamentoComplementar getDadosPagamentoComplementar() {
        return dadosPagamentoComplementar;
    }

    @JsonProperty("dadosPagamentoComplementar")
    public void setDadosPagamentoComplementar(DadosPagamentoComplementar dadosPagamentoComplementar) {
        this.dadosPagamentoComplementar = dadosPagamentoComplementar;
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
        result = ((result* 31)+((this.codigoPedido == null)? 0 :this.codigoPedido.hashCode()));
        result = ((result* 31)+((this.dadosPagamentoComplementar == null)? 0 :this.dadosPagamentoComplementar.hashCode()));
        result = ((result* 31)+((this.valorTotalPedido == null)? 0 :this.valorTotalPedido.hashCode()));
        result = ((result* 31)+((this.pedidoParceiro == null)? 0 :this.pedidoParceiro.hashCode()));
        result = ((result* 31)+((this.produtos == null)? 0 :this.produtos.hashCode()));
        result = ((result* 31)+((this.parametrosExtras == null)? 0 :this.parametrosExtras.hashCode()));
        result = ((result* 31)+((this.aguardandoConfirmacao == null)? 0 :this.aguardandoConfirmacao.hashCode()));
        result = ((result* 31)+((this.dadosEntrega == null)? 0 :this.dadosEntrega.hashCode()));
        result = ((result* 31)+((this.valorProduto == null)? 0 :this.valorProduto.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.idPedidoMktplc == null)? 0 :this.idPedidoMktplc.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CriarPedido) == false) {
            return false;
        }
        CriarPedido rhs = ((CriarPedido) other);
        return ((((((((((((this.codigoPedido == rhs.codigoPedido)||((this.codigoPedido!= null)&&this.codigoPedido.equals(rhs.codigoPedido)))&&((this.dadosPagamentoComplementar == rhs.dadosPagamentoComplementar)||((this.dadosPagamentoComplementar!= null)&&this.dadosPagamentoComplementar.equals(rhs.dadosPagamentoComplementar))))&&((this.valorTotalPedido == rhs.valorTotalPedido)||((this.valorTotalPedido!= null)&&this.valorTotalPedido.equals(rhs.valorTotalPedido))))&&((this.pedidoParceiro == rhs.pedidoParceiro)||((this.pedidoParceiro!= null)&&this.pedidoParceiro.equals(rhs.pedidoParceiro))))&&((this.produtos == rhs.produtos)||((this.produtos!= null)&&this.produtos.equals(rhs.produtos))))&&((this.parametrosExtras == rhs.parametrosExtras)||((this.parametrosExtras!= null)&&this.parametrosExtras.equals(rhs.parametrosExtras))))&&((this.aguardandoConfirmacao == rhs.aguardandoConfirmacao)||((this.aguardandoConfirmacao!= null)&&this.aguardandoConfirmacao.equals(rhs.aguardandoConfirmacao))))&&((this.dadosEntrega == rhs.dadosEntrega)||((this.dadosEntrega!= null)&&this.dadosEntrega.equals(rhs.dadosEntrega))))&&((this.valorProduto == rhs.valorProduto)||((this.valorProduto!= null)&&this.valorProduto.equals(rhs.valorProduto))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.idPedidoMktplc == rhs.idPedidoMktplc)||((this.idPedidoMktplc!= null)&&this.idPedidoMktplc.equals(rhs.idPedidoMktplc))));
    }

}
