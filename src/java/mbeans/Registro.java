package mbeans;

import beans.Usuario;
import dao.DaoGaleria;

public class Registro {

    private Usuario usuarioARegistrar;
    private String error="";
    
    public Registro() {
        usuarioARegistrar = new Usuario();
    }

    public Usuario getUsuarioARegistrar() {
        return usuarioARegistrar;
    }

    public void setUsuarioARegistrar(Usuario usuarioARegistrar) {
        this.usuarioARegistrar = usuarioARegistrar;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public String registrar() {
        if(usuarioARegistrar.getUsername().equals("") || usuarioARegistrar.getEmail().equals("") || usuarioARegistrar.getPass().equals("") || usuarioARegistrar.getNombre().equals("") || usuarioARegistrar.getApellidos().equals("")) {
            error = "Faltan campos por rellenar";
            return null;
        }
        if(DaoGaleria.registro(usuarioARegistrar)){
            return "login.xhtml";
        } else {
            error = "Usuario ya existente";
            return null;
        }
    }
}
