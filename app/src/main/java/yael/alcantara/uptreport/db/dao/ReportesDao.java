package yael.alcantara.uptreport.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import yael.alcantara.uptreport.db.Reportes;

@Dao
public interface ReportesDao {

    @Insert
    long insertarReportes(Reportes reportes);

    @Update
    void actualizarReportes(Reportes reportes);

    @Delete
    void borrarReportes(Reportes reportes);


}
