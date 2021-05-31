package br.com.viavarejo.api.model.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PedidoCarrinho {
	@JsonProperty("IdCampanha")
   private Integer idCampanha;
	
	@JsonProperty("Cnpj")
   private String cnpj;
	
	@JsonProperty("Cep")
   private String cep;
	
	@JsonProperty("Produtos")
   private List<Produtos> produtos = new ArrayList<>();

   public PedidoCarrinho() {}
   
   public PedidoCarrinho(Integer idCampanha, String cnpj, String cep, List<Produtos> produtos) {
		this.idCampanha = idCampanha;
		this.cnpj = cnpj;
		this.cep = cep;
		this.produtos = produtos;
	}

	public Integer getIdCampanha() {
		return idCampanha;
	}

	public void setIdCampanha(Integer idCampanha) {
		this.idCampanha = idCampanha;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public List<Produtos> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produtos> produtos) {
		this.produtos = produtos;
	}

}
