package yael.alcantara.uptreport.db;

import androidx.room.Ignore;

import java.util.Date;
import java.util.List;

// Esta clase contendrá los reportes junto con nombres y evidencias
public class ReporteCompleto {
    public int id;
    public String Nombre;
    public String ApellidoP;
    public String ApellidoM;
    public int Matricula;
    public String Grupo;
    public int idedificio;
    public int idsalon;
    public int idusuarios;
    public String Descripcion;
    public Date Fecha;
    public int id_estado;

    public String nombreEdificio;
    public String nombreSalon;

    @Ignore
    public List<String> evidencias;
}
