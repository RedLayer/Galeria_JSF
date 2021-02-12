package mbeans;

import beans.Comentario;
import beans.Imagen;
import dao.DaoGaleria;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

public class Fotos {

    ArrayList<Imagen> imagenes;
    
    private Imagen imagenSelec;
    
    private ArrayList<Comentario> comentarios;
    
    private String comentario;
    
    DataSession dataSession;
    
    
    private Part archivo = null;
    private String tituloASubir;
    private String desc;
    
    private String carpeta = Fotos.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/../../../../resources/images/";
    
    
    private String error = "";
    
    private String nusuario = "Todas";
    
    public Fotos() {
        imagenes = DaoGaleria.fotos();
        dataSession = (DataSession) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("dataSession");
    }

    public ArrayList<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<Imagen> imagenes) {
        this.imagenes = imagenes;
    }

    public Imagen getImagenSelec() {
        return imagenSelec;
    }

    public void setImagenSelec(Imagen imagenSelec) {
        this.imagenSelec = imagenSelec;
    }
    
        public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    
    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Part getArchivo() {
        return archivo;
    }

    public void setArchivo(Part archivo) {
        this.archivo = archivo;
    }

    public String getTituloASubir() {
        return tituloASubir;
    }

    public void setTituloASubir(String tituloASubir) {
        this.tituloASubir = tituloASubir;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getNusuario() {
        return nusuario;
    }

    public void setNusuario(String nusuario) {
        this.nusuario = nusuario;
    }
    
    
    
    public String comentar() {
        DaoGaleria.comentar(comentario,imagenSelec.getIdfoto(),dataSession.getUsuarioSesion().getIduser());
        comentarios = DaoGaleria.comentariosDe(imagenSelec.getIdfoto());
        comentario = "";
        return null;
    }
    
    public String redirect() {
        return "login.xhtml";
    }
    
    public String imagenSelec(Imagen img) {
        imagenSelec = img;
        comentarios = DaoGaleria.comentariosDe(imagenSelec.getIdfoto());
        return null;
    }
    
    
    public String eliminar() { 
        try{
    		
            File file = new File(Fotos.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/../../../../resources/images/"+imagenSelec.getNarchivo());
        	
            if(file.delete()){
                System.out.println(file.getName() + " is deleted!");
            }else{
                System.out.println(file.getAbsolutePath());
            }
    	   
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		
    	}
        DaoGaleria.eliminar(imagenSelec.getIdfoto());
        if(nusuario.equals("")) {
            imagenes = DaoGaleria.fotos();
        } else {
            imagenes = DaoGaleria.fotosDe(imagenSelec.getNusuario());
        }
        imagenSelec = null;
        return "index.xhtml";
        
    }
    
    public String guardarImagen(){
        
        if(!archivo.getSubmittedFileName().equals("")) {
            String nombreArchivo = "";	
            try (InputStream input = archivo.getInputStream()) {
                    nombreArchivo = archivo.getSubmittedFileName();
                    System.out.println(nombreArchivo);
                    String[] nombreSplit = nombreArchivo.split("\\.");
                    System.out.println(nombreSplit.length);
                    String uniqueID = UUID.randomUUID().toString();

                    nombreArchivo = uniqueID +"."+nombreSplit[nombreSplit.length-1];

                    Files.copy(input, new File(carpeta, nombreArchivo).toPath());
            } catch (IOException ex) {
                Logger.getLogger(Fotos.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (tituloASubir.equals("")) {
                error = "Titulo obligatorio";
            }
            else {
                DaoGaleria.subirFoto(tituloASubir,nombreArchivo,desc, dataSession.getUsuarioSesion().getIduser());
                imagenes = DaoGaleria.fotos();
            }
        } else {
            error = "No has seleccionado imagen";
        }
        return "index.xhtml";
        
    }
    
    public String fotosDe() {
        imagenes = DaoGaleria.fotosDe(imagenSelec.getNusuario());
        nusuario = imagenes.get(0).getNusuario();
        imagenSelec = null;
        
        return null;
    }
    
}
