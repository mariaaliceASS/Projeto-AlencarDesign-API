/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alencar.AlencarDesign.controller;

import com.alencar.AlencarDesign.data.ClienteEntity;
import com.alencar.AlencarDesign.service.ClienteService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    
    @GetMapping
    public List<ClienteEntity> listarCliente(){
        return clienteService.listarTodos();
    }
    @GetMapping("/buscar/{id}")
    public ResponseEntity<ClienteEntity> buscarCliente(@PathVariable Long id) {
        Optional<ClienteEntity> cliente = clienteService.buscarPorId(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping("/adicionar")
    public ResponseEntity<ClienteEntity> adicionarFilme (@Valid @RequestBody ClienteEntity cliente) {
        ClienteEntity clienteSalvo = clienteService.salvar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ClienteEntity> atualizarFilme (@PathVariable Long id,@Valid @RequestBody ClienteEntity clienteAtt){
      Optional<ClienteEntity> clienteExiste = clienteService.buscarPorId(id);
      
      if(clienteExiste.isPresent()){
       ClienteEntity cliente = clienteExiste.get();
       cliente.setNome(clienteAtt.getNome());
       cliente.setCpf(clienteAtt.getCpf());
       cliente.setNumeroTelefone(clienteAtt.getNumeroTelefone());
       cliente.setEndereco(clienteAtt.getEndereco());
       
       ClienteEntity clienteSalvo = clienteService.salvar(cliente);
       return ResponseEntity.ok(clienteSalvo);
          
      }else{
         return ResponseEntity.notFound().build();

      }
      
    }
    

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
}
