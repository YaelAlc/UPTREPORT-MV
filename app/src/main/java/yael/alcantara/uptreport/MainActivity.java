package yael.alcantara.uptreport;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import yael.alcantara.uptreport.db.Estado_Reporte;
import yael.alcantara.uptreport.db.Tipo_Usuario;
import yael.alcantara.uptreport.db.appDatabase;
import yael.alcantara.uptreport.db.dao.Estado_ReporteDao;
import yael.alcantara.uptreport.db.dao.Tipo_UsuariosDAo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_inicio_de_sesion);

        appDatabase db= appDatabase.getInstance(this);

        Tipo_UsuariosDAo tipo_usuariosDAo = (Tipo_UsuariosDAo) db.tipo_usuariosDAo();
        Estado_ReporteDao estado_reporteDao= db.estado_reporteDao();

        if(tipo_usuariosDAo.getCount()==0){
            tipo_usuariosDAo.insertarTipo(new Tipo_Usuario("alumno"));
            tipo_usuariosDAo.insertarTipo(new Tipo_Usuario("docente"));
            tipo_usuariosDAo.insertarTipo(new Tipo_Usuario("administrativo"));
        }

        if (estado_reporteDao.getCount()==0){
            estado_reporteDao.insertarEstado(new Estado_Reporte("Pendiente"));
            estado_reporteDao.insertarEstado(new Estado_Reporte("En curso"));
            estado_reporteDao.insertarEstado(new Estado_Reporte("Resuelto"));
        }

    }
}