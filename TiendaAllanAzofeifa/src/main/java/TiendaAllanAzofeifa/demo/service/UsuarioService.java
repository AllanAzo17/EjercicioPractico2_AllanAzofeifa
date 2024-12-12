package TiendaAllanAzofeifa.demo.service;

import TiendaAllanAzofeifa.demo.domain.Usuarios;
import java.util.List;


public interface UsuarioService {
    
    // Se obtiene un listado de usuarios en un List 
    public List<Usuarios> getUsuarios();
    
    public Usuarios getUsuario(Long id);
    
    public Usuarios getUsuarioPorEmail(String email);
    
    public Usuarios getUsuarioPorEmailYPassword(String email, String password);
    
    public Usuarios getUsuarioPorEmailONombreCompleto(String email, String nombreCompleto);
    
    public boolean existeUsuarioPorEmailONombreCompleto(String email, String nombreCompleto);
    
    public void save(Usuarios usuario);
    
    public void delete(Long id);
    
}
