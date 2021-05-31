
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
    "codigoEntrega",
    "previsaoEntrega",
    "dataEntrega",
    "dataPrevisao",
    "dataEmissaoNotaFiscal",
    "idNotaFiscal",
    "serieNotaFiscal",
    "chaveAcesso",
    "trackingEntrega",
    "produtoEntrega",
    "rastreioEntrega",
    "nomeTransportadora",
    "linkNotaFiscalPDF",
    "listNotaFiscalXML",
    "estorno",
    "origem",
    "motivo"
})
public class Entrega {

    @JsonProperty("codigoEntrega")
    private Integer codigoEntrega;
    @JsonProperty("previsaoEntrega")
    private String previsaoEntrega;
    @JsonProperty("dataEntrega")
    private String dataEntrega;
    @JsonProperty("dataPrevisao")
    private String dataPrevisao;
    @JsonProperty("dataEmissaoNotaFiscal")
    private String dataEmissaoNotaFiscal;
    @JsonProperty("idNotaFiscal")
    private Integer idNotaFiscal;
    @JsonProperty("serieNotaFiscal")
    private String serieNotaFiscal;
    @JsonProperty("chaveAcesso")
    private String chaveAcesso;
    @JsonProperty("trackingEntrega")
    private List<TrackingEntrega> trackingEntrega = null;
    @JsonProperty("produtoEntrega")
    private List<ProdutoEntrega> produtoEntrega = null;
    @JsonProperty("rastreioEntrega")
    private String rastreioEntrega;
    @JsonProperty("nomeTransportadora")
    private String nomeTransportadora;
    @JsonProperty("linkNotaFiscalPDF")
    private String linkNotaFiscalPDF;
    @JsonProperty("listNotaFiscalXML")
    private String listNotaFiscalXML;
    @JsonProperty("estorno")
    private Boolean estorno;
    @JsonProperty("origem")
    private String origem;
    @JsonProperty("motivo")
    private Motivo motivo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("codigoEntrega")
    public Integer getCodigoEntrega() {
        return codigoEntrega;
    }

    @JsonProperty("codigoEntrega")
    public void setCodigoEntrega(Integer codigoEntrega) {
        this.codigoEntrega = codigoEntrega;
    }

    @JsonProperty("previsaoEntrega")
    public String getPrevisaoEntrega() {
        return previsaoEntrega;
    }

    @JsonProperty("previsaoEntrega")
    public void setPrevisaoEntrega(String previsaoEntrega) {
        this.previsaoEntrega = previsaoEntrega;
    }

    @JsonProperty("dataEntrega")
    public String getDataEntrega() {
        return dataEntrega;
    }

    @JsonProperty("dataEntrega")
    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    @JsonProperty("dataPrevisao")
    public String getDataPrevisao() {
        return dataPrevisao;
    }

    @JsonProperty("dataPrevisao")
    public void setDataPrevisao(String dataPrevisao) {
        this.dataPrevisao = dataPrevisao;
    }

    @JsonProperty("dataEmissaoNotaFiscal")
    public String getDataEmissaoNotaFiscal() {
        return dataEmissaoNotaFiscal;
    }

    @JsonProperty("dataEmissaoNotaFiscal")
    public void setDataEmissaoNotaFiscal(String dataEmissaoNotaFiscal) {
        this.dataEmissaoNotaFiscal = dataEmissaoNotaFiscal;
    }

    @JsonProperty("idNotaFiscal")
    public Integer getIdNotaFiscal() {
        return idNotaFiscal;
    }

    @JsonProperty("idNotaFiscal")
    public void setIdNotaFiscal(Integer idNotaFiscal) {
        this.idNotaFiscal = idNotaFiscal;
    }

    @JsonProperty("serieNotaFiscal")
    public String getSerieNotaFiscal() {
        return serieNotaFiscal;
    }

    @JsonProperty("serieNotaFiscal")
    public void setSerieNotaFiscal(String serieNotaFiscal) {
        this.serieNotaFiscal = serieNotaFiscal;
    }

    @JsonProperty("chaveAcesso")
    public String getChaveAcesso() {
        return chaveAcesso;
    }

    @JsonProperty("chaveAcesso")
    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    @JsonProperty("trackingEntrega")
    public List<TrackingEntrega> getTrackingEntrega() {
        return trackingEntrega;
    }

    @JsonProperty("trackingEntrega")
    public void setTrackingEntrega(List<TrackingEntrega> trackingEntrega) {
        this.trackingEntrega = trackingEntrega;
    }

    @JsonProperty("produtoEntrega")
    public List<ProdutoEntrega> getProdutoEntrega() {
        return produtoEntrega;
    }

    @JsonProperty("produtoEntrega")
    public void setProdutoEntrega(List<ProdutoEntrega> produtoEntrega) {
        this.produtoEntrega = produtoEntrega;
    }

    @JsonProperty("rastreioEntrega")
    public String getRastreioEntrega() {
        return rastreioEntrega;
    }

    @JsonProperty("rastreioEntrega")
    public void setRastreioEntrega(String rastreioEntrega) {
        this.rastreioEntrega = rastreioEntrega;
    }

    @JsonProperty("nomeTransportadora")
    public String getNomeTransportadora() {
        return nomeTransportadora;
    }

    @JsonProperty("nomeTransportadora")
    public void setNomeTransportadora(String nomeTransportadora) {
        this.nomeTransportadora = nomeTransportadora;
    }

    @JsonProperty("linkNotaFiscalPDF")
    public String getLinkNotaFiscalPDF() {
        return linkNotaFiscalPDF;
    }

    @JsonProperty("linkNotaFiscalPDF")
    public void setLinkNotaFiscalPDF(String linkNotaFiscalPDF) {
        this.linkNotaFiscalPDF = linkNotaFiscalPDF;
    }

    @JsonProperty("listNotaFiscalXML")
    public String getListNotaFiscalXML() {
        return listNotaFiscalXML;
    }

    @JsonProperty("listNotaFiscalXML")
    public void setListNotaFiscalXML(String listNotaFiscalXML) {
        this.listNotaFiscalXML = listNotaFiscalXML;
    }

    @JsonProperty("estorno")
    public Boolean getEstorno() {
        return estorno;
    }

    @JsonProperty("estorno")
    public void setEstorno(Boolean estorno) {
        this.estorno = estorno;
    }

    @JsonProperty("origem")
    public String getOrigem() {
        return origem;
    }

    @JsonProperty("origem")
    public void setOrigem(String origem) {
        this.origem = origem;
    }

    @JsonProperty("motivo")
    public Motivo getMotivo() {
        return motivo;
    }

    @JsonProperty("motivo")
    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
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
        result = ((result* 31)+((this.previsaoEntrega == null)? 0 :this.previsaoEntrega.hashCode()));
        result = ((result* 31)+((this.listNotaFiscalXML == null)? 0 :this.listNotaFiscalXML.hashCode()));
        result = ((result* 31)+((this.motivo == null)? 0 :this.motivo.hashCode()));
        result = ((result* 31)+((this.estorno == null)? 0 :this.estorno.hashCode()));
        result = ((result* 31)+((this.origem == null)? 0 :this.origem.hashCode()));
        result = ((result* 31)+((this.serieNotaFiscal == null)? 0 :this.serieNotaFiscal.hashCode()));
        result = ((result* 31)+((this.nomeTransportadora == null)? 0 :this.nomeTransportadora.hashCode()));
        result = ((result* 31)+((this.produtoEntrega == null)? 0 :this.produtoEntrega.hashCode()));
        result = ((result* 31)+((this.codigoEntrega == null)? 0 :this.codigoEntrega.hashCode()));
        result = ((result* 31)+((this.idNotaFiscal == null)? 0 :this.idNotaFiscal.hashCode()));
        result = ((result* 31)+((this.chaveAcesso == null)? 0 :this.chaveAcesso.hashCode()));
        result = ((result* 31)+((this.rastreioEntrega == null)? 0 :this.rastreioEntrega.hashCode()));
        result = ((result* 31)+((this.dataEntrega == null)? 0 :this.dataEntrega.hashCode()));
        result = ((result* 31)+((this.dataEmissaoNotaFiscal == null)? 0 :this.dataEmissaoNotaFiscal.hashCode()));
        result = ((result* 31)+((this.dataPrevisao == null)? 0 :this.dataPrevisao.hashCode()));
        result = ((result* 31)+((this.trackingEntrega == null)? 0 :this.trackingEntrega.hashCode()));
        result = ((result* 31)+((this.linkNotaFiscalPDF == null)? 0 :this.linkNotaFiscalPDF.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Entrega) == false) {
            return false;
        }
        Entrega rhs = ((Entrega) other);
        return (((((((((((((((((((this.previsaoEntrega == rhs.previsaoEntrega)||((this.previsaoEntrega!= null)&&this.previsaoEntrega.equals(rhs.previsaoEntrega)))&&((this.listNotaFiscalXML == rhs.listNotaFiscalXML)||((this.listNotaFiscalXML!= null)&&this.listNotaFiscalXML.equals(rhs.listNotaFiscalXML))))&&((this.motivo == rhs.motivo)||((this.motivo!= null)&&this.motivo.equals(rhs.motivo))))&&((this.estorno == rhs.estorno)||((this.estorno!= null)&&this.estorno.equals(rhs.estorno))))&&((this.origem == rhs.origem)||((this.origem!= null)&&this.origem.equals(rhs.origem))))&&((this.serieNotaFiscal == rhs.serieNotaFiscal)||((this.serieNotaFiscal!= null)&&this.serieNotaFiscal.equals(rhs.serieNotaFiscal))))&&((this.nomeTransportadora == rhs.nomeTransportadora)||((this.nomeTransportadora!= null)&&this.nomeTransportadora.equals(rhs.nomeTransportadora))))&&((this.produtoEntrega == rhs.produtoEntrega)||((this.produtoEntrega!= null)&&this.produtoEntrega.equals(rhs.produtoEntrega))))&&((this.codigoEntrega == rhs.codigoEntrega)||((this.codigoEntrega!= null)&&this.codigoEntrega.equals(rhs.codigoEntrega))))&&((this.idNotaFiscal == rhs.idNotaFiscal)||((this.idNotaFiscal!= null)&&this.idNotaFiscal.equals(rhs.idNotaFiscal))))&&((this.chaveAcesso == rhs.chaveAcesso)||((this.chaveAcesso!= null)&&this.chaveAcesso.equals(rhs.chaveAcesso))))&&((this.rastreioEntrega == rhs.rastreioEntrega)||((this.rastreioEntrega!= null)&&this.rastreioEntrega.equals(rhs.rastreioEntrega))))&&((this.dataEntrega == rhs.dataEntrega)||((this.dataEntrega!= null)&&this.dataEntrega.equals(rhs.dataEntrega))))&&((this.dataEmissaoNotaFiscal == rhs.dataEmissaoNotaFiscal)||((this.dataEmissaoNotaFiscal!= null)&&this.dataEmissaoNotaFiscal.equals(rhs.dataEmissaoNotaFiscal))))&&((this.dataPrevisao == rhs.dataPrevisao)||((this.dataPrevisao!= null)&&this.dataPrevisao.equals(rhs.dataPrevisao))))&&((this.trackingEntrega == rhs.trackingEntrega)||((this.trackingEntrega!= null)&&this.trackingEntrega.equals(rhs.trackingEntrega))))&&((this.linkNotaFiscalPDF == rhs.linkNotaFiscalPDF)||((this.linkNotaFiscalPDF!= null)&&this.linkNotaFiscalPDF.equals(rhs.linkNotaFiscalPDF))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
