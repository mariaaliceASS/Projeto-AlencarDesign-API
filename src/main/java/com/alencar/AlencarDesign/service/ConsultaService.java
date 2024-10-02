/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.alencar.AlencarDesign.service;

import com.alencar.AlencarDesign.data.ClienteRepository;
import com.alencar.AlencarDesign.data.ConsultaEntity;
import com.alencar.AlencarDesign.data.ConsultaRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ConsultaService {
    
    @Autowired
    private ConsultaRepository consultaRepository;
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    public Optional<ConsultaEntity> buscarPorId(Long id) {
    return consultaRepository.findById(id);
}
    
    public Optional<ConsultaEntity> buscarPorClienteId(Long clienteId){
        return Optional.ofNullable(consultaRepository.findByClienteId(clienteId));
    }
    
    public ConsultaEntity salvar(ConsultaEntity consulta){
        return consultaRepository.save(consulta);
    }
    
    public void deletar (Long id){
        consultaRepository.deleteById(id);
    }
    
}
