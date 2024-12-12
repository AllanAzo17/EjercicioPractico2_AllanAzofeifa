/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TiendaAllanAzofeifa.demo.service;
import TiendaAllanAzofeifa.demo.domain.Evento;
import java.time.LocalDate;
import java.util.List;
import org.springframework.security.core.userdetails.*;

public interface EventoService {
    

    public List<Evento> getEventos();
    

    public Evento getEvento(Long evento);
    
  
    public void save(Evento evento);
    

    public void delete(Evento evento);
    
    
    List<Evento> filtrarEventos(String tipo, LocalDate fechaInicio, LocalDate fechaFin);
}


