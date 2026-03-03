package yael.alcantara.uptreport;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class reportes_admin extends AppCompatActivity {

    RecyclerView recyclerReportes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes_admin);

        recyclerReportes = findViewById(R.id.recyclerReportes);

        recyclerReportes.setLayoutManager(new LinearLayoutManager(this));

        // Datos de prueba (luego los cambiamos por la BD)
        List<String> lista = new ArrayList<>();
        lista.add("Reporte 1");
        lista.add("Reporte 2");
        lista.add("Reporte 3");

        ReporteAdapter adapter = new ReporteAdapter(lista);
        recyclerReportes.setAdapter(adapter);
    }
}