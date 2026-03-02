package yael.alcantara.uptreport.db.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import yael.alcantara.uptreport.db.Tipo_Usuario;

@Dao
public interface Tipo_UsuariosDAo {

    @Insert
    void insertarTipo(Tipo_Usuario tipo_usuario);
    @Update
    void actualizarTipo(Tipo_Usuario tipo_usuario);
    @Delete
    void borrarTipo(Tipo_Usuario tipo_usuario);
}
