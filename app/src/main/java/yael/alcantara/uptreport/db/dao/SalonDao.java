package yael.alcantara.uptreport.db.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import yael.alcantara.uptreport.db.Salon;

@Dao
public interface SalonDao {
    @Insert
    void insertarSalon(Salon salon);

    @Update
    void actualizarSalon(Salon salon);

    @Delete
    void borrarSalon(Salon salon);

    @Query("SELECT * FROM tabla_salon")
    int getCount();
}
