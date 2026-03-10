package yael.alcantara.uptreport;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class Primera_interfaz_reporte extends AppCompatActivity {
     public EditText edtDescripcion;
     public Button btnSiguiente, btnReportes;
     private int idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primera_interfaz_reporte);
        
        idUsuario = getIntent().getIntExtra("idUsuario", -1);
        
        edtDescripcion=findViewById(R.id.edtmDescripcion);
        btnSiguiente=findViewById(R.id.btnSiguiente);
        btnReportes=findViewById(R.id.btnReportes);
        btnSiguiente.setOnClickListener(v -> {
            String descripcion = edtDescripcion.getText().toString().trim();

            if(!descripcion.isEmpty()){
                Intent siguientePag = new Intent(Primera_interfaz_reporte.this, segunda_interfaz_reporte.class);
                siguientePag.putExtra("descripcion", descripcion);
                siguientePag.putExtra("idUsuario", idUsuario);
                startActivity(siguientePag);
                finish();
            } else {
                edtDescripcion.setError("Ingresa una descripción");
                edtDescripcion.requestFocus();
            }
        });

        btnReportes.setOnClickListener(v ->{
            Intent historial_reportes = new Intent(Primera_interfaz_reporte.this, historial_reportes.class);
            startActivity(historial_reportes);
        });
    }
}
