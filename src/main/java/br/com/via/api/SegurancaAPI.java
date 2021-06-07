package br.com.via.api;

import br.com.via.api.client.ApiException;
import br.com.via.api.client.RequestUtil;
import br.com.via.api.model.response.ChaveDTO;


public class SegurancaAPI {
	
	private RequestUtil<?, ChaveDTO> request;
	
	public SegurancaAPI() {
		request = new RequestUtil<>(ChaveDTO.class);
	}
	
	public ChaveDTO getChave() throws ApiException{
		return request.get("http://api-integracao-casasbahia.hlg-b2b.net/seguranca/chaves", "H9xO4+R8GUy+18nUCgPOlg==");
	}

}
