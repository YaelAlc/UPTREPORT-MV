package yael.alcantara.uptreport;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import yael.alcantara.uptreport.db.Reportes;
import yael.alcantara.uptreport.db.appDatabase;

public class historial_reportes extends AppCompatActivity {

    private LinearLayout layoutReportes;
    private Spinner spinnerEstado;
    private int idUsuario = 1; // Aquí pones el ID del usuario logueado
    private appDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historial_reportes);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencias
        layoutReportes = findViewById(R.id.layoutReportes); // LinearLayout dentro del ScrollView
        spinnerEstado = findViewById(R.id.spinner3);

        database = appDatabase.getInstance(this);

        // Configurar spinner con filtros
        configurarSpinnerEstado();
    }

    // Mapeo id_estado → texto
    private String getNombreEstado(int id_estado){
        switch(id_estado){
            case 1: return "Pendiente";
            case 2: return "En proceso";
            case 3: return "Completo";
            default: return "Desconocido";
        }
    }

    // Configura spinner y listener
    private void configurarSpinnerEstado(){
        String[] estados = {"Todos", "Pendiente", "En proceso", "Completo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);

        spinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // position 0 = Todos, 1 = Pendiente, 2 = En proceso, 3 = Completo
                int estadoSeleccionado = position;
                cargarReportesFiltrados(estadoSeleccionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    // Cargar reportes filtrados por estado
    private void cargarReportesFiltrados(int estadoSeleccionado){
        new Thread(() -> {
            List<Reportes> reportes;

            if(estadoSeleccionado == 0){ // Todos
                reportes = database.reportesDao().getReportesPorUsuario(idUsuario);
            } else {
                reportes = database.reportesDao().getReportesPorUsuarioYEstado(idUsuario, estadoSeleccionado);
            }

            runOnUiThread(() -> {
                layoutReportes.removeAllViews();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

                for(Reportes r : reportes){
                    View item = getLayoutInflater().inflate(R.layout.item_reporte, layoutReportes, false);

                    TextView tvDescripcion = item.findViewById(R.id.tvDescripcion);
                    TextView tvEdificio = item.findViewById(R.id.tvEdificio);
                    TextView tvSalon = item.findViewById(R.id.tvSalon);
                    TextView tvFecha = item.findViewById(R.id.tvFecha);
                    TextView tvEstado = item.findViewById(R.id.tvEstado);

                    tvDescripcion.setText(r.getDescripcion());
                    tvEdificio.setText("Edificio: " + r.getIdedificio());
                    tvSalon.setText("Salón: " + r.getIdsalon());
                    tvFecha.setText("Fecha: " + sdf.format(r.getFecha()));
                    tvEstado.setText("Estado: " + getNombreEstado(r.getId_estado()));

                    layoutReportes.addView(item);
                }
            });
        }).start();
    }
}