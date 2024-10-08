
package com.alencar.AlencarDesign.controller;

import com.alencar.AlencarDesign.data.ClienteEntity;
import com.alencar.AlencarDesign.service.ClienteService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaginaController {
    @Autowired
    private ClienteService clienteService;
    // Serve a página de cadastro de filmes
    @GetMapping("/cadastrarCliente")
    public String cadastrarCliente(Model model) {
        model.addAttribute("cliente", new ClienteEntity()); // Adiciona um novo objeto Filme para ser preenchido no form
        return "cadastrarCliente"; // Nome do arquivo HTML (cadastrarFilmes.html) dentro de src/main/resources/templates
    }
    
    
    @GetMapping("/consulta")
    public String adicionarConsulta(@RequestParam Long clienteId, Model model) {  // (@PathVariable Long id, Model model) {
        Optional<ClienteEntity> cliente = clienteService.buscarPorId(clienteId);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            model.addAttribute("clienteId", cliente.get().getId().toString());
            return "consulta"; // Nome da view para os detalhes
        } else {
            return "erro"; // Nome da página de erro
        }
    }
 
    @GetMapping("/editarCliente")
    public String editarCliente(@RequestParam Long clienteId, Model model) {  // (@PathVariable Long id, Model model) {
        Optional<ClienteEntity> cliente = clienteService.buscarPorId(clienteId);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            model.addAttribute("clienteId", cliente.get().getId().toString());
            return "editarCliente"; // Nome da view para os detalhes
        } else {
            return "erro"; // Nome da página de erro
        }
    }
    // Serve a página de listagem de filmes
    @GetMapping("/listarClientes")
    public String listarClientes() {
        return "listarClientes"; // Nome do arquivo HTML (listarFilmes.html)
    }
}
