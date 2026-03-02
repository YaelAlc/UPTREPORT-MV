package yael.alcantara.uptreport.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tabla_Estado_Reporte")
public class Estado_Reporte {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name =  "Estado_Reporte")
    private String Estado_Reporte;

    public Estado_Reporte(String Estado_Reportes){
        this. Estado_Reporte= Estado_Reportes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado_Reporte(){
        return Estado_Reporte;
    }

    public void setEstado_Reporte(String estado_Reporte) {
        Estado_Reporte = estado_Reporte;
    }
}
