package br.com.via.api;

import br.com.via.api.client.ApiException;
import br.com.via.api.client.RequestUtil;
import br.com.via.api.model.response.ChaveDTO;


public class SegurancaAPI {
	
	private RequestUtil<?, ChaveDTO> request;
	
	private String basePath;
	private String authorization;
	
	public SegurancaAPI(String basePath, String authorization) {
		this.basePath = basePath;
		this.authorization = authorization;
		request = new RequestUtil<>(ChaveDTO.class);
	}
	
	public ChaveDTO getChave() throws ApiException{
		return request.get(basePath + "/seguranca/chaves", authorization);
	}

}
