package br.com.viavarejo.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
	
	public ProdutoDTO getListaDadosProdutos(String idLogista, String ... idSKu) throws ApiException{
		return request.get("http://api-integracao-casasbahia.hlg-b2b.net/lojistas/"+idLogista+"/produtos/", "H9xO4+R8GUy+18nUCgPOlg==","idSku",Arrays.asList(idSKu));
	}
	
	public ProdutoDTO getDadosProdutoCampanha(String idCampanha, String idSKu, String cnpj, String idLojista) throws ApiException{
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("idLojista", idLojista);
		queryParams.put("cnpj", cnpj);
		return request.get("http://api-integracao-casasbahia.hlg-b2b.net/campanhas/"+idCampanha+"/produtos/" + idSKu, "H9xO4+R8GUy+18nUCgPOlg==", queryParams);
	}

}
