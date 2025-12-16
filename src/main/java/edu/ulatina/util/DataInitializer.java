package edu.ulatina.util;


import edu.ulatina.controller.*;
import edu.ulatina.model.*;

public class DataInitializer {
    
    private UsuarioController usuarioController;
    private ParroquiaController parroquiaController;
    private TipoAyudaController tipoAyudaController;
    
    public DataInitializer() {
        this.usuarioController = new UsuarioController();
        this.parroquiaController = new ParroquiaController();
        this.tipoAyudaController = new TipoAyudaController();
    }
    
    /**
     * Inicializa todos los datos básicos del sistema
     */
    public void inicializarDatos() {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("  INICIALIZANDO DATOS DEL SISTEMA");
        System.out.println("═══════════════════════════════════════════\n");
        
        // Verificar si ya existen datos
        if (usuarioController.listarTodos().size() > 0) {
            System.out.println("⚠ Ya existen datos en el sistema. Saltando inicialización.");
            return;
        }
        
        crearUsuariosIniciales();
        crearParroquiasIniciales();
        crearTiposAyudaIniciales();
        
        System.out.println("\n✓ Datos iniciales cargados exitosamente\n");
    }
    
    /**
     * Crea usuarios por defecto del sistema
     */
    private void crearUsuariosIniciales() {
        
    }
    
    /**
     * Crea parroquias de la Diócesis
     */
    private void crearParroquiasIniciales() {
        System.out.println("→ Creando parroquias iniciales...");
        
        parroquiaController.registrarParroquia(
            "Parroquia San José",
            "Cartago Centro, frente al parque",
            "2551-1234",
            "Padre Juan Pérez"
        );
        
        parroquiaController.registrarParroquia(
            "Parroquia Nuestra Señora de los Ángeles",
            "Barrio Los Ángeles, Cartago",
            "2552-5678",
            "Padre Carlos Mora"
        );
        
        parroquiaController.registrarParroquia(
            "Parroquia Cristo Rey",
            "Paraíso de Cartago",
            "2574-9012",
            "Padre Luis Castro"
        );
        
        parroquiaController.registrarParroquia(
            "Parroquia San Rafael",
            "Tierra Blanca, Cartago",
            "2536-7890",
            "Padre Miguel Sánchez"
        );
        
        System.out.println("  ✓ 4 parroquias creadas");
    }
    
    /**
     * Crea catálogo de tipos de ayuda
     */
    private void crearTiposAyudaIniciales() {
        System.out.println("→ Creando tipos de ayuda iniciales...");
        
        // ALIMENTOS
        tipoAyudaController.registrarTipoAyuda(
            "Paquete Alimenticio Básico",
            "Arroz, frijoles, aceite, azúcar, sal",
            TipoAyuda.Categoria.ALIMENTOS
        );
        
        tipoAyudaController.registrarTipoAyuda(
            "Paquete Alimenticio Completo",
            "Alimentos básicos + enlatados + productos de higiene",
            TipoAyuda.Categoria.ALIMENTOS
        );
        
        tipoAyudaController.registrarTipoAyuda(
            "Alimentos Perecederos",
            "Frutas, verduras, carnes, lácteos",
            TipoAyuda.Categoria.ALIMENTOS
        );
        
        // MEDICAMENTOS
        tipoAyudaController.registrarTipoAyuda(
            "Medicamentos Generales",
            "Analgésicos, antipiréticos, antigripales básicos",
            TipoAyuda.Categoria.MEDICAMENTOS
        );
        
        tipoAyudaController.registrarTipoAyuda(
            "Medicamentos Especializados",
            "Medicamentos de prescripción médica",
            TipoAyuda.Categoria.MEDICAMENTOS
        );
        
        // ECONÓMICO
        tipoAyudaController.registrarTipoAyuda(
            "Apoyo Económico Mensual",
            "Ayuda monetaria mensual para gastos básicos",
            TipoAyuda.Categoria.ECONOMICO
        );
        
        tipoAyudaController.registrarTipoAyuda(
            "Apoyo Económico Emergencia",
            "Ayuda monetaria para situaciones urgentes",
            TipoAyuda.Categoria.ECONOMICO
        );
        
        tipoAyudaController.registrarTipoAyuda(
            "Apoyo Servicios Básicos",
            "Pago de luz, agua, alquiler",
            TipoAyuda.Categoria.ECONOMICO
        );
        
        // ACTIVIDADES
        tipoAyudaController.registrarTipoAyuda(
            "Actividad Navideña",
            "Regalos y actividades navideñas para niños",
            TipoAyuda.Categoria.ACTIVIDAD
        );
        
        tipoAyudaController.registrarTipoAyuda(
            "Útiles Escolares",
            "Materiales escolares para inicio de clases",
            TipoAyuda.Categoria.ACTIVIDAD
        );
        
        tipoAyudaController.registrarTipoAyuda(
            "Actividades Recreativas",
            "Paseos, eventos deportivos, culturales",
            TipoAyuda.Categoria.ACTIVIDAD
        );
        
        // OTROS
        tipoAyudaController.registrarTipoAyuda(
            "Ropa y Calzado",
            "Prendas de vestir y calzado para la familia",
            TipoAyuda.Categoria.OTRO
        );
        
        tipoAyudaController.registrarTipoAyuda(
            "Artículos de Limpieza",
            "Productos de limpieza para el hogar",
            TipoAyuda.Categoria.OTRO
        );
        
        tipoAyudaController.registrarTipoAyuda(
            "Muebles y Enseres",
            "Muebles básicos y electrodomésticos",
            TipoAyuda.Categoria.OTRO
        );
        
        tipoAyudaController.registrarTipoAyuda(
            "Otro Tipo de Ayuda",
            "Ayudas no categorizadas anteriormente",
            TipoAyuda.Categoria.OTRO
        );
        
        System.out.println("  ✓ 15 tipos de ayuda creados");
    }
    
    /**
     * Crea datos de prueba para testing (opcional)
     */
    public void crearDatosDePrueba() {
        System.out.println("\n→ Creando datos de prueba...");
        
        // Aquí puedes agregar familias de ejemplo si quieres
        // Por ahora lo dejamos vacío
        
        System.out.println("  ✓ Datos de prueba creados");
    }
}
