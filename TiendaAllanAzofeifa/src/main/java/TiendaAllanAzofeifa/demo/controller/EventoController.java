/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TiendaAllanAzofeifa.demo.controller;

import TiendaAllanAzofeifa.demo.domain.Evento;
import TiendaAllanAzofeifa.demo.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // Mostrar todos los eventos
    @GetMapping
    public String listarEventos(Model model) {
        List<Evento> eventos = eventoService.getEventos();
        model.addAttribute("eventos", eventos);
        return "evento/listar";  // Nombre de la vista Thymeleaf (evento/listar.html)
    }

    // Mostrar formulario para crear un nuevo evento
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoEvento(Model model) {
        Evento evento = new Evento();  // Crear un objeto Evento vacío
        model.addAttribute("evento", evento);
        return "evento/formulario";  // Nombre de la vista Thymeleaf (evento/formulario.html)
    }

    // Guardar un nuevo evento o actualizar uno existente
    @PostMapping("/guardar")
    public String guardarEvento(@ModelAttribute("evento") Evento evento) {
        eventoService.save(evento);  // Llamar al servicio para guardar el evento
        return "redirect:/eventos";  // Redirigir a la lista de eventos
    }

    // Mostrar el formulario para editar un evento existente
@GetMapping("/editar/{id}")
public String mostrarFormularioEditarEvento(@PathVariable("id") Long id, Model model) {
    // Obtener el evento usando el ID proporcionado
    Evento evento = eventoService.getEvento(id); // Usamos solo el ID
    if (evento != null) {
        model.addAttribute("evento", evento);
        return "evento/formulario";  // Nombre de la vista Thymeleaf (evento/formulario.html)
    } else {
        // Si el evento no se encuentra, redirigir a la lista de eventos con un mensaje de error
        model.addAttribute("error", "Evento no encontrado.");
        return "redirect:/eventos";  // Redirigir a la lista de eventos
    }
}

// Eliminar un evento
@GetMapping("/eliminar/{id}")
public String eliminarEvento(@PathVariable("id") Long id) {
    // Obtener el evento usando el ID proporcionado
    Evento evento = eventoService.getEvento(id); // Usamos solo el ID
    if (evento != null) {
        eventoService.delete(evento);  // Llamar al servicio para eliminar el evento
    }
    return "redirect:/eventos";  // Redirigir a la lista de eventos
}
@GetMapping("/eventos")
    public String verEventos(Model model,
                             @RequestParam(required = false) String tipo,
                             @RequestParam(required = false) String fechaInicio,
                             @RequestParam(required = false) String fechaFin) {
        
        LocalDate fechaInicioDate = null;
        LocalDate fechaFinDate = null;
        
        // Convertir las fechas de String a LocalDate
        try {
            if (fechaInicio != null && !fechaInicio.isEmpty()) {
                fechaInicioDate = LocalDate.parse(fechaInicio);
            }
            if (fechaFin != null && !fechaFin.isEmpty()) {
                fechaFinDate = LocalDate.parse(fechaFin);
            }
        } catch (DateTimeParseException e) {
            model.addAttribute("error", "Formato de fecha incorrecto. Use el formato yyyy-MM-dd.");
            return "eventos"; // Redirige a la vista con un error.
        }

        // Llamar al servicio para obtener los eventos filtrados
        List<Evento> eventos = eventoService.filtrarEventos(tipo, fechaInicioDate, fechaFinDate);

        // Añadir los eventos y los filtros al modelo
        model.addAttribute("eventos", eventos);
        model.addAttribute("tipo", tipo);
        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("fechaFin", fechaFin);

        return "eventos"; // El nombre de la vista Thymeleaf que se mostrará
    }
}
