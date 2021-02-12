package beans;

import java.util.Date;

public class Comentario {
    private int idcomentario;
    private String comentario;
    private java.util.Date fechacom;
    private int idfoto;
    private String nusuario;

    public Comentario() {
    }

    public Comentario(int idcomentario, String comentario, Date fechacom, int idfoto, String nusuario) {
        this.idcomentario = idcomentario;
        this.comentario = comentario;
        this.fechacom = fechacom;
        this.idfoto = idfoto;
        this.nusuario = nusuario;
    }

    public int getIdcomentario() {
        return idcomentario;
    }

    public void setIdcomentario(int idcomentario) {
        this.idcomentario = idcomentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechacom() {
        return fechacom;
    }

    public void setFechacom(Date fechacom) {
        this.fechacom = fechacom;
    }

    public int getIdfoto() {
        return idfoto;
    }

    public void setIdfoto(int idfoto) {
        this.idfoto = idfoto;
    }

    public String getNusuario() {
        return nusuario;
    }

    public void setNusuario(String nusuario) {
        this.nusuario = nusuario;
    }
    
    
}
