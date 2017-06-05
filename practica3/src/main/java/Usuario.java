/**
 * Created by eva_c on 6/3/2017.
 */
public class Usuario {
    private String username ;
    private String nombre;
    private String password;
    private boolean administrator;
    private boolean autor;

    public Usuario(){}

    public Usuario(String usename, String nombre, String password, boolean administrator, boolean autor) {
        this.username = usename;
        this.nombre = nombre;
        this.password = password;
        this.administrator = administrator;
        this.autor = autor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String usename) {
        this.username = usename;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    public boolean isAutor() {
        return autor;
    }

    public void setAutor(boolean autor) {
        this.autor = autor;
    }


}
