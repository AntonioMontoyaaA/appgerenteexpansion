package expansion.neto.com.mx.gerenteapp.sorted.autoriza;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.HorasPeatonales;

public class AdapterListaHoras extends RecyclerView.Adapter<AdapterListaHoras.ListaviewHolder> {

    private List<HorasPeatonales.Detalle> listas_im;
    Context fm;

    public AdapterListaHoras(List<HorasPeatonales.Detalle> listas_im,
                             Context fm){
        this.listas_im = listas_im;
        this.fm = fm;
    }

    public class ListaviewHolder extends RecyclerView.ViewHolder {
        TextView txtHoras;
        public ListaviewHolder(View itemView) {
            super(itemView);
            txtHoras = (TextView) itemView.findViewById(R.id.nombre);
        }
    }

    @Override
    public ListaviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListaviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_autoriza_hora_pricker, parent, false));
    }

    @Override
    public void onBindViewHolder(final ListaviewHolder holder, final int position) {
        holder.txtHoras.setText(listas_im.get(position).getHoraMin() + " - "+listas_im.get(position).getHoraMax());
    }

    @Override
    public int getItemCount() {
        return listas_im.size();
    }

}