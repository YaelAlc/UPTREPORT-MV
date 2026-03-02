package yael.alcantara.uptreport.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Tabla_Evidencias",
        foreignKeys = @ForeignKey(
                entity = Reportes.class,
                parentColumns = "id",
                childColumns = "id_reporte",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        ),
        indices = {@Index("id_reporte")}
)
public class Evidencias {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "id_reporte")
    private int id_reporte;

    @ColumnInfo(name = "url")
    private String url;


    public Evidencias(int id_reporte, String url){
        this.id_reporte = id_reporte;
        this.url = url;
    }

    public int getId(){return id;}
    public void setId(int id){this.id = id;}

    public int getId_reporte(){return id_reporte;}
    public void setId_reporte(int id_reporte){this.id_reporte = id_reporte;}

    public String getUrl(){return url;}
    public void setUrl(String url){this.url = url;}
}