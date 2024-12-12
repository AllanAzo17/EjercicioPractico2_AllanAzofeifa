/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TiendaAllanAzofeifa.demo.service.impl;



import TiendaAllanAzofeifa.demo.dao.UsuarioDao;
import TiendaAllanAzofeifa.demo.domain.Usuarios;
import TiendaAllanAzofeifa.demo.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    @Transactional(readOnly = true)
    public List<Usuarios> getUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuarios getUsuario(Long id) {
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuarios getUsuarioPorEmail(String email) {
        return usuarioDao.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuarios getUsuarioPorEmailYPassword(String email, String password) {
        return usuarioDao.findByEmailAndPassword(email, password);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuarios getUsuarioPorEmailONombreCompleto(String email, String nombreCompleto) {
        return usuarioDao.findByEmailOrNombreCompleto(email, nombreCompleto);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorEmailONombreCompleto(String email, String nombreCompleto) {
        return usuarioDao.existsByEmailOrNombreCompleto(email, nombreCompleto);
    }

    @Override
    @Transactional
    public void save(Usuarios usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioDao.deleteById(id);
    }

    @Transactional
    public void delete(Usuarios usuario) {
        usuarioDao.delete(usuario);
    }
}


