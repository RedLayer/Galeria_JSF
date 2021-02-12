package mbeans;

import beans.Usuario;
import dao.DaoGaleria;
import javax.faces.context.FacesContext;

public class DataSession {

    private String username;
    private String pass;
    
    private Usuario usuarioSesion;
    
    private String error = "";
    
    
    public DataSession() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Usuario getUsuarioSesion() {
        return usuarioSesion;
    }

    public void setUsuarioSesion(Usuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    
    public String comprobarUsuario() {
        usuarioSesion = DaoGaleria.usuario(username, pass);
        
        if(usuarioSesion == null) {
            error = "El usuario o la contraseña no son correctos";
            return null;
        } else {
            return "index.xhtml";
        }
    }
    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login.xhtml";
    }
  
}
