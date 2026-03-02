package yael.alcantara.uptreport.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import yael.alcantara.uptreport.db.Evidencias;

@Dao
public interface EvidenciasDao {
    @Insert
    void insertarEvidencias(Evidencias evidencias);

    @Update
    void actualizarEvidencias(Evidencias evidencias);

    @Delete
    void borrarEvidencias(Evidencias evidencias);
}
