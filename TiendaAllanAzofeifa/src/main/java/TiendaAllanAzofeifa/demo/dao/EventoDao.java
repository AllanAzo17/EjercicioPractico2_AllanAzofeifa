/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TiendaAllanAzofeifa.demo.dao;

import TiendaAllanAzofeifa.demo.domain.Evento;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

public interface EventoDao extends JpaRepository <Evento,Long>{
    
    public interface EventoRepository extends JpaRepository<Evento, Long> {
    // Buscar eventos por tipo
    List<Evento> findByTipo(String tipo);
    
    // Buscar eventos dentro de un rango de fechas
    List<Evento> findByFechaBetween(LocalDate fechaInicio, LocalDate fechaFin);
    
    // Buscar eventos por tipo y rango de fechas
    List<Evento> findByTipoAndFechaBetween(String tipo, LocalDate fechaInicio, LocalDate fechaFin);
}
@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> filtrarEventos(String tipo, LocalDate fechaInicio, LocalDate fechaFin) {
        if (tipo != null && fechaInicio != null && fechaFin != null) {
            return eventoRepository.findByTipoAndFechaBetween(tipo, fechaInicio, fechaFin);
        } else if (tipo != null) {
            return eventoRepository.findByTipo(tipo);
        } else if (fechaInicio != null && fechaFin != null) {
            return eventoRepository.findByFechaBetween(fechaInicio, fechaFin);
        } else {
            return eventoRepository.findAll();
        }
    }
}

}
