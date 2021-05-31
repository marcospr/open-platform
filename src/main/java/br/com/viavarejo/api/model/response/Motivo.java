
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
    "categoria",
    "assunto",
    "motivo",
    "observacao"
})
public class Motivo {

    @JsonProperty("categoria")
    private String categoria;
    @JsonProperty("assunto")
    private String assunto;
    @JsonProperty("motivo")
    private String motivo;
    @JsonProperty("observacao")
    private String observacao;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("categoria")
    public String getCategoria() {
        return categoria;
    }

    @JsonProperty("categoria")
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @JsonProperty("assunto")
    public String getAssunto() {
        return assunto;
    }

    @JsonProperty("assunto")
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    @JsonProperty("motivo")
    public String getMotivo() {
        return motivo;
    }

    @JsonProperty("motivo")
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @JsonProperty("observacao")
    public String getObservacao() {
        return observacao;
    }

    @JsonProperty("observacao")
    public void setObservacao(String observacao) {
        this.observacao = observacao;
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
        result = ((result* 31)+((this.assunto == null)? 0 :this.assunto.hashCode()));
        result = ((result* 31)+((this.motivo == null)? 0 :this.motivo.hashCode()));
        result = ((result* 31)+((this.observacao == null)? 0 :this.observacao.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.categoria == null)? 0 :this.categoria.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Motivo) == false) {
            return false;
        }
        Motivo rhs = ((Motivo) other);
        return ((((((this.assunto == rhs.assunto)||((this.assunto!= null)&&this.assunto.equals(rhs.assunto)))&&((this.motivo == rhs.motivo)||((this.motivo!= null)&&this.motivo.equals(rhs.motivo))))&&((this.observacao == rhs.observacao)||((this.observacao!= null)&&this.observacao.equals(rhs.observacao))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.categoria == rhs.categoria)||((this.categoria!= null)&&this.categoria.equals(rhs.categoria))));
    }

}
