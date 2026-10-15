package component;

import model.Registro;
import repository.RegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RegistroRepository repository;

    @Override
    public void run(String... args) throws Exception {
        // Verificamos si la tabla está vacía para no duplicar datos cada vez que reinicies
        if (repository.count() == 0) {
            System.out.println("--> Iniciando carga masiva de 50,000 registros a SQL Server...");
            long inicio = System.currentTimeMillis();

            List<Registro> lista = new ArrayList<>();
            for (int i = 1; i <= 50000; i++) {
                lista.add(new Registro("COD-" + i, "Descripción del registro masivo número " + i, Math.random() * 1000));
            }

            repository.saveAll(lista);

            long fin = System.currentTimeMillis();
            System.out.println("--> ¡50,000 registros insertados exitosamente en " + (fin - inicio) + " ms!");
        } else {
            System.out.println("--> La base de datos ya contiene registros. Omitiendo carga masiva.");
        }
    }
}