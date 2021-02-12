package beans;

public class Imagen {
    private int idfoto;
    private String titulo;
    private String narchivo;
    private String descripcion;
    private int iduser;
    private String nusuario;

    public Imagen() {
    }

    public Imagen(int idfoto, String titulo, String narchivo, String descripcion, int iduser, String nusuario) {
        this.idfoto = idfoto;
        this.titulo = titulo;
        this.narchivo = narchivo;
        this.descripcion = descripcion;
        this.iduser = iduser;
        this.nusuario = nusuario;
    }

    public int getIdfoto() {
        return idfoto;
    }

    public void setIdfoto(int idfoto) {
        this.idfoto = idfoto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNarchivo() {
        return narchivo;
    }

    public void setNarchivo(String narchivo) {
        this.narchivo = narchivo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNusuario() {
        return nusuario;
    }

    public void setNusuario(String nusuario) {
        this.nusuario = nusuario;
    }
    
    
}
