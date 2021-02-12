package dao;

import beans.Comentario;
import beans.Imagen;
import beans.PasswordUtils;
import beans.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoGaleria {
        public static Connection conexion(){
        
        Connection cn=null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("jdbc/galeria");
            
            cn = ds.getConnection();
        } catch (NamingException ex) {
            Logger.getLogger(DaoGaleria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DaoGaleria.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cn;
    }
    
    public static void devolverConexion(Connection cn){
        try {
            //Devuelve conexion al pool
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DaoGaleria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Usuario usuario(String username, String pass) {
        
        Usuario usuario = null;
        
        Connection cn=conexion();
        
        try {


            String sql="SELECT iduser, nombre, apellidos, username, password, salt, correo FROM usuarios WHERE username='"+username+"'";

            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);

            if(rs.next()){
                
                String passw = rs.getString("password");
                
                String salt = rs.getString("salt");
                
                boolean passwordMatch = PasswordUtils.verifyUserPassword(pass, passw, salt);
                
                if (passwordMatch) {
                    usuario = new Usuario();
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setIduser(rs.getInt("iduser"));
                    usuario.setApellidos(rs.getString("apellidos"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setEmail(rs.getString("correo"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoGaleria.class.getName()).log(Level.SEVERE, null, ex);
        }

        devolverConexion(cn);

        return usuario;
    }
    
    public static boolean registro(Usuario usuario) {
        
        Connection cn=conexion();

        try {


            String sql="SELECT iduser, nombre, apellidos, username, correo FROM usuarios WHERE username='"+usuario.getUsername()+"' OR correo='"+usuario.getEmail()+"'";

            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);

            if(rs.next()){
                return false;
            }
            rs.close();
            
            String pass = usuario.getPass();
            
            String salt = PasswordUtils.getSalt(30);
            
            String mySecurePassword = PasswordUtils.generateSecurePassword(pass, salt);
            
            sql = "INSERT INTO usuarios (nombre, apellidos, username, password, salt, correo) VALUES ('"+usuario.getNombre()+"','"+usuario.getApellidos()+"','"+usuario.getUsername()+"','"+mySecurePassword+"','"+salt+"','"+usuario.getEmail()+"')";
            st.executeUpdate(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoGaleria.class.getName()).log(Level.SEVERE, null, ex);
        }           

       devolverConexion(cn);
        
        
        return true;
    }
    
    public static ArrayList<Imagen> fotos() {
        
        ArrayList<Imagen> fotos = new ArrayList<Imagen>();
        
        Connection cn=conexion();
        
        try {


            String sql="SELECT idfoto, titulo, narchivo, descripcion, fotos.iduser, usuarios.username FROM fotos LEFT JOIN usuarios ON fotos.iduser = usuarios.iduser";

            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);

            while(rs.next()){
                Imagen i = new Imagen();
                i.setIdfoto(rs.getInt("idfoto"));
                i.setTitulo(rs.getString("titulo"));
                i.setNarchivo(rs.getString("narchivo"));
                i.setDescripcion(rs.getString("descripcion"));
                i.setIduser(rs.getInt("iduser"));
                i.setNusuario(rs.getString("username"));
                
                fotos.add(i);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoGaleria.class.getName()).log(Level.SEVERE, null, ex);
        }

        devolverConexion(cn);

        System.out.println(fotos.size());
        
        return fotos;
    }
    
    public static ArrayList<Imagen> fotosDe(String nusuario) {
        
        ArrayList<Imagen> fotos = new ArrayList<Imagen>();
        
        Connection cn=conexion();
        
        
        try {


            String sql="SELECT idfoto, titulo, narchivo, descripcion, fotos.iduser, usuarios.username FROM fotos LEFT JOIN usuarios ON fotos.iduser = usuarios.iduser WHERE usuarios.username = '"+nusuario+"'";

            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);

            while(rs.next()){
                Imagen i = new Imagen();
                i.setIdfoto(rs.getInt("idfoto"));
                i.setTitulo(rs.getString("titulo"));
                i.setNarchivo(rs.getString("narchivo"));
                i.setDescripcion(rs.getString("descripcion"));
                i.setIduser(rs.getInt("iduser"));
                i.setNusuario(rs.getString("username"));
                
                fotos.add(i);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoGaleria.class.getName()).log(Level.SEVERE, null, ex);
        }

        devolverConexion(cn);

        
        return fotos;
    }
    
    
    public static ArrayList<Comentario> comentariosDe(int idfoto) {
        
        ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
        
        Connection cn=conexion();
        
        try {


            String sql="SELECT idcomentario, comentario, fechacom, idfoto, username FROM comentarios LEFT JOIN usuarios ON comentarios.iduser = usuarios.iduser WHERE idfoto = "+idfoto+"";

            Statement st=cn.createStatement();
            ResultSet rs=st.executeQuery(sql);

            while(rs.next()){
                Comentario c = new Comentario();
                c.setIdcomentario(rs.getInt("idcomentario"));
                c.setComentario(rs.getString("comentario"));
                c.setFechacom(new java.util.Date(rs.getTimestamp("fechacom").getTime()));
                c.setIdfoto(rs.getInt("idfoto"));
                c.setNusuario(rs.getString("username"));
                
                comentarios.add(c);
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(DaoGaleria.class.getName()).log(Level.SEVERE, null, ex);
        }

        devolverConexion(cn);

        return comentarios;
    }
    
    public static void comentar(String comentario, int idfoto, int iduser) {
        Connection cn=conexion();
        try {
            String sql="INSERT INTO comentarios (comentario, fechacom, idfoto, iduser) VALUES ('"+comentario+"',CURRENT_TIMESTAMP,"+idfoto+","+iduser+")";
            Statement st = cn.createStatement();
            st.executeUpdate(sql);
            devolverConexion(cn);
        } catch (SQLException ex) {
            devolverConexion(cn);;
            System.out.println("ERROR AL PREPARAR STATEMENT: "+ex.toString());
        }
    }
    
    public static void eliminar(int idfoto) {
        Connection cn=conexion();
        try {
            String sql="DELETE FROM comentarios WHERE idfoto = "+idfoto+"";
            Statement st = cn.createStatement();
            st.executeUpdate(sql);
            
            sql = "DELETE from fotos WHERE idfoto = "+idfoto+"";
            st.executeUpdate(sql);
            
            devolverConexion(cn);
        } catch (SQLException ex) {
            devolverConexion(cn);;
            System.out.println("ERROR AL PREPARAR STATEMENT: "+ex.toString());
        }
    }
    
    public static void subirFoto(String titulo, String nombre, String desc, int iduser) {
        Connection cn=conexion();
        try {
            String sql="INSERT INTO fotos (titulo, narchivo, descripcion, iduser) VALUES ('"+titulo+"','"+nombre+"','"+desc+"',"+iduser+")";
            Statement st = cn.createStatement();
            st.executeUpdate(sql);
            devolverConexion(cn);
        } catch (SQLException ex) {
            devolverConexion(cn);;
            System.out.println("ERROR AL PREPARAR STATEMENT: "+ex.toString());
        }
    }
}
