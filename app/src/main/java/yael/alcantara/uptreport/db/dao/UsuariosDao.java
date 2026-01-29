package yael.alcantara.uptreport.db.dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import yael.alcantara.uptreport.db.Usuarios;

@Dao
public interface UsuariosDao {
    @Insert
    void insertarUsuario(Usuarios usuarios);

    @Update
    void actualizarUsuario(Usuarios usuarios);

    @Delete
    void borrarUsuario(Usuarios usuarios);

    @Query("SELECT id FROM tabla_usuarios WHERE matricula= :matricula LIMIT 1")
    int obtenerIdPorMatricula(String matricula);
}
