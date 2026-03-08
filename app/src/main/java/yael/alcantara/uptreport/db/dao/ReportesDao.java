package yael.alcantara.uptreport.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import yael.alcantara.uptreport.db.Reportes;

@Dao
public interface ReportesDao {

    @Insert
    long insertarReportes(Reportes reportes);

    @Update
    void actualizarReportes(Reportes reportes);

    @Delete
    void borrarReportes(Reportes reportes);
    @Query("SELECT * FROM Tabla_Reportes WHERE idusuarios = :idUsuario ORDER BY Fecha DESC")
    List<Reportes> getReportesPorUsuario(int idUsuario);

    @Query("SELECT * FROM Tabla_Reportes WHERE idusuarios = :idUsuario AND id_estado = :estado ORDER BY Fecha DESC")
    List<Reportes> getReportesPorUsuarioYEstado(int idUsuario, int estado);


}
