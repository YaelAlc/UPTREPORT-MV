package yael.alcantara.uptreport;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import yael.alcantara.uptreport.db.appDatabase;


public class registro_usuario extends AppCompatActivity {
    private EditText edtMatricula, edtNombre, edtApellidoP, edtApellidoM, edtCorreo, edtContrasenia;

    private Spinner spinnerGrupo;

    private Button btnGuardar;


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



    }
}