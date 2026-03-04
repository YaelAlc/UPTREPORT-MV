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
        edtApellidoM =findViewById(R.id.edtApellidoM);
        edtContrasenia = findViewById(R.id.edtContrasenia);
        edtNombre =findViewById(R.id.edtNombre);
        edtMatricula = findViewById(R.id.edtMatricula);
        edtApellidoP = findViewById(R.id.edtApellidoP);
        edtCorreo =findViewById(R.id.edtCorreo);
        spinnerGrupo =findViewById(R.id.spinnerGrupo);
        btnGuardar =findViewById(R.id.btnGuardar);

        appDatabase db = appDatabase.getInstance(this);
        UsuariosDao dao = db.usuariosDao();

        listaGrupo =db.grupoDao().obtenerGrupos();

        List<String> nombresGrupos =new ArrayList<>();
        for (Grupo g: listaGrupo){
            nombresGrupos.add(g.getGrupo());
        }

        ArrayAdapter<String> adapterGrupo = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                nombresGrupos);

        adapterGrupo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGrupo.setAdapter(adapterGrupo);

        btnGuardar.setOnClickListener(v -> {

            String nombre = edtNombre.getText().toString().trim();
            String ApellidoP = edtApellidoP.getText().toString().trim();
            String ApellidoM = edtApellidoM.getText().toString().trim();
            String correo =edtCorreo.getText().toString().trim();
            String contrasenia = edtContrasenia.getText().toString().trim();
            String mat= edtMatricula.getText().toString().trim();
            Integer matricula = Integer.valueOf(mat);
            String grupoSeleccionado = spinnerGrupo.getSelectedItem().toString();

            int idgrupo =0;
            for(Grupo g: listaGrupo){
                if (g.getGrupo().equals(grupoSeleccionado)){
                    idgrupo =g.getId();
                    break;
                }
            }

            new Thread(() -> {
                // 1. Buscamos en segundo plano
                Usuarios existente = dao.obtenerIdPorMatricula(matricula);

                // 2. Volvemos al hilo principal para actualizar la interfaz
                runOnUiThread(() -> {
                    if (existente != null) {
                        edtMatricula.setError("Ya existe un usuario con esta matricula");
                    } else{
                        if (existente == null) {
                            Usuarios nuevo = new Usuarios(matricula, nombre, ApellidoP, ApellidoM, idgrupo, );
                            dao.insertarUsuario(nuevo);


                            runOnUiThread(this::finish);
                        }
                    }
                });
            }).start();

        });



    }
}