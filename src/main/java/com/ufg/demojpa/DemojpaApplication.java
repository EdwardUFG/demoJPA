package com.ufg.demojpa;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.ufg.demojpa.model.Categoria;
import com.ufg.demojpa.model.Trip;
import com.ufg.demojpa.repository.ICategoriasRepository;
import com.ufg.demojpa.repository.ITripRepository;
import com.ufg.demojpa.model.Perfil;
import com.ufg.demojpa.model.Usuario;
import com.ufg.demojpa.repository.IPerfilesRepository;
import com.ufg.demojpa.repository.IUsuariosRepository;
import java.util.ArrayList;
import java.util.LinkedList;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


@SpringBootApplication
public class DemojpaApplication implements CommandLineRunner {

    @Autowired
    private ICategoriasRepository repoCategoria;
    
    @Autowired
    private ITripRepository repoTrip;    
    
    @Autowired
    private IUsuariosRepository repoUsuario;

    @Autowired
    private IPerfilesRepository repoPerfil;

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
         //antidadCategorias();
        //eliminarTodo();
        //encontrarPorIds();
        //buscarTodosJpa();
        //borrarEnBatch();
        //buscarTodosOrdenados();
        //buscarTodoEnPaginacion();
        //guardarTrip();
        //crearUsuarioConPerfiles();
        //buscarTripsPorEstatus();
        //buscarTripsPorEstatusYDestacado();
        //buscarTripsPorRangoDePrecio();
        buscarTripsPorEstatusIn();
        buscarTripsPorEstatusOrdenados();
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
        Page<Categoria> page = repoCategoria.findAll(PageRequest.of(0, 5));
        
        System.out.println("Total Categorias: " + page.getTotalElements());
        System.out.println("Total paginas: " + page.getTotalPages());
        
        for (Categoria cat : page) {
            System.out.println(cat.getId() + " - " + cat.getNombre());
        }
    }
    private void guardarTrip() {
        Trip trip = new Trip();
        trip.setNombre("Volcan de Santa Ana");
        trip.setDescripcion("Caminata al crater");
        trip.setFecha(new java.util.Date());
        trip.setCosto(15.50);
        trip.setEstatus("Activo");
        trip.setDestacado(1);
        trip.setImagen("volcan.png");
        trip.setDetalles("Incluye guia y transporte");


        java.util.Optional<Categoria> catOptional = repoCategoria.findById(1);
        if(catOptional.isPresent()){

            trip.setCategoria(catOptional.get());
        }


        repoTrip.save(trip);
        System.out.println("Trip guardado con la categoria asociada exitosamente.");
    }
    private void crearUsuarioConPerfiles() {
        Perfil perfilAdmin = new Perfil();
        perfilAdmin.setPerfil("Administrador");
        repoPerfil.save(perfilAdmin);

        Perfil perfilUser = new Perfil();
        perfilUser.setPerfil("Usuario");
        repoPerfil.save(perfilUser);

        Usuario usuario = new Usuario();
        usuario.setNombre("Edward Perez");
        usuario.setEmail("edward@example.com");
        usuario.setPassword("12345");
        usuario.setEstatus("Activo");
        usuario.setFecha(new java.util.Date());

        ArrayList<Perfil> listaPerfiles = new ArrayList<>();
        listaPerfiles.add(perfilAdmin);
        listaPerfiles.add(perfilUser);
        
        usuario.setPerfiles(listaPerfiles);
        repoUsuario.save(usuario);
        System.out.println("Usuario guardado con perfiles exitosamente.");
    }
    private void buscarTripsPorEstatus() {
        List<Trip> trips = repoTrip.findByEstatus("Activo");
        System.out.println("Trips encontrados con estatus Activo:");
        for (Trip t : trips) {
            System.out.println(t.getId() + " - " + t.getNombre() + " | Costo: $" + t.getCosto());
        }
    }
    private void buscarTripsPorEstatusYDestacado() {
        List<Trip> trips = repoTrip.findByEstatusAndDestacado("Activo", 1);
        System.out.println("--- Trips Activos y Destacados ---");
        for (Trip t : trips) {
            System.out.println(t.getNombre() + " | Destacado: " + t.getDestacado());
        }
    }

    private void buscarTripsPorRangoDePrecio() {
        List<Trip> trips = repoTrip.findByCostoBetween(10.0, 50.0);
        System.out.println("--- Trips entre $10 y $50 ---");
        for (Trip t : trips) {
            System.out.println(t.getNombre() + " | Costo: $" + t.getCosto());
        }
    }
    private void buscarTripsPorEstatusIn() {
        List<String> listaEstatus = new java.util.LinkedList<>();
        listaEstatus.add("Activo");
        listaEstatus.add("Inactivo");
        
        List<Trip> trips = repoTrip.findByEstatusIn(listaEstatus);
        System.out.println("--- Trips con estatus Activo o Inactivo ---");
        for (Trip t : trips) {
            System.out.println(t.getNombre() + " | Estatus: " + t.getEstatus());
        }
    }

    private void buscarTripsPorEstatusOrdenados() {
        List<Trip> trips = repoTrip.findByEstatusOrderByIdDesc("Activo");
        System.out.println("--- Trips Activos ordenados por ID Descendente ---");
        for (Trip t : trips) {
            System.out.println("ID: " + t.getId() + " - " + t.getNombre());
        }
    }
}