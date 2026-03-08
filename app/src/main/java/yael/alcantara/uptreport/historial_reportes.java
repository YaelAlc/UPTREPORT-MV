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

import yael.alcantara.uptreport.db.ReporteCompleto;
import yael.alcantara.uptreport.db.appDatabase;

public class historial_reportes extends AppCompatActivity {

    private LinearLayout layoutReportes;
    private Spinner spinnerEstado;
    private int idUsuario = 1;
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

        layoutReportes = findViewById(R.id.layoutReportes);
        spinnerEstado = findViewById(R.id.spinner3);
        database = appDatabase.getInstance(this);

        configurarSpinnerEstado();
    }

    private String getNombreEstado(int id_estado){
        switch(id_estado){
            case 1: return "Pendiente";
            case 2: return "En proceso";
            case 3: return "Completo";
            default: return "Desconocido";
        }
    }

    private void configurarSpinnerEstado(){
        String[] estados = {"Todos", "Pendiente", "En proceso", "Completo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, estados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);

        spinnerEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int estadoSeleccionado = position;
                cargarReportesFiltrados(estadoSeleccionado);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void cargarReportesFiltrados(int estadoSeleccionado){
        new Thread(() -> {
            List<ReporteCompleto> reportes;

            if(estadoSeleccionado == 0){
                reportes = database.reportesDao().getReportesPorUsuario(idUsuario);
            } else {
                reportes = database.reportesDao().getReportesPorUsuarioYEstado(idUsuario, estadoSeleccionado);
            }

            runOnUiThread(() -> {
                layoutReportes.removeAllViews();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

                for(ReporteCompleto r : reportes){
                    View item = getLayoutInflater().inflate(R.layout.item_reporte, layoutReportes, false);

                    TextView tvDescripcion = item.findViewById(R.id.tvDescripcion);
                    TextView tvEdificio = item.findViewById(R.id.tvEdificio);
                    TextView tvSalon = item.findViewById(R.id.tvSalon);
                    TextView tvFecha = item.findViewById(R.id.tvFecha);
                    TextView tvEstado = item.findViewById(R.id.tvEstado);
                    TextView tvEvidencias = item.findViewById(R.id.tvEvidencias);

                    tvDescripcion.setText(r.Descripcion);
                    tvEdificio.setText("Edificio: " + r.nombreEdificio);
                    tvSalon.setText("Salón: " + r.nombreSalon);
                    tvFecha.setText("Fecha: " + sdf.format(r.Fecha));
                    tvEstado.setText("Estado: " + getNombreEstado(r.id_estado));

                    List<String> urls = database.reportesDao().getEvidenciasPorReporte(r.id);
                    if(urls.isEmpty()){
                        tvEvidencias.setText("Evidencias: Ninguna");
                    } else {
                        StringBuilder sb = new StringBuilder("Evidencias:\n");
                        for(String u : urls){
                            sb.append(u).append("\n");
                        }
                        tvEvidencias.setText(sb.toString());
                    }

                    layoutReportes.addView(item);
                }
            });
        }).start();
    }
}
