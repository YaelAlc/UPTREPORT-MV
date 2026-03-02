package yael.alcantara.uptreport.db;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Tabla_Tipo")
public class Tipo_Usuario {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "Nombre")
    public String nombre;

    public Tipo_Usuario(String nombre){
        this.nombre=nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
