package yael.alcantara.uptreport;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import yael.alcantara.uptreport.db.Grupo;
import yael.alcantara.uptreport.db.Usuarios;
import yael.alcantara.uptreport.db.appDatabase;
import yael.alcantara.uptreport.db.dao.UsuariosDao;


public class registro_usuario extends AppCompatActivity {
    private EditText edtMatricula, edtNombre, edtApellidoP, edtApellidoM, edtCorreo, edtContrasenia;

    private Spinner spinnerGrupo;

    private Button btnGuardar;

    List <Grupo> listaGrupo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        edtMatricula = findViewById(R.id.edtMatricula);
        edtNombre = findViewById(R.id.edtNombre);
        edtApellidoP = findViewById(R.id.edtApellidoP);
        edtApellidoM = findViewById(R.id.edtApellidoM);
        spinnerGrupo = findViewById(R.id.spinnerGrupo);
        edtCorreo = findViewById(R.id.edtCorreo);
        edtContrasenia = findViewById(R.id.edtContrasenia);
        btnGuardar = findViewById(R.id.btnGuardar);

        appDatabase db = appDatabase.getInstance(this);
        UsuariosDao dao = db.usuariosDao();

        // 1. CARGAR GRUPOS EN HILO SECUNDARIO
        new Thread(() -> {
            listaGrupo = db.grupoDao().obtenerGrupos();
            List<String> nombresGrupos = new ArrayList<>();
            for (Grupo g : listaGrupo) {
                nombresGrupos.add(g.getGrupo());
            }

            runOnUiThread(() -> {
                ArrayAdapter<String> adapterGrupo = new ArrayAdapter<>(this,
                        android.R.layout.simple_spinner_item, nombresGrupos);
                adapterGrupo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerGrupo.setAdapter(adapterGrupo);
            });
        }).start();

        btnGuardar.setOnClickListener(v -> {
            String mat = edtMatricula.getText().toString().trim();

            // VALIDACIÓN: Evitar error si el campo está vacío
            if (mat.isEmpty()) {
                edtMatricula.setError("Ingresa una matrícula");
                return;
            }

            int matricula = Integer.parseInt(mat);
            String nombre = edtNombre.getText().toString().trim();
            String apellidoP = edtApellidoP.getText().toString().trim();
            String apellidoM = edtApellidoM.getText().toString().trim();
            String correo = edtCorreo.getText().toString().trim();
            String contrasenia = edtContrasenia.getText().toString().trim();

            String grupoSeleccionado = (spinnerGrupo.getSelectedItem() != null) ?
                    spinnerGrupo.getSelectedItem().toString() : "";

            new Thread(() -> {
                // Obtener el ID del grupo
                int idgrupo = listaGrupo.stream()
                        .filter(g -> g.getGrupo().equals(grupoSeleccionado))
                        .findFirst()
                        .map(Grupo::getId)
                        .orElse(0);

                Usuarios existente = dao.obtenerIdPorMatricula(matricula);

                if (existente != null) {
                    runOnUiThread(() -> edtMatricula.setError("Ya existe esta matrícula"));
                } else {
                    // 2. INSERTAR Y FINALIZAR
                    Usuarios nuevo = new Usuarios(matricula, nombre, apellidoP, apellidoM, idgrupo, correo, contrasenia, "1");
                    dao.insertarUsuario(nuevo);

                    // Es vital volver al hilo principal para cerrar la actividad
                    runOnUiThread(this::finish);
                }
            }).start();
        });
    }
}