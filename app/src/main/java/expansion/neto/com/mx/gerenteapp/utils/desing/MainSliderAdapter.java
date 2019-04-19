package expansion.neto.com.mx.gerenteapp.utils.desing;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    @Override
    public int getItemCount() {
        return 9;
    }

    String frente, lateral, lateral2, entorno1, entorno2, entorno3, predial, agua, luz;
    public MainSliderAdapter(String frente, String lateral, String lateral2, String entorno1, String entorno2, String entorno3, String predial, String agua, String luz){
        this.frente = frente;
        this.lateral = lateral;
        this.lateral2 = lateral2;
        this.entorno1 = entorno1;
        this.entorno2 = entorno2;
        this.entorno3 = entorno3;
        if(predial.isEmpty()){
            predial = "http://www.sanisidrolonas.com.ar/wp-content/uploads/2014/05/sin-imagen.jpg";
        }
        this.predial = predial;
        this.agua = agua;
        this.luz = luz;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide(frente);
                break;
            case 1:
                viewHolder.bindImageSlide(lateral);
                break;
            case 2:
                viewHolder.bindImageSlide(lateral2);
                break;
            case 3:
                viewHolder.bindImageSlide(entorno1);
                break;
            case 4:
                viewHolder.bindImageSlide(entorno2);
                break;
            case 5:
                viewHolder.bindImageSlide(entorno3);
                break;
            case 6:
                viewHolder.bindImageSlide(predial);
                break;
            case 7:
                viewHolder.bindImageSlide(agua);
                break;
            case 8:
                viewHolder.bindImageSlide(luz);
                break;
        }
    }

}
