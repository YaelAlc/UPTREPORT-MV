package yael.alcantara.uptreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import yael.alcantara.uptreport.db.Estado_Reporte;
import yael.alcantara.uptreport.db.Grupo;
import yael.alcantara.uptreport.db.Tipo_Usuario;
import yael.alcantara.uptreport.db.Usuarios;
import yael.alcantara.uptreport.db.appDatabase;
import yael.alcantara.uptreport.db.dao.Estado_ReporteDao;
import yael.alcantara.uptreport.db.dao.GrupoDao;
import yael.alcantara.uptreport.db.dao.Tipo_UsuariosDAo;
import yael.alcantara.uptreport.db.dao.UsuariosDao;

public class MainActivity extends AppCompatActivity {
    private EditText edtMatricula, edtPasword;

    private Button btnLogin, btnResgistro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_inicio_de_sesion);

        edtPasword= findViewById(R.id.edtPassword);
        edtMatricula=findViewById(R.id.edtMatricula);


        appDatabase db= appDatabase.getInstance(this);
        UsuariosDao dao= db.usuariosDao();

        Tipo_UsuariosDAo tipo_usuariosDAo = (Tipo_UsuariosDAo) db.tipo_usuariosDAo();
        Estado_ReporteDao estado_reporteDao= db.estado_reporteDao();
        GrupoDao grupoDao= db.grupoDao();

        if(tipo_usuariosDAo.getCount()==0){
            tipo_usuariosDAo.insertarTipo(new Tipo_Usuario("alumno"));
            tipo_usuariosDAo.insertarTipo(new Tipo_Usuario("administrativo"));
        }

        if (estado_reporteDao.getCount()==0){
            estado_reporteDao.insertarEstado(new Estado_Reporte("Pendiente"));
            estado_reporteDao.insertarEstado(new Estado_Reporte("En curso"));
            estado_reporteDao.insertarEstado(new Estado_Reporte("Resuelto"));
        }

        if (grupoDao.getCount()==0){
            grupoDao.insertarGrupo(new Grupo("2526ITII"));
            grupoDao.insertarGrupo(new Grupo("2425ITII"));
        }

        btnLogin.setOnClickListener(v -> {
            String matricula = edtMatricula.getText().toString().trim();
            String contrasenia = edtPasword.getText().toString().trim();
            
            new Thread(()->{
                Usuarios usuarios = dao.iniciar(matricula, contrasenia);

                runOnUiThread(()->{
                    if( matricula != null ) {
                        Intent ventanaPrimeraI = new Intent(MainActivity.this, Primera_interfaz_reporte.class);
                        startActivity(ventanaPrimeraI);
                    }else {
                        edtPasword.setError("Usuaio o contrasenia incorrectos");
                    }
                });
            }).start();
        });
        btnResgistro.setOnClickListener(v -> {
            Intent vRegistro=new Intent(MainActivity.this, registro_usuario.class);
            startActivity(vRegistro);
        });






    }
}