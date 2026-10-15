package controller;

import service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/registros")
@CrossOrigin(origins = "*") // Permite solicitudes de cualquier origen en desarrollo
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @GetMapping("/comparar")
    public Map<String, Object> compararTiempos() {
        return registroService.compararTiempos();
    }
}