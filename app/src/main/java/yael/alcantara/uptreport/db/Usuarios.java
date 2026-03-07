package yael.alcantara.uptreport.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Tabla_Usuarios",
        foreignKeys = {
                @ForeignKey(
                        entity = Grupo.class,
                        parentColumns = "id",
                        childColumns =  "idgrupo",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Tipo_Usuario.class,
                        parentColumns = "id",
                        childColumns = "id_tipo",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE
                )

        },
        indices={@Index("idgrupo"), @Index("id_tipo")}
)
public class Usuarios {
    //Atributos de la tabla de Usuarios
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name= "matricula")
    private String matricula;

    @ColumnInfo(name= "nombre")
    private String nombre;

    @ColumnInfo(name= "apellidoP")
    private String apellidoP;

    @ColumnInfo(name= "apellidoM")
    private String apellidoM;

    @ColumnInfo(name= "idgrupo")
    private int idgrupo;

    @ColumnInfo(name= "correo")
    private String correo;

    @ColumnInfo(name = "contrasenia")
    private String contrasenia;
    @ColumnInfo(name = "id_tipo")
    private String id_tipo;

    public Usuarios(String matricula, String nombre, String apellidoP, String apellidoM,int idgrupo,String correo,String contrasenia, String id_tipo){
        this.matricula=matricula;
        this.nombre = nombre;
        this.apellidoM = apellidoM;
        this.apellidoP= apellidoP;
        this.idgrupo= idgrupo;
        this.correo= correo;
        this.contrasenia=contrasenia;
        this.id_tipo=id_tipo;
    }
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id= id;
    }

    public String getMatricula(){
        return matricula;
    }

    public void setMatricula(String matricula){
        this.matricula= matricula;
    }

    public  String getNombre(){
        return nombre;
    }

    public void  setNombre(String nombre){
        this.nombre=nombre;
    }

    public String getApellidoP(){
        return apellidoP;
    }

    public void setApellidoP(String apellidoP){
        this.apellidoP=apellidoP;
    }

    public String getApellidoM(){
        return apellidoM;
    }

    public void setApellidoM(String apellidoM){
        this.apellidoM=apellidoM;
    }
    public int getIdgrupo(){
        return idgrupo;
    }

    public  void setIdgrupo(int idgrupo){
        this.idgrupo=idgrupo;
    }

    public String getCorreo(){
        return correo;
    }

    public void setCorreo(String correo){
        this.correo=correo;
    }

    public String getContrasenia(){
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setId_tipo(String id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getId_tipo() {
        return id_tipo;
    }
}
