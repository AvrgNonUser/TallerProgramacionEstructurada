package service;

import model.Registro;
import repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository repository;

    // Método SIN caché: Consulta a la base de datos cada vez que se invoca
    public List<Registro> obtenerSinCache() {
        return repository.findAll();
    }

    // Método CON caché: La primera vez va a SQL Server; las siguientes devuelve la memoria RAM
    @Cacheable("registrosCache")
    public List<Registro> obtenerConCache() {
        return repository.findAll();
    }

    // Método para comparar los tiempos de acceso
    public Map<String, Object> compararTiempos() {
        // 1. Medir acceso directo a base de datos (Sin Caché)
        long inicioSin = System.currentTimeMillis();
        List<Registro> listaSinCache = obtenerSinCache();
        long tiempoSinCache = System.currentTimeMillis() - inicioSin;

        // 2. Medir acceso usando Caché (para que la caché se llene, asegurate de invocar o simular,
        // o si es la segunda petición notarás el impacto. Aquí medimos la llamada consecutiva).
        // Forzamos una primera llamada si estuviera vacía, o evaluamos directamente:
        obtenerConCache(); // Llena la caché si no estaba llena

        long inicioCon = System.currentTimeMillis();
        List<Registro> listaConCache = obtenerConCache();
        long tiempoConCache = System.currentTimeMillis() - inicioCon;

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("totalRegistros", listaSinCache.size());
        resultado.put("tiempoSinCacheMs", tiempoSinCache);
        resultado.put("tiempoConCacheMs", tiempoConCache);

        double mejora = tiempoSinCache > 0 ? ((double)(tiempoSinCache - tiempoConCache) / tiempoSinCache) * 100 : 0;
        resultado.put("mejoraPorcentaje", String.format("%.2f%%", mejora));

        return resultado;
    }
}
