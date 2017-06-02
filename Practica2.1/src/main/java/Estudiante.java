/**
 * Created by eva_c on 5/31/2017.
 */
public class Estudiante {
    private String nombre;
    private String apellido;
    private String telefono;
    private int matricula;

    public Estudiante(String nombre, String apellido, String telefono, int matricula) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
}
