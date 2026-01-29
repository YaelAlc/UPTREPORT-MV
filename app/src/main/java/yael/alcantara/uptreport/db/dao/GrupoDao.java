package yael.alcantara.uptreport.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import yael.alcantara.uptreport.db.Grupo;

@Dao
public interface GrupoDao {

    //Funciones de la tabla Grupo
    @Insert
    void insertarGrupo(Grupo grupo);

    @Update
    void actualizarGrupo(Grupo grupo);

    @Delete
    void borrarGrupo(Grupo grupo);

    //Consultas
    @Query("SELECT * FROM tabla_grupo WHERE grupo = :grupo LIMIT 1")
    Grupo buscarPorNombreG(String grupo);

}
