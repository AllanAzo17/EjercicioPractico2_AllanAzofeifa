/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TiendaAllanAzofeifa.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotEmpty; 

import lombok.Data;
@Entity
@Data
@Table (name="usuarios")
public class Usuarios implements Serializable { 

private static final long serialVersionUID = 1L;


@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
private Long id; 
@NotEmpty 
private String nombreCompleto; 
@NotEmpty 
private String email; 
@NotEmpty 
private String password;

@OneToMany
@JoinColumn (name="rol_id") 
private List<Rol> roles;
}