package expansion.neto.com.mx.gerenteapp.sorted.proceso;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatProceso;

public class ReceivedMessageHolder extends RecyclerView.ViewHolder {
    TextView messageText, timeText, nameText;
    ImageView profileImage;

    private final int AREA_EXPANSION = 1;
    private final int AREA_GESTORIA = 2;
    private final int AREA_CONSTRUCCION = 3;
    private final int AREA_OPERACIONES = 5;

    private static final int TIPO_COMENTARIO_EVALUACIONES = 1;
    private static final int TIPO_COMENTARIO_GENERAL = 2;
    private static final int TIPO_COMENTARIO_AREA = 4;

    public ReceivedMessageHolder(View itemView) {
        super(itemView);

        messageText = (TextView) itemView.findViewById(R.id.text_message_body);
        timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        nameText = (TextView) itemView.findViewById(R.id.text_message_name);
        profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
    }

    public void bind(ChatProceso.MensajeChat message, Context context, int tipoComentario) {

        if(message.getArea()==null){
            message.setNombreFactor(context.getString(R.string.general));
        }

        if(tipoComentario == TIPO_COMENTARIO_EVALUACIONES) {

            String nombreArea = message.getArea();
            nombreArea = nombreArea.substring(0,1).toUpperCase() + nombreArea.substring(1).toLowerCase();
            nombreArea = nombreArea+": "+" \n"+message.getComentario();
            SpannableString ss1 =  new SpannableString(nombreArea);
            String[] nombreAreaSplit = nombreArea.split(": ");
            ss1.setSpan(new RelativeSizeSpan(.8f), 0, nombreAreaSplit[0].length()+1, 0); // set size
            ss1.setSpan(new ForegroundColorSpan(Color.parseColor(context.getString(R.string.colorazul))), 0, nombreAreaSplit[0].length()+1, 0);// set color
            messageText.setText(ss1);

        } else if(tipoComentario == TIPO_COMENTARIO_GENERAL || tipoComentario == TIPO_COMENTARIO_AREA)  {

            String nombreArea = message.getArea();
            nombreArea = nombreArea.substring(0,1).toUpperCase() + nombreArea.substring(1).toLowerCase();
            nombreArea = nombreArea+": "+" \n"+message.getComentario();
            SpannableString ss1 =  new SpannableString(nombreArea);
            String[] nombreAreaSplit = nombreArea.split(": ");
            ss1.setSpan(new RelativeSizeSpan(.8f), 0, nombreAreaSplit[0].length()+1, 0); // set size
            ss1.setSpan(new ForegroundColorSpan(Color.parseColor(context.getString(R.string.colorazul))), 0, nombreAreaSplit[0].length()+1, 0);// set color
            messageText.setText(ss1);

        }

        timeText.setText(message.getFecha());
        nameText.setText(message.getArea());

        final Resources resource = context.getResources();

        switch (message.getAreaId()) {
            case AREA_EXPANSION:
                profileImage.setImageDrawable(resource.getDrawable(R.drawable.expansioazul));
                break;
            case AREA_GESTORIA:
                profileImage.setImageDrawable(resource.getDrawable(R.drawable.gestoriaazul));
                break;
            case AREA_CONSTRUCCION:
                profileImage.setImageDrawable(resource.getDrawable(R.drawable.construccionazul));
                break;
            case AREA_OPERACIONES:
                profileImage.setImageDrawable(resource.getDrawable(R.drawable.operacionesazul));
                break;
        };
    }
}
