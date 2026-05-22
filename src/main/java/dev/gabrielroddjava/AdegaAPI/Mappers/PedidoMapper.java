package dev.gabrielroddjava.AdegaAPI.Mappers;

import dev.gabrielroddjava.AdegaAPI.Dtos.PedidoDTO;
import dev.gabrielroddjava.AdegaAPI.Pedidos.PedidoModel;
import org.springframework.stereotype.Component;

@Component
public class PedidoMapper {

    public PedidoModel toModel(PedidoDTO pedidoDTO) {
        PedidoModel pedidoModel = new PedidoModel();

        pedidoModel.setId(pedidoDTO.getId());
        pedidoModel.setDataDaVenda(pedidoDTO.getDataDaVenda());
        pedidoModel.setValorTotalPedido(pedidoDTO.getValorTotalPedido());
        pedidoModel.setItensPedido(pedidoDTO.getItensPedido());

        return pedidoModel;
    }

    public PedidoDTO toDTO(PedidoModel pedidoModel) {
        PedidoDTO pedidoDTO = new PedidoDTO();

        pedidoDTO.setId(pedidoModel.getId());
        pedidoDTO.setDataDaVenda(pedidoModel.getDataDaVenda());
        pedidoDTO.setValorTotalPedido(pedidoModel.getValorTotalPedido());
        pedidoDTO.setItensPedido(pedidoModel.getItensPedido());

        return pedidoDTO;
    }
}
