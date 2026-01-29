package yael.alcantara.uptreport.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import yael.alcantara.uptreport.db.Edificio;

@Dao
public interface EdificioDao {
    //Funciones de la tabla Edificio
    @Insert
    void insertarEdificio(Edificio edificio);

    @Update
    void actualizarEdificio(Edificio edificio);

    @Delete
    void borrarEdificio(Edificio edificio);

    @Query("SELECT * FROM tabla_edificios WHERE edificio = :edificio LIMIT 1")
    Edificio buscarporNombreE(String edificio);
}
