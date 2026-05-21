package dev.gabrielroddjava.AdegaAPI.Pedidos;

import dev.gabrielroddjava.AdegaAPI.Item.ItemPedidoModel;
import dev.gabrielroddjava.AdegaAPI.Item.ItemPedidoRepository;
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

    private ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, ItemPedidoRepository itemPedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
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

    public PedidoModel mostrarPedidoPorID(Long id) {
        Optional<PedidoModel> pedidoMostrar = pedidoRepository.findById(id);
        return pedidoMostrar.orElse(null);
    }

    public void deletarPedidoPorID(Long id) {
        PedidoModel pedidoDeletar = mostrarPedidoPorID(id);
        if (pedidoDeletar != null) {
            pedidoRepository.delete(pedidoDeletar);
        }
    }

    public PedidoModel editarPedidoPorID(Long id, PedidoModel pedidoAtualizado) {
        PedidoModel pedidoAntigo = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado!"));

        // 2. DEVOLVER PARA A PRATELEIRA: Estorna o estoque dos itens antigos
        for (ItemPedidoModel itemAntigo : pedidoAntigo.getItensPedido()) {
            ProdutoModel produto = itemAntigo.getProduto();
            // Soma a quantidade que tinha sido comprada de volta ao estoque
            produto.setQtdEstoque(produto.getQtdEstoque() + itemAntigo.getQuantidadeComprada());
            produtoRepository.save(produto);
        }

        // 3. LIMPAR O CUPOM: Apaga os itens antigos do banco para não duplicar
        itemPedidoRepository.deleteAll(pedidoAntigo.getItensPedido());
        pedidoAntigo.getItensPedido().clear();

        // 4. REFAZER A VENDA: Agora aplicamos a lista nova (exatamente como no criarPedido)
        double valorTotalAcumulado = 0.0;
        List<ItemPedidoModel> novosItens = pedidoAtualizado.getItensPedido();

        for (ItemPedidoModel novoItem : novosItens) {

            // Busca o produto real para abater o estoque novo
            ProdutoModel produtoDoBanco = produtoRepository.findById(novoItem.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Bebida não encontrada no catálogo!"));

            if (produtoDoBanco.getQtdEstoque() < novoItem.getQuantidadeComprada()) {
                throw new RuntimeException("Estoque insuficiente para a bebida: " + produtoDoBanco.getNome());
            }

            // Subtrai a nova quantidade
            produtoDoBanco.setQtdEstoque(produtoDoBanco.getQtdEstoque() - novoItem.getQuantidadeComprada());
            produtoRepository.save(produtoDoBanco);

            // Amarra o novo item no pedido ANTIGO (mantendo o mesmo ID de venda)
            novoItem.setPedido(pedidoAntigo);
            novoItem.setProduto(produtoDoBanco);
            novoItem.setValorUnitarioNaVenda(produtoDoBanco.getValor());

            valorTotalAcumulado += produtoDoBanco.getValor() * novoItem.getQuantidadeComprada();
        }

        // 5. Atualiza os dados finais do pedido antigo e salva
        pedidoAntigo.setItensPedido(novosItens);
        pedidoAntigo.setValorTotalPedido(valorTotalAcumulado);

        // Retorna o pedido atualizado
        return pedidoRepository.save(pedidoAntigo);
    }
}

