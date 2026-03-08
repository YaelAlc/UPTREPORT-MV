package yael.alcantara.uptreport;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import yael.alcantara.uptreport.db.Edificio;
import yael.alcantara.uptreport.db.Evidencias;
import yael.alcantara.uptreport.db.Reportes;
import yael.alcantara.uptreport.db.Salon;
import yael.alcantara.uptreport.db.Usuarios;
import yael.alcantara.uptreport.db.appDatabase;

public class segunda_interfaz_reporte extends AppCompatActivity {

    private ImageView imageView;
    private Button btnAdjuntar, btnEnviar;
    private Spinner spinnerEdificio, spinnerSalon;
    private EditText edtFecha;

    private String descripcion;
    private int idUsuario;
    private Uri imagenUri;

    private appDatabase database;
    private List<Edificio> listaEdificios;
    private List<Salon> listaSalones;

    private ActivityResultLauncher<Intent> seleccionarImagenLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segunda_interfaz_reporte);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        database = appDatabase.getInstance(this);


        imageView = findViewById(R.id.imageView4);
        btnAdjuntar = findViewById(R.id.button2);
        btnEnviar = findViewById(R.id.button);
        spinnerEdificio = findViewById(R.id.spinner);
        spinnerSalon = findViewById(R.id.spinner2);
        edtFecha = findViewById(R.id.editTextDate2);


        descripcion = getIntent().getStringExtra("descripcion");
        idUsuario = getIntent().getIntExtra("idUsuario", -1);


        seleccionarImagenLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK && result.getData() != null){
                        imagenUri = result.getData().getData();
                        imageView.setImageURI(imagenUri);
                    }
                }
        );


        cargarEdificios();

        btnAdjuntar.setOnClickListener(v -> abrirGaleria());

        edtFecha.setOnClickListener(v -> mostrarDatePicker());

        btnEnviar.setOnClickListener(v -> guardarReporte());

        spinnerEdificio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listaEdificios != null && !listaEdificios.isEmpty()) {
                    int idEdificio = listaEdificios.get(position).getId();
                    cargarSalones(idEdificio);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void cargarEdificios() {
        new Thread(() -> {
            listaEdificios = database.edificioDao().getAllEdificios();
            List<String> nombresEdificios = new ArrayList<>();
            for (Edificio e : listaEdificios) {
                nombresEdificios.add(e.getEdificio());
            }

            runOnUiThread(() -> {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresEdificios);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerEdificio.setAdapter(adapter);
            });
        }).start();
    }

    private void cargarSalones(int idEdificio) {
        new Thread(() -> {
            listaSalones = database.salonDao().getSalonesByEdificio(idEdificio);
            List<String> nombresSalones = new ArrayList<>();
            for (Salon s : listaSalones) {
                nombresSalones.add(s.getSalon());
            }

            runOnUiThread(() -> {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresSalones);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerSalon.setAdapter(adapter);
            });
        }).start();
    }

    private void abrirGaleria(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        seleccionarImagenLauncher.launch(intent);
    }

    private void mostrarDatePicker(){
        Calendar calendario = Calendar.getInstance();
        int year = calendario.get(Calendar.YEAR);
        int month = calendario.get(Calendar.MONTH);
        int day = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (view, y, m, d) -> {
                    String fecha = d + "/" + (m + 1) + "/" + y;
                    edtFecha.setText(fecha);
                },
                year,
                month,
                day
        );
        dialog.show();
    }

    private void guardarReporte(){
        String fechaStr = edtFecha.getText().toString().trim();
        if (fechaStr.isEmpty()) {
            Toast.makeText(this, "Por favor selecciona una fecha", Toast.LENGTH_SHORT).show();
            return;
        }

        if (spinnerEdificio.getSelectedItem() == null || spinnerSalon.getSelectedItem() == null) {
            Toast.makeText(this, "Por favor selecciona edificio y salón", Toast.LENGTH_SHORT).show();
            return;
        }

        int posEdificio = spinnerEdificio.getSelectedItemPosition();
        int posSalon = spinnerSalon.getSelectedItemPosition();

        int idEdificio = listaEdificios.get(posEdificio).getId();
        int idSalon = listaSalones.get(posSalon).getId();

        String urlImagen = (imagenUri != null) ? imagenUri.toString() : null;

        new Thread(() -> {
            Usuarios usuario = database.usuariosDao().getUsuarioById(idUsuario);
            if (usuario == null) {
                runOnUiThread(() -> Toast.makeText(this, "Error: Usuario no encontrado", Toast.LENGTH_SHORT).show());
                return;
            }

            int idEstadoPendiente = 1;

            Reportes reporte = new Reportes(
                    usuario.getNombre(),
                    usuario.getApellidoP(),
                    usuario.getApellidoM(),
                    Integer.parseInt(usuario.getMatricula()),
                    String.valueOf(usuario.getIdgrupo()),
                    idEdificio,
                    idSalon,
                    idUsuario,
                    descripcion,
                    new Date(),
                    idEstadoPendiente
            );

            long idReporte = database.reportesDao().insertarReportes(reporte);

            if (urlImagen != null) {
                Evidencias evidencia = new Evidencias((int) idReporte, urlImagen);
                database.evidenciasDao().insertarEvidencias(evidencia);
            }

            runOnUiThread(() -> {
                Toast.makeText(this, "Reporte enviado con éxito", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(segunda_interfaz_reporte.this, historial_reportes.class);
                intent.putExtra("idUsuario", idUsuario);
                startActivity(intent);
                finish();
            });
        }).start();
    }
}
