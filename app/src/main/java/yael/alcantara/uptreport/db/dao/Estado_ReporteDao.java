package yael.alcantara.uptreport.db.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import yael.alcantara.uptreport.db.Estado_Reporte;

@Dao
public interface Estado_ReporteDao {
    @Insert
    void insertarEstado(Estado_Reporte estado_reporte);

    @Update
    void actualizarEstado(Estado_Reporte estado_reporte);

    @Delete
    void borrarEstado(Estado_Reporte estado_reporte);
}
