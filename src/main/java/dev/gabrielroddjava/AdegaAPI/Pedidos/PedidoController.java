package dev.gabrielroddjava.AdegaAPI.Pedidos;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    //GET - Mostrar todos os pedidos
    @GetMapping("/mostrar")
    public String mostrarTodosPedidos() {
        return "mostrando todos os pedidos";
    }

    //GET - Mostrar pedidos por ID
    @GetMapping("/mostrarporid")
    public String mostrarPedidoPorId() {
        return "mostrando por id";
    }

    //POST - Criar novo pedido
    @PostMapping("/criar")
    public String criarPedido(){
        return "criando pedido";
    }

    //PUT - Atualizar pedido
    @PutMapping("/editar")
    public String editarPedido() {
        return "editando pedido";
    }

    //DELETE - Deletar pedido
    @DeleteMapping("/deletar")
    public String deletarPedido() {
        return "Deletando pedido";
    }

}
