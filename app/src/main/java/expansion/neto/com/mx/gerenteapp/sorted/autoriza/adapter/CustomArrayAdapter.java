package expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza.FragmentDialogCancelar;

public class CustomArrayAdapter extends BaseAdapter {
    Context context;
    List<FragmentDialogCancelar.MotivoVO> motivoVOS;
    LayoutInflater inflter;

    public CustomArrayAdapter(Context applicationContext, List<FragmentDialogCancelar.MotivoVO> motivoVOS) {
        this.context = applicationContext;
        this.motivoVOS = motivoVOS;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return motivoVOS.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.simple_spinner, null);

        TextView names = (TextView) view.findViewById(R.id.text);
        names.setText(motivoVOS.get(i).getDescripcion());
        if(motivoVOS.get(i).getRechazoDefinitivo()==1){
            names.setTextColor(context.getResources().getColor(R.color.rojo));
        }else{
            names.setTextColor(context.getResources().getColor(R.color.azul));
        }

        return view;
    }
}
