package dev.gabrielroddjava.AdegaAPI.Pedidos;

import dev.gabrielroddjava.AdegaAPI.Item.ItemPedidoModel;
import dev.gabrielroddjava.AdegaAPI.Produtos.ProdutoModel;
import dev.gabrielroddjava.AdegaAPI.Produtos.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    //Injetando dependencia do repository
    private PedidoRepository pedidoRepository;

    //Injetando dependencia do produto repository
    private ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    public PedidoModel criarPedido(PedidoModel novoPedido) {
        double valorTotalPedido = 0.0;

        List<ItemPedidoModel> itensDoPedido = novoPedido.getItensPedido();

        for (ItemPedidoModel item : itensDoPedido) {
            ProdutoModel produtoDoBanco = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Bebida não encontrada no catálogo!"));

            if (produtoDoBanco.getQtdEstoque() < item.getQuantidadeComprada()) {
                throw new RuntimeException("Estoque insuficiente para a bebida: " + produtoDoBanco.getNome());
            }

            produtoDoBanco.setQtdEstoque(produtoDoBanco.getQtdEstoque() - item.getQuantidadeComprada());
            produtoRepository.save(produtoDoBanco);

            item.setPedido(novoPedido);
            item.setProduto(produtoDoBanco);

            item.setValorUnitarioNaVenda(produtoDoBanco.getValor());

            double subTotalItem = produtoDoBanco.getValor() * item.getQuantidadeComprada();
            valorTotalPedido += subTotalItem;
        }

        novoPedido.setValorTotalPedido(valorTotalPedido);

        return pedidoRepository.save(novoPedido);
    }

    public List<PedidoModel> mostrarTodosPedidos() {
        return pedidoRepository.findAll();
    }

}

