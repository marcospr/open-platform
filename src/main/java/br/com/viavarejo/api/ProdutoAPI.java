package br.com.viavarejo.api;

import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.client.RequestUtil;
import br.com.viavarejo.api.model.response.ProdutoDTO;

public class ProdutoAPI {
	
	private RequestUtil<ProdutoDTO> request;
	
	public ProdutoAPI() {
		request = new RequestUtil<ProdutoDTO>(ProdutoDTO.class);
	}
	
	
	public ProdutoDTO getDadosProduto(String idLogista, String idSKu) throws ApiException{
		return request.get("http://api-integracao-casasbahia.hlg-b2b.net/lojistas/"+idLogista+"/produtos/" + idSKu, "H9xO4+R8GUy+18nUCgPOlg==");
	}
	
	public ProdutoDTO getListaDadosProdutos(String idLogista) throws ApiException{
		return request.get("http://api-integracao-casasbahia.hlg-b2b.net/lojistas/"+idLogista+"/produtos/", "H9xO4+R8GUy+18nUCgPOlg==");
	}
	
	public ProdutoDTO getDadosProdutoCampanha(String idLogista, String idSKu) throws ApiException{
		return request.get("http://api-integracao-casasbahia.hlg-b2b.net/campanhas/"+idLogista+"/produtos/" + idSKu, "H9xO4+R8GUy+18nUCgPOlg==");
	}
	
	
	
	
	
	

}
