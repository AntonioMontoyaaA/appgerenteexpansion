package expansion.neto.com.mx.gerenteapp.utils.desing;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    @Override
    public int getItemCount() {
        return 4;
    }

    String frente, lateral, lateral2, predial;
    public MainSliderAdapter(String frente, String lateral, String lateral2, String predial){
        this.frente = frente;
        this.lateral = lateral;
        this.lateral2 = lateral2;
        if(predial.isEmpty()){
            predial = "http://www.sanisidrolonas.com.ar/wp-content/uploads/2014/05/sin-imagen.jpg";
        }
        this.predial = predial;
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
                viewHolder.bindImageSlide(predial);
                break;
        }
    }

}
