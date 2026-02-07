package yael.alcantara.uptreport.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.sql.Blob;
import java.sql.Date;

@Entity(
        tableName =  "Tabla_Reportes",
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
                )
        },
        indices = {@Index("idedificio")}
)
public class Reportes {
    //Atributos de la tabla de Reportes
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
    @ColumnInfo(name = "Descripcion")
    private Blob Descripcion;
    @ColumnInfo(name = "Evidencia")
    private Blob Evidencia;
    @ColumnInfo(name = "Fecha")
    private Date Fecha;
    @ColumnInfo(name = "Estado_Reporte")
    private String Estado_Reporte;

    public Reportes(String Nombre, String ApellidoP, String ApellidoM, int Matricula, String Grupo, int idedificio, int idsalon, Blob Descripcion, Blob Evidencia, Date Fecha, String Estado_Reporte){
        this.Nombre = Nombre;
        this.ApellidoP = ApellidoP;
        this.ApellidoM = ApellidoM;
        this.Matricula = Matricula;
        this.Grupo = Grupo;
        this.idedificio = idedificio;
        this.idsalon = idsalon;
        this.Descripcion = Descripcion;
        this.Evidencia = Evidencia;
        this.Fecha = Fecha;
        this.Estado_Reporte = Estado_Reporte;
    }

    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    public String getNombre(){return Nombre;}
    public void setNombre(String nombre){this.Nombre = Nombre;}
    public String getApellidoP(){return ApellidoP;}
    public void setApellidoP(String apellidoP){this.ApellidoP = ApellidoP;}
    public String getApellidoM(){return ApellidoM;}
    public void setApellidoM(String apellidoM){this.ApellidoM = ApellidoM;}
    public int getMatricula(){return  Matricula;}
    public void setMatricula(int matricula){this.Matricula = Matricula;}
    public String getGrupo(){return Grupo;}
    public void setGrupo(String grupo){this.Grupo = Grupo;}
    public int getIdedificio(){return idedificio;}
    public void setIdedificio(int idedificio){this.idedificio = idedificio;}
    public int getIdsalon(){return idsalon;}
    public void setIdsalon(int idsalon){this.idsalon = idsalon;}
    public Blob getDescripcion(){return Descripcion;}
    public void setDescripcion(Blob descripcion){this.Descripcion = Descripcion;}
    public Blob getEvidencia(){return Evidencia;}
    public void setEvidencia(Blob evidencia){this.Evidencia = Evidencia;}
    public Date getFecha(){return Fecha;}
    public void setFecha(Date fecha){this.Fecha = Fecha;}
    public String getEstado_Reporte(){return Estado_Reporte;}
    public void setEstado_Reporte(String estado_Reporte){this.Estado_Reporte = Estado_Reporte;}

}