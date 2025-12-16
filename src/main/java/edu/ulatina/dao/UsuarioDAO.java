package edu.ulatina.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.List;
import java.util.Properties;
import edu.ulatina.model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario, Integer> {
    
    public UsuarioDAO() {
        super(Usuario.class);
    }
    
    // Buscar usuario por nombre de usuario
    public Usuario findByUsername(String nombreUsuario) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT u FROM Usuario u WHERE u.nombreUsuario = :username AND u.activo = true";
            TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
            query.setParameter("username", nombreUsuario);
            Usuario usuario = query.getSingleResult();
            System.out.println("✓ Usuario encontrado: " + usuario.getNombreCompleto());
            return usuario;
        } catch (NoResultException e) {
            System.out.println("⚠ No se encontró usuario con username: " + nombreUsuario);
            return null;
        } catch (Exception e) {
            System.err.println("✗ Error al buscar usuario: " + e.getMessage());
            throw new RuntimeException("Error al buscar usuario", e);
        } finally {
            em.close();
        }
    }
    
    public static void enviarRecuperacion(String destino, String token) {

        final String from = "jaimefungdel@gmail.com";
        final String password = "mzkumurxhepulcxm";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });
        
        

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destino)
            );

            message.setSubject("Recuperación de contraseña");

            String link = "http://localhost:8081/Pastoral_Social/reset.jsp?token=" + token;

            message.setText(
                "Para restablecer su contraseña, haga clic en el siguiente enlace:\n\n" + link
            );

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Usuario> findActivos() {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.activo = true ORDER BY u.nombreCompleto", 
                Usuario.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    public Usuario findByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            return em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.email = :email",
                    Usuario.class
            )
            .setParameter("email", email)
            .getSingleResult();

        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public Usuario findByResetToken(String token) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuario> q = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.resetToken = :token",
                Usuario.class
            );
            q.setParameter("token", token);
            return q.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }


    
    // Validar credenciales de login
    public Usuario login(String usuarioOEmail, String contrasena) {
        EntityManager em = getEntityManager();
        try {
            String jpql =
                "SELECT u FROM Usuario u " +
                "WHERE (u.nombreUsuario = :valor OR u.email = :valor) " +
                "AND u.contrasena = :password " +
                "AND u.activo = true";

            TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
            query.setParameter("valor", usuarioOEmail);
            query.setParameter("password", contrasena);

            Usuario usuario = query.getSingleResult();
            System.out.println("✓ Login exitoso: " + usuario.getNombreCompleto());
            return usuario;

        } catch (NoResultException e) {
            System.out.println("✗ Credenciales inválidas para: " + usuarioOEmail);
            return null;

        } catch (Exception e) {
            System.err.println("✗ Error en login: " + e.getMessage());
            throw new RuntimeException("Error en login", e);

        } finally {
            em.close();
        }
    }

    
    // Listar usuarios por rol
    public java.util.List<Usuario> findByRol(Usuario.Rol rol) {
        EntityManager em = getEntityManager();
        try {
            String jpql = "SELECT u FROM Usuario u WHERE u.rol = :rol AND u.activo = true";
            TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
            query.setParameter("rol", rol);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("✗ Error al buscar usuarios por rol: " + e.getMessage());
            throw new RuntimeException("Error al buscar usuarios por rol", e);
        } finally {
            em.close();
        }
    }
}
