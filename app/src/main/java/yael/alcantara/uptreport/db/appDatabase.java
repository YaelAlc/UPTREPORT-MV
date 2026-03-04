package yael.alcantara.uptreport.db;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import yael.alcantara.uptreport.db.dao.EdificioDao;
import yael.alcantara.uptreport.db.dao.Estado_ReporteDao;
import yael.alcantara.uptreport.db.dao.EvidenciasDao;
import yael.alcantara.uptreport.db.dao.GrupoDao;
import yael.alcantara.uptreport.db.dao.ReportesDao;
import yael.alcantara.uptreport.db.dao.SalonDao;
import yael.alcantara.uptreport.db.dao.UsuariosDao;

@Database(
        entities = {
                Edificio.class,
                Estado_Reporte.class,
                Evidencias.class,
                Grupo.class,
                Reportes.class,
                Salon.class,
                Tipo_Usuario.class,
                Usuarios.class

        },
        version = 1,
        exportSchema = false
)
public abstract class appDatabase extends  RoomDatabase{

    public abstract EdificioDao edificioDao();

    public abstract Estado_ReporteDao estado_reporteDao();

    public abstract EvidenciasDao evidenciasDao();

    public abstract GrupoDao grupoDao();

    public abstract ReportesDao reportesDao();

    public abstract SalonDao salonDao();

    public abstract Tipo_Usuario tipo_usuariosDAo();

    public abstract UsuariosDao usuariosDao();

    private static volatile appDatabase INSTANCE;

    public static appDatabase getInstance(Context context){
        if(INSTANCE == null){
            synchronized (appDatabase.class){
                if (INSTANCE == null){
                    INSTANCE= Room.databaseBuilder(
                            context.getApplicationContext(),
                            appDatabase.class,
                            "Reportes_UPT.db"
                            )
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
