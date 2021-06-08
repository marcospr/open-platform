package br.com.viavarejo.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.client.RequestUtil;
import br.com.viavarejo.api.model.response.ProdutoDTO;
import br.com.viavarejo.api.model.response.ProdutosDTO;

public class ProdutoAPI {
	
	private RequestUtil<?, ProdutoDTO> request;
	private RequestUtil<?, ProdutosDTO> requestList;
	
	private String basePath;
	private String authorization;
	
	public ProdutoAPI(String basePath, String authorization) {
		this.basePath = basePath;
		this.authorization = authorization;
		request = new RequestUtil<>(ProdutoDTO.class);
		requestList = new RequestUtil<>(ProdutosDTO.class);
	}
	
	
	public ProdutoDTO getDadosProduto(String idLogista, String idSKu) throws ApiException{
		return request.get(basePath +"/lojistas/"+idLogista+"/produtos/" + idSKu, authorization);
	}
	
	public ProdutosDTO getListaDadosProdutos(String idLogista, String ... idSKu) throws ApiException{
		return requestList.get(basePath +"/lojistas/"+idLogista+"/produtos", authorization,"idSku",Arrays.asList(idSKu));
	}
	
	public ProdutoDTO getDadosProdutoCampanha(String idCampanha, String idSKu, String cnpj, String idLojista) throws ApiException{
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("idLojista", idLojista);
		queryParams.put("cnpj", cnpj);
		return request.get(basePath +"/campanhas/"+idCampanha+"/produtos/" + idSKu, authorization, queryParams);
	}

}
