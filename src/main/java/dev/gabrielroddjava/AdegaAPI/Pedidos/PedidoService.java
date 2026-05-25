package dev.gabrielroddjava.AdegaAPI.Pedidos;

import dev.gabrielroddjava.AdegaAPI.Dtos.PedidoDTO;
import dev.gabrielroddjava.AdegaAPI.Item.ItemPedidoModel;
import dev.gabrielroddjava.AdegaAPI.Item.ItemPedidoRepository;
import dev.gabrielroddjava.AdegaAPI.Mappers.PedidoMapper;
import dev.gabrielroddjava.AdegaAPI.Produtos.ProdutoModel;
import dev.gabrielroddjava.AdegaAPI.Produtos.ProdutoRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    //Injetando dependencia do repository
    private final PedidoRepository pedidoRepository;

    //Injetando dependencia do produto repository
    private final ProdutoRepository produtoRepository;

    //Injetando dependencia do produto Mapper
    private final PedidoMapper pedidoMapper;

    //Injetando dependencia do itempedidorepository
    private final ItemPedidoRepository itemPedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, ItemPedidoRepository itemPedidoRepository, PedidoMapper pedidoMapper) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoMapper = pedidoMapper;
    }

    public PedidoDTO criarPedido(PedidoDTO novoPedidoDTO) {
        PedidoModel novoPedidoModel = pedidoMapper.toModel(novoPedidoDTO);

        double valorTotalPedido = 0.0;

        List<ItemPedidoModel> itensDoPedido = novoPedidoModel.getItensPedido();

        for (ItemPedidoModel item : itensDoPedido) {
            ProdutoModel produtoDoBanco = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Bebida não encontrada no catálogo!"));

            if (produtoDoBanco.getQtdEstoque() < item.getQuantidadeComprada()) {
                throw new RuntimeException("Estoque insuficiente para a bebida: " + produtoDoBanco.getNome());
            }

            produtoDoBanco.setQtdEstoque(produtoDoBanco.getQtdEstoque() - item.getQuantidadeComprada());
            produtoRepository.save(produtoDoBanco);

            item.setPedido(novoPedidoModel);
            item.setProduto(produtoDoBanco);

            item.setValorUnitarioNaVenda(produtoDoBanco.getValor());

            double subTotalItem = produtoDoBanco.getValor() * item.getQuantidadeComprada();
            valorTotalPedido += subTotalItem;
        }

        novoPedidoModel.setValorTotalPedido(valorTotalPedido);

        PedidoModel pedidoSalvoModel = pedidoRepository.save(novoPedidoModel);

        return pedidoMapper.toDTO(pedidoSalvoModel);
    }

    public List<PedidoDTO> mostrarTodosPedidos() {
        List<PedidoModel> listaPedidosModel = pedidoRepository.findAll();
        return listaPedidosModel.stream()
                .map(pedidoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PedidoDTO mostrarPedidoPorID(Long id) {
        Optional<PedidoModel> pedidoMostrar = pedidoRepository.findById(id);
        return pedidoMostrar.map(pedidoMapper::toDTO).orElse(null);
    }

    public void deletarPedidoPorID(Long id) {
        PedidoDTO pedidoDeletar = mostrarPedidoPorID(id);
        if (pedidoDeletar != null) {
            for (ItemPedidoModel itemParaDeletar : pedidoDeletar.getItensPedido()) {
                ProdutoModel produto = itemParaDeletar.getProduto();
                produto.setQtdEstoque((produto.getQtdEstoque() + itemParaDeletar.getQuantidadeComprada()));
                produtoRepository.save(produto);
            }
            pedidoRepository.delete(pedidoMapper.toModel(pedidoDeletar));
        }
    }

    public PedidoDTO editarPedidoPorID(Long id, PedidoDTO pedidoAtualizado) {
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
        PedidoModel pedidoAntigoSalvo = pedidoRepository.save(pedidoAntigo);
        return pedidoMapper.toDTO(pedidoAntigoSalvo);
    }
}

