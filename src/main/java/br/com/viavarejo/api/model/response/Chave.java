
package br.com.viavarejo.api.model.response;

import java.util.HashMap;
import java.util.Map;

import javax.xml.crypto.Data;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "chavePublica",
    "dataCadastro",
    "dataExpiracao",
    "dataAtualizacao",
    "ativo"
})
public class Chave {

    @JsonProperty("chavePublica")
    private String chavePublica;
    @JsonProperty("dataCadastro")
    private String dataCadastro;
    @JsonProperty("dataExpiracao")
    private String dataExpiracao;
    @JsonProperty("dataAtualizacao")
    private String dataAtualizacao;
    @JsonProperty("ativo")
    private Boolean ativo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("chavePublica")
    public String getChavePublica() {
        return chavePublica;
    }

    @JsonProperty("chavePublica")
    public void setChavePublica(String chavePublica) {
        this.chavePublica = chavePublica;
    }

    @JsonProperty("dataCadastro")
    public String getDataCadastro() {
        return dataCadastro;
    }

    @JsonProperty("dataCadastro")
    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @JsonProperty("dataExpiracao")
    public String getDataExpiracao() {
        return dataExpiracao;
    }

    @JsonProperty("dataExpiracao")
    public void setDataExpiracao(String dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    @JsonProperty("dataAtualizacao")
    public String getDataAtualizacao() {
        return dataAtualizacao;
    }

    @JsonProperty("dataAtualizacao")
    public void setDataAtualizacao(String dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @JsonProperty("ativo")
    public Boolean getAtivo() {
        return ativo;
    }

    @JsonProperty("ativo")
    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
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
