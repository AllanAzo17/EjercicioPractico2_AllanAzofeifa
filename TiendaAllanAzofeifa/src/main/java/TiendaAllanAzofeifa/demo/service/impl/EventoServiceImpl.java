/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TiendaAllanAzofeifa.demo.service.impl;

import TiendaAllanAzofeifa.demo.dao.EventoDao;
import TiendaAllanAzofeifa.demo.domain.Evento;
import TiendaAllanAzofeifa.demo.service.EventoService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoDao eventoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Evento> getEventos() {
        return eventoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Evento getEvento(Long evento) {
        return eventoDao.findById(evento.getId()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Evento evento) {
        eventoDao.save(evento);
    }

    @Override
    @Transactional
    public void delete(Evento evento) {
        eventoDao.delete(evento);
    }


    @Override
    public List<Evento> filtrarEventos(String tipo, LocalDate fechaInicio, LocalDate fechaFin) {
        if (tipo != null && fechaInicio != null && fechaFin != null) {
            return eventoDao.findByTipoAndFechaBetween(tipo, fechaInicio, fechaFin);
        } else if (tipo != null) {
            return eventoDao.findByTipo(tipo);
        } else if (fechaInicio != null && fechaFin != null) {
            return eventoDao.findByFechaBetween(fechaInicio, fechaFin);
        } else {
            return eventoDao.findAll();
        }
    }
}



