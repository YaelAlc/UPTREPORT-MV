package yael.alcantara.uptreport.db.dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import yael.alcantara.uptreport.db.Usuarios;

@Dao
public interface UsuariosDao {

    //funciones de la tabla Usuarios
    @Insert
    void insertarUsuario(Usuarios usuarios);

    @Update
    void actualizarUsuario(Usuarios usuarios);

    @Delete
    void borrarUsuario(Usuarios usuarios);

    //Consultas
    @Query("SELECT id FROM tabla_usuarios WHERE matricula= :matricula LIMIT 1")
    int obtenerIdPorMatricula(String matricula);
}
