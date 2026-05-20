package dev.gabrielroddjava.AdegaAPI.Produtos;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    //Injetando service
    ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping("boasvindas")
    public String boasVindasTeste() {
        return "boas vindas a rota";
    }

    //GET - Mostrar todos produtos
    @GetMapping("/mostrar")
    public List<ProdutoModel> mostrarTodosProdutos() {
        return produtoService.listarProdutos();
    }

    //POST - Criar Produto
    @PostMapping("/criar")
    public ProdutoModel criarProduto(@RequestBody ProdutoModel novoProduto) {
        return produtoService.cadastrarNovoProduto(novoProduto);
    }
}
