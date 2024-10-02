
package com.alencar.AlencarDesign.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consultas")
public class ConsultaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String dataConsulta; // data consulta
    
    @NotBlank
    private String horaConsulta;
    
    @NotBlank
    private String pagamento;
    
    @NotBlank
    private String tipoProcedimento;
    
    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference    
    private ClienteEntity cliente;


}
 