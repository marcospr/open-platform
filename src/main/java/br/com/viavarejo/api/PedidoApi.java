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
	private RequestUtil<Pedido> requestUtilPedidoParceiro = new RequestUtil<Pedido>(Pedido.class);
	private RequestUtil<String> requestUtilNotaFiscalPedido = new RequestUtil<String>(String.class);
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
		String path = basePath + "/pedidos/carrinho";

		return requestUtilPedidoCarrinho.post(path, authorization, pedidosCarrinho);
	}

	public Pedido getDadosPedidoParceiro(Map<String, String> pathParams) throws ApiException {
		// verify the required parameter
		if (pathParams == null) {
			throw new ApiException(400, "Missing the required parameter 'pathParams'");
		}

		// create path and map variables
		String path = basePath + String.format("/pedidos/%s", pathParams.get("request.idCompra"));

		return requestUtilPedidoParceiro.get(path, authorization, pathParams);
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
		String path = basePath + String.format("/pedidos/%s", variableParams.get("idCompra"));

		return requestUtilConfirmacaoReqDTO.patch(path, authorization, confirmacaoPedido);
	}

	public String getNotaFiscalPedido(Map<String, String> pathParams) throws ApiException {
		// verify the required parameter
		if (pathParams == null) {
			throw new ApiException(400, "Missing the required parameter 'pathParams'");
		}
		// create path and map variables
		String path = basePath + String.format("/pedidos/%s/entregas/%s/nfe/%s", pathParams.get("idCompra"),
				pathParams.get("idCompraEntrega"), pathParams.get("formato"));

		return requestUtilNotaFiscalPedido.get(path, authorization, pathParams);
	}

}
