/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alencar.AlencarDesign.controller;

import com.alencar.AlencarDesign.data.ClienteEntity;
import com.alencar.AlencarDesign.data.ConsultaEntity;
import com.alencar.AlencarDesign.service.ClienteService;
import com.alencar.AlencarDesign.service.ConsultaService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/consultas")
public class ConsultaController {
    
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/{clienteId}")
    public ResponseEntity<ConsultaEntity> adicionarAnalise(@PathVariable Long clienteId, @Valid @RequestBody ConsultaEntity consulta) {
        return clienteService.buscarPorId(clienteId)
                .map(cliente -> {
                    consulta.setCliente(cliente);
                    ConsultaEntity consultaSalva = consultaService.salvar(consulta);
                    return ResponseEntity.status(HttpStatus.CREATED).body(consultaSalva);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ConsultaEntity> buscarConsulta(@PathVariable Long clienteId) {
        Optional<ConsultaEntity> consulta = consultaService.buscarPorClienteId(clienteId);
        return consulta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    
     @PutMapping("/{clienteId}")
    public ResponseEntity<ConsultaEntity> atualizarAnalise(@PathVariable Long clienteId, @Valid @RequestBody ConsultaEntity consultaAtt) {
        Optional<ConsultaEntity> consultaExistente = consultaService.buscarPorClienteId(clienteId);

        if (consultaExistente.isPresent()) {
            ConsultaEntity consulta = consultaExistente.get();
            consulta.setDataConsulta(consultaAtt.getDataConsulta());
            consulta.setHoraConsulta(consultaAtt.getHoraConsulta());
            consulta.setPagamento(consultaAtt.getPagamento());
            consulta.setTipoProcedimento(consultaAtt.getTipoProcedimento());
            
            ConsultaEntity consultaSalva = consultaService.salvar(consulta);
            return ResponseEntity.ok(consultaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
      @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deletarAnalise(@PathVariable Long clienteId) {
        Optional<ConsultaEntity> consultaExiste = consultaService.buscarPorClienteId(clienteId);

        if (consultaExiste.isPresent()) {
            ConsultaEntity consulta = consultaExiste.get();
            ClienteEntity cliente = consulta.getCliente();

            cliente.setConsulta(null);
            consultaService.deletar(consultaExiste.get().getId());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
