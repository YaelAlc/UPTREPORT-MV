package yael.alcantara.uptreport.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tabla_Estado_Reporte")
public class Estado_Reporte {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name =  "Estado_Reporte")
    private String Estado;

    public Estado_Reporte(String Estado){
        this. Estado= Estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado(){
        return Estado;
    }

    public void setEstado(String Estado) {
        Estado = Estado;
    }
}
