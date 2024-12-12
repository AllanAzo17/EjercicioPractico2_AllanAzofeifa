/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TiendaAllanAzofeifa.demo.service.impl;

import TiendaAllanAzofeifa.demo.domain.Rol;
import TiendaAllanAzofeifa.demo.domain.Usuarios;
import TiendaAllanAzofeifa.demo.service.UsuarioDetailsService;
import TiendaAllanAzofeifa.demo.dao.UsuarioDao;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority; 
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private HttpSession session;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca el usuario por el username (debes haber implementado este método en UsuarioDao)
        Usuarios usuario = usuarioDao.findByEmail(username); // O reemplaza por findByUsername si es el caso
        
        // Si no existe el usuario lanza una excepción
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        
        // Configuración de la sesión (asegúrate de que rutaImagen existe en la entidad Usuario)
        session.removeAttribute("usuarioImagen");
        session.setAttribute("usuarioImagen", usuario.getRutaImagen());

        // Se sacan los roles asociados al usuario
        List<GrantedAuthority> roles = new ArrayList<>();
        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }

        // Se devuelve un User (clase de UserDetails)
        return new User(usuario.getEmail(), usuario.getPassword(), roles);
    }
}

