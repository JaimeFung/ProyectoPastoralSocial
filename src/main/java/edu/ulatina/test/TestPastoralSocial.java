package edu.ulatina.test;

import edu.ulatina.controller.*;
import edu.ulatina.model.*;
import edu.ulatina.util.JPAUtil;
import edu.ulatina.util.DataInitializer;
import edu.ulatina.util.StringUtils;
import java.util.List;
import java.util.Scanner;

public class TestPastoralSocial {
    private static Scanner scanner = new Scanner(System.in);
    private static UsuarioController usuarioController = new UsuarioController();
    private static FamiliaController familiaController = new FamiliaController();
    private static ExpedienteController expedienteController = new ExpedienteController();
    private static Usuario usuarioLogueado = null;
    
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  SISTEMA DE GESTIÓN - PASTORAL SOCIAL");
        System.out.println("  Diócesis de Cartago");
        System.out.println("═══════════════════════════════════════════════════════════\n");
        
        try {
            // Verificar conexión
            if (JPAUtil.isFactoryOpen()) {
                System.out.println("✓ Conexión a base de datos establecida");
                System.out.println("✓ Hibernate creará/actualizará las tablas automáticamente\n");
            }
            
            // IMPORTANTE: Inicializar datos básicos si es la primera vez
            inicializarSistema();
            
            // Menú principal
            boolean continuar = true;
            while (continuar) {
                if (usuarioLogueado == null) {
                    mostrarMenuLogin();
                    int opcion = leerOpcion();
                    switch (opcion) {
                        case 1:
                            login();
                            break;
                        case 2:
                            testearConexion();
                            break;
                        case 3:
                            continuar = false;
                            break;
                        default:
                            System.out.println("⚠ Opción inválida");
                    }
                } else {
                    mostrarMenuPrincipal();
                    int opcion = leerOpcion();
                    procesarOpcionPrincipal(opcion);
                    if (opcion == 0) {
                        continuar = false;
                    }
                }
            }
            
        } catch (Exception e) {
            System.err.println("✗ Error crítico: " + e.getMessage());
            e.printStackTrace();
        } finally {
            JPAUtil.closeEntityManagerFactory();
            scanner.close();
            System.out.println("\n✓ Sistema finalizado correctamente");
        }
    }
    
    /**
     * Inicializa datos básicos del sistema si es la primera ejecución
     */
    private static void inicializarSistema() {
        try {
            DataInitializer initializer = new DataInitializer();
            initializer.inicializarDatos();
        } catch (Exception e) {
            System.err.println("⚠ Error al inicializar datos: " + e.getMessage());
            System.out.println("El sistema continuará pero puede que no haya datos iniciales.");
        }
    }
    
    private static void mostrarMenuLogin() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║          MENÚ DE ACCESO                ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ 1. Iniciar Sesión                      ║");
        System.out.println("║ 2. Probar Conexión                     ║");
        System.out.println("║ 3. Salir                               ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println("\n💡 Credenciales por defecto:");
        System.out.println("   Usuario: admin  |  Contraseña: admin123");
        System.out.print("\nSeleccione opción: ");
    }
    
    private static void mostrarMenuPrincipal() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        MENÚ PRINCIPAL                  ║");
        System.out.println("║  Usuario: " + String.format("%-28s", usuarioLogueado.getNombreCompleto()) + "║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ 1. Registrar Usuario                   ║");
        System.out.println("║ 2. Listar Usuarios                     ║");
        System.out.println("║ 3. Registrar Familia                   ║");
        System.out.println("║ 4. Listar Familias                     ║");
        System.out.println("║ 5. Buscar Familia                      ║");
        System.out.println("║ 6. Ver Expediente de Familia           ║");
        System.out.println("║ 7. Listar Expedientes                  ║");
        System.out.println("║ 8. Cerrar Sesión                       ║");
        System.out.println("║ 0. Salir                               ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.print("Seleccione opción: ");
    }
    
    private static void login() {
        System.out.println("\n--- INICIAR SESIÓN ---");
        System.out.print("Usuario: ");
        String username = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        
        usuarioLogueado = usuarioController.login(username, password);
        
        if (usuarioLogueado != null) {
            System.out.println("\n✓ Bienvenido(a), " + usuarioLogueado.getNombreCompleto());
            System.out.println("  Rol: " + usuarioLogueado.getRol());
        } else {
            System.out.println("\n✗ Credenciales incorrectas");
        }
    }
    
    private static void procesarOpcionPrincipal(int opcion) {
        switch (opcion) {
            case 1:
                registrarUsuario();
                break;
            case 2:
                listarUsuarios();
                break;
            case 3:
                registrarFamilia();
                break;
            case 4:
                listarFamilias();
                break;
            case 5:
                buscarFamilia();
                break;
            case 6:
                verExpediente();
                break;
            case 7:
                listarExpedientes();
                break;
            case 8:
                usuarioLogueado = null;
                System.out.println("✓ Sesión cerrada");
                break;
            case 0:
                System.out.println("Cerrando sistema...");
                break;
            default:
                System.out.println("⚠ Opción inválida");
        }
    }
    
    private static void registrarUsuario() {
        System.out.println("\n--- REGISTRAR NUEVO USUARIO ---");
        System.out.print("Nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        System.out.print("Nombre completo: ");
        String nombreCompleto = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.println("Roles: 1.ADMIN 2.COORDINADOR 3.VOLUNTARIO");
        System.out.print("Seleccione rol: ");
        int rol = leerOpcion();
        
        Usuario.Rol rolSeleccionado = Usuario.Rol.VOLUNTARIO;
        switch (rol) {
            case 1: rolSeleccionado = Usuario.Rol.ADMIN; break;
            case 2: rolSeleccionado = Usuario.Rol.COORDINADOR; break;
            case 3: rolSeleccionado = Usuario.Rol.VOLUNTARIO; break;
        }
        
        Usuario usuario = usuarioController.registrarUsuario(username, password, nombreCompleto, rolSeleccionado, email);
        if (usuario != null) {
            System.out.println("\n✓ Usuario registrado exitosamente");
        }
    }
    
    private static void listarUsuarios() {
        System.out.println("\n--- LISTA DE USUARIOS ---");
        List<Usuario> usuarios = usuarioController.listarTodos();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados");
        } else {
            System.out.println(String.format("%-5s %-20s %-30s %-15s %-10s", 
                              "ID", "Usuario", "Nombre", "Rol", "Activo"));
            
            for (Usuario u : usuarios) {
                System.out.println(String.format("%-5d %-20s %-30s %-15s %-10s",
                    u.getIdUsuario(), u.getNombreUsuario(), u.getNombreCompleto(),
                    u.getRol(), u.getActivo() ? "Sí" : "No"));
            }
        }
    }
    
    private static void registrarFamilia() {
        System.out.println("\n--- REGISTRAR NUEVA FAMILIA ---");
        System.out.print("ID de Parroquia: ");
        int idParroquia = leerOpcion();
        System.out.print("Número de Expediente: ");
        String numExpediente = scanner.nextLine();
        System.out.print("Nombre Jefe de Familia: ");
        String nombreJefe = scanner.nextLine();
        System.out.print("Cédula/Identificación: ");
        String identificacion = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.println("Situación Económica: 1.MUY_BAJA 2.BAJA 3.MEDIA 4.ESTABLE");
        System.out.print("Seleccione: ");
        int sit = leerOpcion();
        
        Familia.SituacionEconomica situacion = Familia.SituacionEconomica.BAJA;
        switch (sit) {
            case 1: situacion = Familia.SituacionEconomica.MUY_BAJA; break;
            case 2: situacion = Familia.SituacionEconomica.BAJA; break;
            case 3: situacion = Familia.SituacionEconomica.MEDIA; break;
            case 4: situacion = Familia.SituacionEconomica.ESTABLE; break;
        }
        
        System.out.print("Observaciones: ");
        String observaciones = scanner.nextLine();
        System.out.print("Motivo de apertura de expediente: ");
        String motivo = scanner.nextLine();
        
        Familia familia = familiaController.registrarFamilia(idParroquia, numExpediente, nombreJefe,
                identificacion, direccion, telefono, situacion, observaciones, motivo);
        
        if (familia != null) {
            System.out.println("\n✓ Familia registrada exitosamente");
            System.out.println("  Expediente: " + familia.getNumeroExpediente());
        }
    }
    
    private static void listarFamilias() {
        System.out.println("\n--- LISTA DE FAMILIAS ACTIVAS ---");
        List<Familia> familias = familiaController.listarActivas();
        if (familias.isEmpty()) {
            System.out.println("No hay familias registradas");
        } else {
            System.out.println(String.format("%-5s %-15s %-30s %-20s %-12s", 
                              "ID", "Expediente", "Jefe Familia", "Cédula", "Situación"));
            
            for (Familia f : familias) {
                System.out.println(String.format("%-5d %-15s %-30s %-20s %-12s",
                    f.getIdFamilia(), f.getNumeroExpediente(), f.getNombreJefeFamilia(),
                    f.getIdentificacionJefe(), f.getSituacionEconomica()));
            }
            System.out.println("\nTotal de familias: " + familias.size());
        }
    }
    
    private static void buscarFamilia() {
        System.out.println("\n--- BUSCAR FAMILIA ---");
        System.out.print("Ingrese nombre a buscar: ");
        String nombre = scanner.nextLine();
        
        List<Familia> familias = familiaController.buscarPorNombre(nombre);
        if (familias.isEmpty()) {
            System.out.println("No se encontraron familias");
        } else {
            for (Familia f : familias) {
                System.out.println("\n" + f);
                System.out.println("  Parroquia: " + f.getParroquia().getNombre());
                System.out.println("  Dirección: " + f.getDireccion());
                System.out.println("  Teléfono: " + f.getTelefono());
            }
        }
    }
    
    private static void verExpediente() {
        System.out.println("\n--- VER EXPEDIENTE ---");
        System.out.print("ID de Familia: ");
        int idFamilia = leerOpcion();
        
        Expediente exp = expedienteController.buscarPorFamilia(idFamilia);
        if (exp != null) {
            System.out.println("\n" + exp);
            System.out.println("Familia: " + exp.getFamilia().getNombreJefeFamilia());
            System.out.println("Motivo: " + exp.getMotivoApertura());
            System.out.println("Notas: " + exp.getNotasGenerales());
        } else {
            System.out.println("No se encontró expediente para esta familia");
        }
    }
    
    private static void listarExpedientes() {
        System.out.println("\n--- LISTA DE EXPEDIENTES ---");
        List<Expediente> expedientes = expedienteController.listarTodos();
        if (expedientes.isEmpty()) {
            System.out.println("No hay expedientes registrados");
        } else {
            System.out.println(String.format("%-5s %-30s %-12s %-10s %-15s", 
                              "ID", "Familia", "Fecha", "Estado", "Total Ayudas"));
            
            for (Expediente e : expedientes) {
                System.out.println(String.format("%-5d %-30s %-12s %-10s %-15d",
                    e.getIdExpediente(), 
                    e.getFamilia().getNombreJefeFamilia(),
                    e.getFechaApertura(),
                    e.getEstado(),
                    e.getTotalAyudasRecibidas()));
            }
        }
    }
    
    private static void testearConexion() {
        System.out.println("\n--- PRUEBA DE CONEXIÓN ---");
        long totalUsuarios = usuarioController.listarTodos().size();
        System.out.println("Total de usuarios en BD: " + totalUsuarios);
        long totalFamilias = familiaController.contarFamilias();
        System.out.println("Total de familias en BD: " + totalFamilias);
        System.out.println("✓ Conexión funcionando correctamente");
    }
    
    private static int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}