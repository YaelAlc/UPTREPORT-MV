package yael.alcantara.uptreport.db.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import yael.alcantara.uptreport.db.Edificio;

@Entity(tableName = "Tabla_Salon",
        foreignKeys = {
        @ForeignKey(
                entity= Edificio.class,
                parentColumns = "id",
                childColumns = "idedificio",
                onUpdate = ForeignKey.CASCADE,
                onDelete = ForeignKey.CASCADE
        )
        },
        indices ={@Index("idedificio")}
)
public class Salon {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name= "salon")
    private String salon;

    @ColumnInfo(name= "idedificio")
    private int idedificio;

    public Salon(String salon, int idedificio){
        this.salon=salon;
        this.idedificio=idedificio;
    }

    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public int getIdedificio() {
        return idedificio;
    }

    public void setIdedificio(int idedificio) {
        this.idedificio = idedificio;
    }
}
