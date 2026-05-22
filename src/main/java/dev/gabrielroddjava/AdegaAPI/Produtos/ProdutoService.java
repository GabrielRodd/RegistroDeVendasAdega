package dev.gabrielroddjava.AdegaAPI.Produtos;

import dev.gabrielroddjava.AdegaAPI.Dtos.ProdutoDTO;
import dev.gabrielroddjava.AdegaAPI.Mappers.ProdutoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    //Injetando dependencia do Repository
    private ProdutoRepository produtoRepository;

    //Injetando dependencia do Repository
    private ProdutoMapper produtoMapper;

    public ProdutoService (ProdutoRepository produtoRepository, ProdutoMapper produtoMapper) {
        this.produtoRepository = produtoRepository;
        this.produtoMapper = produtoMapper;
    }

    public ProdutoDTO cadastrarNovoProduto(ProdutoDTO novoProduto) {
        ProdutoModel novoProdutoModel = produtoMapper.toModel(novoProduto);
        produtoRepository.save(novoProdutoModel);
        return produtoMapper.toDTO(novoProdutoModel);
    }

    public List<ProdutoDTO> listarProdutos() {
        List<ProdutoModel> listaProdutosModel = produtoRepository.findAll();
        return listaProdutosModel.stream()
                .map(produtoMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ProdutoDTO mostrarProdutoPorId(Long id) {
        Optional<ProdutoModel> produtoBuscadoModel = produtoRepository.findById(id);
        return produtoBuscadoModel.map(produtoMapper::toDTO).orElse(null);
    }

    public void deletarProdutoPorId(Long id) {
        ProdutoDTO produtoDeletarDTO = mostrarProdutoPorId(id);
        produtoRepository.delete(produtoMapper.toModel(produtoDeletarDTO));
    }

    public ProdutoDTO atualizarProdutoPorId(Long id, ProdutoDTO produtoAtualizadoDTO) {
        if (produtoRepository.existsById(id)) {
            ProdutoModel produtoAtualizadoModel = produtoMapper.toModel(produtoAtualizadoDTO);
            produtoAtualizadoModel.setId(id);
            produtoRepository.save(produtoAtualizadoModel);
            return produtoMapper.toDTO(produtoAtualizadoModel);
        } else {
            return null;
        }
    }
}
