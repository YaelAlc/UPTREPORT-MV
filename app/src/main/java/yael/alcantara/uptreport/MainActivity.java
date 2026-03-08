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
                edificioDao.insertarEdificio(new Edificio("Edificio 1"));
                edificioDao.insertarEdificio(new Edificio("Edificio 2"));
                edificioDao.insertarEdificio(new Edificio("Edificio 3"));
                edificioDao.insertarEdificio(new Edificio("Centro de Investigaciones (queso)"));

                // Salones Edificio 1 (ID 1)
                String[] salonesE1 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16","auditorio","idiomas 1","Finanzas","Negocios","Sala de juntas","Departamento de información"};
                for (String s : salonesE1) salonDao.insertarSalon(new Salon(s, 1));

                // Salones Edificio 2 (ID 2)
                String[] salonesE2 = {"17","20", "21","22","23","24","25","28","29","30","31","32","33","Monitoreo de Universidad Segura","Jefatura de Vinculación","Ídiomas 4","Enfermería","Extención Extracurricular","Centro de Negocios","Coordinación de Íngles","Control Escolar","Tutorías Asesorías","Dirección de Ingenierías","Área de Desarrollo de Software","Electrónica","Tecnologías de manufactura","Sala de muestras","Química","Gimnasio","Idiomas 3"};
                for (String s : salonesE2) salonDao.insertarSalon(new Salon(s, 2));

                // Salones Edificio 3 (ID 3)
                String[] salonesE3 = {"34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50","Site 4","Biblioteca","Gimnasio 2","Software 1","Observatorio Empresarial Universitario","Abogacia","Nodo UPT ANIERN","Dirección Licenciatura","Laboratorio de Software 2"};
                for (String s : salonesE3) salonDao.insertarSalon(new Salon(s, 3));

                // Salones Queso (ID 4)
                String[] salonesQueso = {"Investigacion","CAD CAM","Metrologia","CIM","Maquinas y Herramientas","Macanica Automotriz","Proyectos de Ingenieria","Hidraulica y Neumatica","Sala de Reuniones,","Laboratorio de TICS","Desarrollo de Formulacion de Proyectos","Desrrollo de Formulacion de Programacion","Unidades Economicas"};
                for (String s : salonesQueso) salonDao.insertarSalon(new Salon(s, 4));
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
