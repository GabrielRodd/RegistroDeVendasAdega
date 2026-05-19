package dev.gabrielroddjava.AdegaAPI.Produtos;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @GetMapping("boasvindas")
    public String boasVindasTeste() {
        return "boas vindas a rota";
    }
}
