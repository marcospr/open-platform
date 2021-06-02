
package br.com.viavarejo.api.model.response;

import java.io.Serializable;
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
public class Entrega implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @JsonProperty("codigoEntrega")
		private double codigoEntrega;
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
		public double getCodigoEntrega() {
        return codigoEntrega;
    }

    @JsonProperty("codigoEntrega")
		public void setCodigoEntrega(double codigoEntrega) {
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



}
