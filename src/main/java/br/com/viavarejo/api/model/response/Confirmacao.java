
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
    "pedidoConfirmado",
    "pedidoCancelado"
})
public class Confirmacao {

    @JsonProperty("pedidoConfirmado")
    private Boolean pedidoConfirmado;
    @JsonProperty("pedidoCancelado")
    private Boolean pedidoCancelado;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("pedidoConfirmado")
    public Boolean getPedidoConfirmado() {
        return pedidoConfirmado;
    }

    @JsonProperty("pedidoConfirmado")
    public void setPedidoConfirmado(Boolean pedidoConfirmado) {
        this.pedidoConfirmado = pedidoConfirmado;
    }

    @JsonProperty("pedidoCancelado")
    public Boolean getPedidoCancelado() {
        return pedidoCancelado;
    }

    @JsonProperty("pedidoCancelado")
    public void setPedidoCancelado(Boolean pedidoCancelado) {
        this.pedidoCancelado = pedidoCancelado;
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
        result = ((result* 31)+((this.pedidoConfirmado == null)? 0 :this.pedidoConfirmado.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.pedidoCancelado == null)? 0 :this.pedidoCancelado.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Confirmacao) == false) {
            return false;
        }
        Confirmacao rhs = ((Confirmacao) other);
        return ((((this.pedidoConfirmado == rhs.pedidoConfirmado)||((this.pedidoConfirmado!= null)&&this.pedidoConfirmado.equals(rhs.pedidoConfirmado)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.pedidoCancelado == rhs.pedidoCancelado)||((this.pedidoCancelado!= null)&&this.pedidoCancelado.equals(rhs.pedidoCancelado))));
    }

}
