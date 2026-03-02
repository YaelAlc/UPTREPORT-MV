package yael.alcantara.uptreport.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(
        tableName = "Tabla_Reportes",
        foreignKeys = {
                @ForeignKey(
                        entity = Edificio.class,
                        parentColumns = "id",
                        childColumns = "idedificio",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Salon.class,
                        parentColumns = "id",
                        childColumns = "idsalon",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Usuarios.class,
                        parentColumns = "id",
                        childColumns = "idusuarios",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Estado_Reporte.class,
                        parentColumns = "id",
                        childColumns = "id_estado",
                        onUpdate = ForeignKey.CASCADE,
                        onDelete = ForeignKey.CASCADE
                )
        },
        indices = {@Index("idedificio"), @Index("idsalon"), @Index("idusuarios"), @Index("id_estado")}
)
public class Reportes {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Nombre")
    private String Nombre;

    @ColumnInfo(name = "ApellidoP")
    private String ApellidoP;

    @ColumnInfo(name = "ApellidoM")
    private String ApellidoM;

    @ColumnInfo(name = "Matricula")
    private int Matricula;

    @ColumnInfo(name = "Grupo")
    private String Grupo;

    @ColumnInfo(name = "idedificio")
    private int idedificio;

    @ColumnInfo(name = "idsalon")
    private int idsalon;

    @ColumnInfo(name = "idusuarios")
    private int idusuarios;

    @ColumnInfo(name = "Descripcion")
    private String Descripcion;

    @ColumnInfo(name = "Fecha")
    private Date Fecha;

    @ColumnInfo(name = "id_estado")
    private int id_estado;


    public Reportes(String Nombre, String ApellidoP, String ApellidoM, int Matricula, String Grupo, int idedificio, int idsalon, int idusuarios, String Descripcion, Date Fecha, int id_estado){
        this.Nombre = Nombre;
        this.ApellidoP = ApellidoP;
        this.ApellidoM = ApellidoM;
        this.Matricula = Matricula;
        this.Grupo = Grupo;
        this.idedificio = idedificio;
        this.idsalon = idsalon;
        this.idusuarios = idusuarios;
        this.Descripcion = Descripcion;
        this.Fecha = Fecha;
        this.id_estado = id_estado;
    }

    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public String getNombre(){return Nombre;}
    public void setNombre(String nombre){this.Nombre = nombre;}

    public String getApellidoP(){return ApellidoP;}
    public void setApellidoP(String apellidoP){this.ApellidoP = apellidoP;}

    public String getApellidoM(){return ApellidoM;}
    public void setApellidoM(String apellidoM){this.ApellidoM = apellidoM;}

    public int getMatricula(){return Matricula;}
    public void setMatricula(int matricula){this.Matricula = matricula;}

    public String getGrupo(){return Grupo;}
    public void setGrupo(String grupo){this.Grupo = grupo;}

    public int getIdedificio(){return idedificio;}
    public void setIdedificio(int idedificio){this.idedificio = idedificio;}

    public int getIdsalon(){return idsalon;}
    public void setIdsalon(int idsalon){this.idsalon = idsalon;}

    public int getIdusuarios(){return idusuarios;}
    public void setIdusuarios(int idusuarios){this.idusuarios = idusuarios;}

    public String getDescripcion(){return Descripcion;}
    public void setDescripcion(String descripcion){this.Descripcion = descripcion;}

    public Date getFecha(){return Fecha;}
    public void setFecha(Date fecha){this.Fecha = fecha;}

    public int getId_estado(){return id_estado;}
    public void setId_estado(int id_estado){this.id_estado = id_estado;}
}