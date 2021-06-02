
package br.com.viavarejo.api.model.response;

import java.io.Serializable;
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
    "codDescricao",
    "data",
    "dataEntrega",
    "descricao"
})
public class TrackingEntrega implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @JsonProperty("codDescricao")
    private String codDescricao;
    @JsonProperty("data")
    private String data;
    @JsonProperty("dataEntrega")
    private String dataEntrega;
    @JsonProperty("descricao")
    private String descricao;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("codDescricao")
    public String getCodDescricao() {
        return codDescricao;
    }

    @JsonProperty("codDescricao")
    public void setCodDescricao(String codDescricao) {
        this.codDescricao = codDescricao;
    }

    @JsonProperty("data")
    public String getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(String data) {
        this.data = data;
    }

    @JsonProperty("dataEntrega")
    public String getDataEntrega() {
        return dataEntrega;
    }

    @JsonProperty("dataEntrega")
    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    @JsonProperty("descricao")
    public String getDescricao() {
        return descricao;
    }

    @JsonProperty("descricao")
    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
        result = ((result* 31)+((this.codDescricao == null)? 0 :this.codDescricao.hashCode()));
        result = ((result* 31)+((this.dataEntrega == null)? 0 :this.dataEntrega.hashCode()));
        result = ((result* 31)+((this.descricao == null)? 0 :this.descricao.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof TrackingEntrega) == false) {
            return false;
        }
        TrackingEntrega rhs = ((TrackingEntrega) other);
        return ((((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data))))&&((this.codDescricao == rhs.codDescricao)||((this.codDescricao!= null)&&this.codDescricao.equals(rhs.codDescricao))))&&((this.dataEntrega == rhs.dataEntrega)||((this.dataEntrega!= null)&&this.dataEntrega.equals(rhs.dataEntrega))))&&((this.descricao == rhs.descricao)||((this.descricao!= null)&&this.descricao.equals(rhs.descricao))));
    }

}
