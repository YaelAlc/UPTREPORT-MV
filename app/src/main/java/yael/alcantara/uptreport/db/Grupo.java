package yael.alcantara.uptreport.db;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;


public class Grupo {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "Grupo")
    private String grupo;

    public Grupo(String grupo){
        this.grupo =grupo;
    }
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getGrupo(){
        return  grupo;
    }

    public void setGrupo(String grupo){
        this.grupo=grupo;
    }
}
