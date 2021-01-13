package expansion.neto.com.mx.gerenteapp.fragment.fragmentCreacion;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.FragmentMostrarTipBinding;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.Tips;
import expansion.neto.com.mx.gerenteapp.sorted.autoriza.adapter.AdapterListaTips;

/**
 * Created by Kevin on 26/6/2017.
 */

public class FragmentDialogMostrarTip extends DialogFragment {

    AdapterListaTips adapterListaTips;
    FragmentMostrarTipBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mostrar_tip,container,false);
        View view = binding.getRoot();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        System.out.println( "ancho absoluto en pixels "+ width );
        int height = metrics.heightPixels;
        System.out.println( "alto absoluto en pixels " + height );
        Window window = getDialog().getWindow();
        if (width <= 400 && height <= 400){
            window.setLayout( width-50 , height-50 );
        }else {
            window.setLayout( width - 100, height - 150 );
        }
        window.setGravity(Gravity.CENTER);

        SharedPreferences preferences = getContext().getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);

        Gson gson = new Gson();
        String json = preferences.getString("tips", "");
        Type type = new TypeToken<ArrayList<Tips.Tip>>() {}.getType();
        ArrayList<Tips.Tip> tips = gson.fromJson(json, type);


        for(int i=0;i<tips.size();i++){
            //Log.e("======", tips.get(i).getDetalle().toString());
        }

        //adapterListaTips = new AdapterListaTips(tips, getContext());
        binding.recyclerPropietarios.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerPropietarios.setAdapter(adapterListaTips);
    }


}