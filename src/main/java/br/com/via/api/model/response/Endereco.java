
package br.com.via.api.model.response;

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
    "cep",
    "estado",	
    "logradouro",
    "cidade",
    "numero",
    "referencia",
    "bairro",
    "complemento",
    "telefone",
    "telefone2",
    "telefone3"
})
public class Endereco implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @JsonProperty("cep")
    private String cep;
    @JsonProperty("estado")
    private String estado;
    @JsonProperty("logradouro")
    private String logradouro;
    @JsonProperty("cidade")
    private String cidade;
    @JsonProperty("numero")
    private Integer numero;
    @JsonProperty("referencia")
    private String referencia;
    @JsonProperty("bairro")
    private String bairro;
    @JsonProperty("complemento")
    private String complemento;
    @JsonProperty("telefone")
    private String telefone;
    @JsonProperty("telefone2")
    private String telefone2;
    @JsonProperty("telefone3")
    private String telefone3;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cep")
    public String getCep() {
        return cep;
    }

    @JsonProperty("cep")
    public void setCep(String cep) {
        this.cep = cep;
    }

    @JsonProperty("estado")
    public String getEstado() {
        return estado;
    }

    @JsonProperty("estado")
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @JsonProperty("logradouro")
    public String getLogradouro() {
        return logradouro;
    }

    @JsonProperty("logradouro")
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @JsonProperty("cidade")
    public String getCidade() {
        return cidade;
    }

    @JsonProperty("cidade")
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @JsonProperty("numero")
    public Integer getNumero() {
        return numero;
    }

    @JsonProperty("numero")
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @JsonProperty("referencia")
    public String getReferencia() {
        return referencia;
    }

    @JsonProperty("referencia")
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @JsonProperty("bairro")
    public String getBairro() {
        return bairro;
    }

    @JsonProperty("bairro")
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @JsonProperty("complemento")
    public String getComplemento() {
        return complemento;
    }

    @JsonProperty("complemento")
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @JsonProperty("telefone")
    public String getTelefone() {
        return telefone;
    }

    @JsonProperty("telefone")
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @JsonProperty("telefone2")
    public String getTelefone2() {
        return telefone2;
    }

    @JsonProperty("telefone2")
    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    @JsonProperty("telefone3")
    public String getTelefone3() {
        return telefone3;
    }

    @JsonProperty("telefone3")
    public void setTelefone3(String telefone3) {
        this.telefone3 = telefone3;
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
        result = ((result* 31)+((this.estado == null)? 0 :this.estado.hashCode()));
        result = ((result* 31)+((this.cidade == null)? 0 :this.cidade.hashCode()));
        result = ((result* 31)+((this.telefone == null)? 0 :this.telefone.hashCode()));
        result = ((result* 31)+((this.numero == null)? 0 :this.numero.hashCode()));
        result = ((result* 31)+((this.bairro == null)? 0 :this.bairro.hashCode()));
        result = ((result* 31)+((this.telefone2 == null)? 0 :this.telefone2 .hashCode()));
        result = ((result* 31)+((this.telefone3 == null)? 0 :this.telefone3 .hashCode()));
        result = ((result* 31)+((this.cep == null)? 0 :this.cep.hashCode()));
        result = ((result* 31)+((this.complemento == null)? 0 :this.complemento.hashCode()));
        result = ((result* 31)+((this.logradouro == null)? 0 :this.logradouro.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.referencia == null)? 0 :this.referencia.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Endereco) == false) {
            return false;
        }
        Endereco rhs = ((Endereco) other);
        return (((((((((((((this.estado == rhs.estado)||((this.estado!= null)&&this.estado.equals(rhs.estado)))&&((this.cidade == rhs.cidade)||((this.cidade!= null)&&this.cidade.equals(rhs.cidade))))&&((this.telefone == rhs.telefone)||((this.telefone!= null)&&this.telefone.equals(rhs.telefone))))&&((this.numero == rhs.numero)||((this.numero!= null)&&this.numero.equals(rhs.numero))))&&((this.bairro == rhs.bairro)||((this.bairro!= null)&&this.bairro.equals(rhs.bairro))))&&((this.telefone2 == rhs.telefone2)||((this.telefone2 != null)&&this.telefone2 .equals(rhs.telefone2))))&&((this.telefone3 == rhs.telefone3)||((this.telefone3 != null)&&this.telefone3 .equals(rhs.telefone3))))&&((this.cep == rhs.cep)||((this.cep!= null)&&this.cep.equals(rhs.cep))))&&((this.complemento == rhs.complemento)||((this.complemento!= null)&&this.complemento.equals(rhs.complemento))))&&((this.logradouro == rhs.logradouro)||((this.logradouro!= null)&&this.logradouro.equals(rhs.logradouro))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.referencia == rhs.referencia)||((this.referencia!= null)&&this.referencia.equals(rhs.referencia))));
    }

}
