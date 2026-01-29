package yael.alcantara.uptreport.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity( tableName = "Tabla_Edificios")

public class Edificio {
    //Atributos de la tabla Edificios

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "edificio")
    private String edificio;

    public  Edificio(String edificio){
        this.edificio=edificio;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }
    public String getEdificio(){
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }
}
