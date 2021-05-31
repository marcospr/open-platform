package br.com.viavarejo.api.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Produtos {
	@JsonProperty("Codigo")
	private Integer codigo;
	
	@JsonProperty("Quantidade")
   private Integer quantidade;
   
	@JsonProperty("IdLojista")
	private Integer idLojista;
   
   public Produtos() {}
   
	public Produtos(Integer codigo, Integer quantidade, Integer idLojista) {
		this.codigo = codigo;
		this.quantidade = quantidade;
		this.idLojista = idLojista;
	}

	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Integer getIdLojista() {
		return idLojista;
	}
	public void setIdLojista(Integer idLojista) {
		this.idLojista = idLojista;
	}
   
}
