package beans;


public class Usuario {
    
    private int iduser;
    private String nombre;
    private String apellidos;
    private String username;
    private String pass;
    private String email;

    public Usuario() {
    }

    public Usuario(int iduser, String nombre, String apellidos, String username, String pass, String email) {
        this.iduser = iduser;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.username = username;
        this.pass = pass;
        this.email = email;
    }


    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
}
