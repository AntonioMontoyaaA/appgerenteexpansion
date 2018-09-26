package expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.CompetenciasGeneradoresV2;


/**
 * Created by Kevin on 26/6/2017.
 */

public class AdapterListaTiendaNeto extends RecyclerView.Adapter<AdapterListaTiendaNeto.ListaviewHolder> {

    private List<CompetenciasGeneradoresV2.TiendaNeto> listas_im;
    Context fm;
    private OnItemClick mCallback;


    public AdapterListaTiendaNeto(List<CompetenciasGeneradoresV2.TiendaNeto> listas_im,
                                  Context fm, OnItemClick listener){
        this.listas_im = listas_im;
        this.fm = fm;
        this.mCallback = listener;
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
    public void onBindViewHolder(final ListaviewHolder holder, final int position) {

        holder.txtNombre.setText(listas_im.get(position).getNombreGenerador());

        if(listas_im.get(position).getGeneradorId()==10){
            String uri = "@drawable/neto";
            int imageResource = fm.getResources().getIdentifier(uri, null, fm.getPackageName());
            Drawable res = fm.getResources().getDrawable(imageResource);
            holder.imagen.setImageDrawable(res);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallback.onClick(listas_im.get(position).getGeneradorId(),
                        listas_im.get(position).getNombreGenerador(), listas_im.get(position).getNivelId());
                selectedPosition=position;
                notifyDataSetChanged();

            }
        });


        holder.txtNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mCallback.onClick(listas_im.get(position).getGeneradorId(),
                        listas_im.get(position).getNombreGenerador(), listas_im.get(position).getNivelId());
                selectedPosition=position;
                notifyDataSetChanged();

            }
        });

        if(selectedPosition==position) {
            holder.txtNombre.setTypeface(null, Typeface.BOLD);
        }else {
            holder.txtNombre.setTypeface(null, Typeface.NORMAL);
        }

    }

    int selectedPosition = -1;

    public interface OnItemClick {
        void onClick(int value, String nombre, int nivelId);
    }

    @Override
    public int getItemCount() {
        return listas_im.size();
    }

}
