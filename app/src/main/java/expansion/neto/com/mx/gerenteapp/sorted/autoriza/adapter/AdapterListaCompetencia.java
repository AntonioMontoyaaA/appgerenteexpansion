package expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.Zonificacion;
/**
 * Created by Kevin on 26/6/2017.
 */

public class AdapterListaCompetencia extends RecyclerView.Adapter<AdapterListaCompetencia.ListaviewHolder> {

    private List<Zonificacion.Detalle> listas_im;
    Context fm;


    public AdapterListaCompetencia(List<Zonificacion.Detalle> listas_im, Context fm){
        this.listas_im = listas_im;
        this.fm = fm;
    }

    public class ListaviewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre;
        ImageView imagen;

        public ListaviewHolder(View itemView) {
            super(itemView);

            txtNombre = (TextView) itemView.findViewById(R.id.nombre);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);

        }

    }

    @Override
    public ListaviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListaviewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_autoriza_competencia_pricker, parent, false));
    }

    @Override
    public void onBindViewHolder(ListaviewHolder holder, final int position) {

        holder.txtNombre.setText(listas_im.get(position).getNombreGenerador());

        if(listas_im.get(position).getGeneradorId()==1){
            String uri = "@drawable/bbb";
            int imageResource = fm.getResources().getIdentifier(uri, null, fm.getPackageName());
            Drawable res = fm.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else if(listas_im.get(position).getGeneradorId()==2){
            String uri = "@drawable/oxxo";
            int imageResource = fm.getResources().getIdentifier(uri, null, fm.getPackageName());
            Drawable res = fm.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else if(listas_im.get(position).getGeneradorId()==3){
            String uri = "@drawable/bodegaa";
            int imageResource = fm.getResources().getIdentifier(uri, null, fm.getPackageName());
            Drawable res = fm.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else if(listas_im.get(position).getGeneradorId()==4){
            String uri = "@drawable/abarrotes";
            int imageResource = fm.getResources().getIdentifier(uri, null, fm.getPackageName());
            Drawable res = fm.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }else{
            String uri = "@drawable/otros";
            int imageResource = fm.getResources().getIdentifier(uri, null, fm.getPackageName());
            Drawable res = fm.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }

    }

    @Override
    public int getItemCount() {
        return listas_im.size();
    }

}
