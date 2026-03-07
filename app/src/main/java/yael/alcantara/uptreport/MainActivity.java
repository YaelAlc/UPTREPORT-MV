package yael.alcantara.uptreport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import yael.alcantara.uptreport.db.Edificio;
import yael.alcantara.uptreport.db.Estado_Reporte;
import yael.alcantara.uptreport.db.Grupo;
import yael.alcantara.uptreport.db.Salon;
import yael.alcantara.uptreport.db.Tipo_Usuario;
import yael.alcantara.uptreport.db.Usuarios;
import yael.alcantara.uptreport.db.appDatabase;
import yael.alcantara.uptreport.db.dao.EdificioDao;
import yael.alcantara.uptreport.db.dao.Estado_ReporteDao;
import yael.alcantara.uptreport.db.dao.GrupoDao;
import yael.alcantara.uptreport.db.dao.SalonDao;
import yael.alcantara.uptreport.db.dao.Tipo_UsuariosDAo;
import yael.alcantara.uptreport.db.dao.UsuariosDao;

public class MainActivity extends AppCompatActivity {
    private EditText edtMatricula, edtPasword;
    private Button btnLogin, btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ventana_inicio_de_sesion);

        // 1. Inicializar vistas (Indispensable para evitar Crash)
        edtPasword = findViewById(R.id.edtPassword);
        edtMatricula = findViewById(R.id.edtMatricula);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistro = findViewById(R.id.btnRegistro);

        appDatabase db = appDatabase.getInstance(this);
        UsuariosDao dao = db.usuariosDao();
        Tipo_UsuariosDAo tipo_usuariosDAo = db.tipo_usuariosDAo();
        Estado_ReporteDao estado_reporteDao = db.estado_reporteDao();
        GrupoDao grupoDao = db.grupoDao();
        EdificioDao edificioDao = db.edificioDao();
        SalonDao salonDao = db.salonDao();

        // 2. Operaciones de base de datos (Ejecutar en hilo para mejor rendimiento)
        new Thread(() -> {
            if (tipo_usuariosDAo.getCount() == 0) {
                tipo_usuariosDAo.insertarTipo(new Tipo_Usuario("alumno"));
                tipo_usuariosDAo.insertarTipo(new Tipo_Usuario("administrativo"));
            }

            if (estado_reporteDao.getCount() == 0) {
                estado_reporteDao.insertarEstado(new Estado_Reporte("Pendiente"));
                estado_reporteDao.insertarEstado(new Estado_Reporte("En curso"));
                estado_reporteDao.insertarEstado(new Estado_Reporte("Resuelto"));
            }

            if (grupoDao.getCount() == 0) {
                grupoDao.insertarGrupo(new Grupo("2526ITII"));
                grupoDao.insertarGrupo(new Grupo("2425ITII"));
            }

            if (edificioDao.getCount() == 0) {
                edificioDao.insertarEdificio(new Edificio("Edificio A"));
                edificioDao.insertarEdificio(new Edificio("Edificio B"));
                
                // Agregar salones de prueba para el Edificio A (asumiendo ID 1)
                salonDao.insertarSalon(new Salon("Aula 1", 1));
                salonDao.insertarSalon(new Salon("Aula 2", 1));
                // Agregar salones de prueba para el Edificio B (asumiendo ID 2)
                salonDao.insertarSalon(new Salon("Aula 3", 2));
                salonDao.insertarSalon(new Salon("Aula 4", 2));
            }
        }).start();

        btnRegistro.setOnClickListener(v -> {
                    Intent ventanaRegistro = new Intent(MainActivity.this, registro_usuario.class);
                    startActivity(ventanaRegistro);
                    finish();
                });


        btnLogin.setOnClickListener(v -> {
            String matricula = edtMatricula.getText().toString().trim();
            String contrasenia = edtPasword.getText().toString().trim();

            if (matricula.isEmpty() || contrasenia.isEmpty()) {
                Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                Usuarios usuario = dao.iniciar(matricula, contrasenia);

                runOnUiThread(() -> {
                    if (usuario != null) {
                        int tipo = Integer.parseInt(usuario.getId_tipo());

                        if (tipo == 1) {

                            Intent ventanaAlumno = new Intent(MainActivity.this, Primera_interfaz_reporte.class);
                            ventanaAlumno.putExtra("idUsuario", usuario.getId());
                            startActivity(ventanaAlumno);
                            finish();

                        } else if (tipo == 2) {

                            Intent ventanaAdmin = new Intent(MainActivity.this, reportes_admin.class);
                            startActivity(ventanaAdmin);
                            finish();

                        }
                    } else {
                        edtPasword.setError("Usuario o contraseña incorrectos");
                    }
                });
            }).start();
        });



    }
}
