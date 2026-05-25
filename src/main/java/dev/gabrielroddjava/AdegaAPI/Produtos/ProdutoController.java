package dev.gabrielroddjava.AdegaAPI.Produtos;

import dev.gabrielroddjava.AdegaAPI.Dtos.ProdutoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    //Injetando service
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping("boasvindas")
    public String boasVindasTeste() {
        return "boas vindas a rota";
    }

    //POST - Criar Produto
    @PostMapping("/criar")
    public ResponseEntity<String> criarProduto(@RequestBody ProdutoDTO novoProduto) {
        ProdutoDTO novoProdutoDTO = produtoService.cadastrarNovoProduto(novoProduto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Produto: " + novoProdutoDTO.getNome() + " criado com sucesso. ID: " + novoProdutoDTO.getId());
    }

    //GET - Mostrar todos produtos
    @GetMapping("/mostrar")
    public ResponseEntity<List<ProdutoDTO>> mostrarTodosProdutos() {
        List<ProdutoDTO> listaProdutoDTO = produtoService.listarProdutos();
        return ResponseEntity.status(HttpStatus.OK)
                .body(listaProdutoDTO);
    }

    //GET - Mostrar produtos por ID
    @GetMapping("/mostrar/{id}")
    public ResponseEntity<Object> mostrarProdutoPorID(@PathVariable Long id) {
        ProdutoDTO produtoMostrar = produtoService.mostrarProdutoPorId(id);
        if (produtoMostrar != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(produtoMostrar);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("ID " + id + " nao existe na lista de produtos");
        }
    }

    //PUT - Editar produto
    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editarProdutoPorID(@PathVariable Long id, @RequestBody ProdutoDTO produtoAtualizado) {
        ProdutoDTO produto = produtoService.atualizarProdutoPorId(id, produtoAtualizado);
        if (produto != null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Produto: " + produto.getNome() + " editado com sucesso. ID: " + produto.getId());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produto de ID " + id + " nao existe no banco de dados.");
        }
    }

    //DELETE - Deletar produto
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarProdutoPorID(@PathVariable Long id) {
        ProdutoDTO produtoDeletar = produtoService.mostrarProdutoPorId(id);
        if (produtoDeletar != null) {
            produtoService.deletarProdutoPorId(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Produto de ID " + id + " deletado com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Produto de ID " + id + " nao existe no banco de dados");
        }
    }
}
