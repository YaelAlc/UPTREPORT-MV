package yael.alcantara.uptreport.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import yael.alcantara.uptreport.db.ReporteCompleto;
import yael.alcantara.uptreport.db.Reportes;

@Dao
public interface ReportesDao {

    @Insert
    long insertarReportes(Reportes reportes);

    @Update
    void actualizarReportes(Reportes reportes);

    @Delete
    void borrarReportes(Reportes reportes);

    @Query("""
        SELECT r.*, e.edificio AS nombreEdificio, s.salon AS nombreSalon
        FROM Tabla_Reportes r
        INNER JOIN Tabla_Edificios e ON r.idedificio = e.id
        INNER JOIN Tabla_Salon s ON r.idsalon = s.id
        WHERE r.idusuarios = :idUsuario
        ORDER BY r.Fecha DESC
    """)
    List<ReporteCompleto> getReportesPorUsuario(int idUsuario);

    @Query("""
        SELECT r.*, e.edificio AS nombreEdificio, s.salon AS nombreSalon
        FROM Tabla_Reportes r
        INNER JOIN Tabla_Edificios e ON r.idedificio = e.id
        INNER JOIN Tabla_Salon s ON r.idsalon = s.id
        WHERE r.idusuarios = :idUsuario AND r.id_estado = :estado
        ORDER BY r.Fecha DESC
    """)
    List<ReporteCompleto> getReportesPorUsuarioYEstado(int idUsuario, int estado);

    @Query("SELECT url FROM Tabla_Evidencias WHERE id_reporte = :idReporte")
    List<String> getEvidenciasPorReporte(int idReporte);


}
