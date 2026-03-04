package yael.alcantara.uptreport;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ReporteAdapter extends RecyclerView.Adapter<ReporteAdapter.ViewHolder> {

    private List<String> listaReportes;

    public ReporteAdapter(List<String> listaReportes) {
        this.listaReportes = listaReportes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reporte_admin, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtDescripcion.setText(listaReportes.get(position));
    }

    @Override
    public int getItemCount() {
        return listaReportes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtDescripcion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
        }
    }
}
