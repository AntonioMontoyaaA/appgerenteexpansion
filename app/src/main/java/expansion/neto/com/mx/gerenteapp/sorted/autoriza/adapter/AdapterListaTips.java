package expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.modelView.Tips;

;

/**
 * Created by Kevin on 26/6/2017.
 */

public class AdapterListaTips extends RecyclerView.Adapter<AdapterListaTips.ListaviewHolder> {

    private ArrayList<Tips.Tip> listas_im;
    Context fm;
    private AdapterListaTips.OnItemClick mCallback;


    public AdapterListaTips(ArrayList<Tips.Tip> listas_im, Context fm){
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
        return new ListaviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tips_pricker, parent, false));
    }

    @Override
    public void onBindViewHolder(final ListaviewHolder holder, final int position) {
        holder.txtHoras.setText(position+1+".- "+listas_im.get(position).getDetalle().toString()+" ");
    }


    public interface OnItemClick {
        void onClick(String nombre, String apellido, String apellidoM, String telefono, String email);
    }

    @Override
    public int getItemCount() {
        return listas_im.size();
    }

}
