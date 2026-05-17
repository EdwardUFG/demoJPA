package com.ufg.demojpa;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ufg.demojpa.model.Categoria;
import com.ufg.demojpa.repository.ICategoriasRepository;
import java.util.LinkedList;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class DemojpaApplication implements CommandLineRunner {

    @Autowired
    private ICategoriasRepository repoCategoria;

    public static void main(String[] args) {
        SpringApplication.run(DemojpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        testConexion();
        //guardar();
        // buscarPorId();
        // modificar();
        // eliminarPorId();
         cantidadCategorias();
        //eliminarTodo();
        //encontrarPorIds();
        //buscarTodosJpa();
        //borrarEnBatch();
        //buscarTodosOrdenados();
        //buscarTodoEnPaginacion();
    }

    private void testConexion() {
        if (repoCategoria != null) {
            System.out.println("Conexion exitosa: " + repoCategoria);
        } else {
            System.out.println("Error en la conexion");
        }
    }

    private void guardar() {
        Categoria categoria = new Categoria();
        categoria.setNombre("Caminata en el volcan");
        categoria.setDescripcion("Caminata de dificultad media, para personas con buena condicion fisica");
        repoCategoria.save(categoria);
    }

    private void buscarPorId() {
        Optional<Categoria> optional = repoCategoria.findById(1);
        if (optional.isPresent()) {
            System.out.println(optional.get().getNombre());
        } else {
            System.out.println("Categoria no encontrada");
        }
    }

    private void modificar() {
        Optional<Categoria> optional = repoCategoria.findById(1);
        if (optional.isPresent()) {
            Categoria catTemp = optional.get();
            catTemp.setNombre("Caminatas en el Volcan");
            catTemp.setDescripcion("Exigentes caminatas para profesionales");
            repoCategoria.save(catTemp);
            System.out.println("Categoria modificada: " + optional.get().getNombre());
        } else {
            System.out.println("Categoria no encontrada");
        }
    }
    private void eliminarPorId() {
        repoCategoria.deleteById(1);
        System.out.println("Registro eliminado exitosamente");
    }
    private void cantidadCategorias() {
        long cantidad = repoCategoria.count();
        System.out.println("Cantidad: " + cantidad);
    }
    private void eliminarTodo() {
        repoCategoria.deleteAll();
        System.out.println("Todos los registros eliminados");
    }
    private void encontrarPorIds() {
        List<Integer> ids = new LinkedList<Integer>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        Iterable<Categoria> categorias = repoCategoria.findAllById(ids);
        for (Categoria cat : categorias) {
            System.out.println(cat.getNombre() + " | " + cat.getDescripcion());
        }
    }
    private void buscarTodosJpa() {
        List<Categoria> categorias = repoCategoria.findAll();
        for (Categoria cat : categorias) {
            System.out.println(cat.getId() + " - " + cat.getNombre());
        }
    }
    private void borrarEnBatch() {
        repoCategoria.deleteAllInBatch();
        System.out.println("Todos los registros eliminados de golpe (Batch)");
    }
    private void buscarTodosOrdenados() {
        List<Categoria> categorias = repoCategoria.findAll(Sort.by("nombre"));
        for (Categoria cat : categorias) {
            System.out.println(cat.getId() + " - " + cat.getNombre());
        }
    }
    private void buscarTodoEnPaginacion() {
        // Solicitamos la página 0 con un tamaño de 5 registros
        Page<Categoria> page = repoCategoria.findAll(PageRequest.of(0, 5));
        
        System.out.println("Total Categorias: " + page.getTotalElements());
        System.out.println("Total paginas: " + page.getTotalPages());
        
        for (Categoria cat : page) {
            System.out.println(cat.getId() + " - " + cat.getNombre());
        }
    }
}