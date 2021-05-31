package br.com.viavarejo.api;

import java.util.Map;

import javax.ws.rs.core.Response;

import br.com.viavarejo.api.client.ApiException;
import br.com.viavarejo.api.client.RequestUtil;
import br.com.viavarejo.api.model.request.ConfirmacaoReqDTO;
import br.com.viavarejo.api.model.request.PedidoCarrinho;
import br.com.viavarejo.api.model.response.Pedido;

public class PedidoApi {
	private RequestUtil<PedidoCarrinho> requestUtilPedidoCarrinho = new RequestUtil<PedidoCarrinho>(
			PedidoCarrinho.class);
	private RequestUtil<Pedido> requestUtilPedido = new RequestUtil<Pedido>(Pedido.class);
	private RequestUtil<ConfirmacaoReqDTO> requestUtilConfirmacaoReqDTO = new RequestUtil<ConfirmacaoReqDTO>(
			ConfirmacaoReqDTO.class);

	private String basePath;
	private String authorization;

	public PedidoApi(String basePath, String authorization) {
		this.basePath = basePath;
		this.authorization = authorization;
	}

	public Response postPedidosCarrinho(PedidoCarrinho pedidosCarrinho) throws ApiException {
		// verify the required parameter
		if (pedidosCarrinho == null) {
			throw new ApiException(400,
					"Missing the required parameter 'pedidosCarrinho' when calling postPedidosCarrinho");
		}

		// create path and map variables
		String path = "/pedidos/carrinho".replaceAll("\\{format\\}", "json");

		return requestUtilPedidoCarrinho.post(basePath + "/" + path, authorization, pedidosCarrinho);
	}

	public Pedido getDadosPedidoParceiro(Map<String, String> variableParams) throws ApiException {

		// verify the required parameter
		if (variableParams == null) {
			throw new ApiException(400, "Missing the required parameter 'variableParams'");
		}

		// create path and map variables
		String path = basePath + "/"
				+ "/pedidos/{idCompra}".replaceAll("\\{format\\}", "json").replaceAll("\\{" + "idCompra" + "\\}",
				variableParams.get("request.idCompra"));

		return requestUtilPedido.get(path, authorization, variableParams);
	}

	public Response patchPedidosCancelamentoOrConfirmacao(ConfirmacaoReqDTO confirmacaoPedido,
			Map<String, String> variableParams)
			throws ApiException {
		// verify the required parameter
		if (variableParams == null) {
			throw new ApiException(400, "Missing the required parameter 'variableParams'");
		}

		// verify the required parameter
		if (confirmacaoPedido == null) {
			throw new ApiException(400,
					"Missing the required parameter 'confirmacaoPedido' when calling pathPedidosCancelamentoOrConfirmacao");
		}

		// create path and map variables
		String path = basePath + "/" + "/pedidos/{idCompra}".replaceAll("\\{format\\}", "json")
				.replaceAll("\\{" + "idCompra" + "\\}",
						variableParams.get("idCompra"));

		return requestUtilConfirmacaoReqDTO.patch(path, authorization, confirmacaoPedido);
	}

}
