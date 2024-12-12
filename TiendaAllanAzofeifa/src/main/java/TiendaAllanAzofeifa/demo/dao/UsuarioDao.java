/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TiendaAllanAzofeifa.demo.dao;

import TiendaAllanAzofeifa.demo.domain.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDao extends JpaRepository<Usuarios, Long> {

    Usuarios findNombreCompleto (String nombreCompleto);  
    
    Usuarios findByUsernameAndPassword(String email, String Password);
    
    Usuarios findByUsernameOrCorreo(String email, String nombreCompleto);
    
    boolean existsByUsernameOrCorreo(String email, String nombreCompleto);

    public Usuarios findByEmail(String email);

    public Usuarios findByEmailAndPassword(String email, String password);

    public Usuarios findByEmailOrNombreCompleto(String email, String nombreCompleto);

    public boolean existsByEmailOrNombreCompleto(String email, String nombreCompleto);
}

