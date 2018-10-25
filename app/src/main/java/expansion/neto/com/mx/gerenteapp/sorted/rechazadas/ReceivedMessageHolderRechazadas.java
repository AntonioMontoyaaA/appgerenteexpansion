package expansion.neto.com.mx.gerenteapp.sorted.rechazadas;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatProceso;

public class ReceivedMessageHolderRechazadas extends RecyclerView.ViewHolder {
    TextView messageText, timeText, nameText;
    ImageView profileImage;

    private final int AREA_EXPANSION = 1;
    private final int AREA_GESTORIA = 2;
    private final int AREA_CONSTRUCCION = 3;
    private final int AREA_OPERACIONES = 5;

    private static final int TIPO_COMENTARIO_EVALUACIONES = 1;
    private static final int TIPO_COMENTARIO_GENERAL = 2;
    private static final int TIPO_COMENTARIO_AREA = 4;

    public ReceivedMessageHolderRechazadas(View itemView) {
        super(itemView);

        messageText = (TextView) itemView.findViewById(R.id.text_message_body);
        timeText = (TextView) itemView.findViewById(R.id.text_message_time);
        nameText = (TextView) itemView.findViewById(R.id.text_message_name);
        //profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
    }

    public void bind(ChatProceso.MensajeChat message, Context context, int tipoComentario) {
        if(tipoComentario == TIPO_COMENTARIO_EVALUACIONES) {
            messageText.setText("En " + message.getNombreFactor() + ". " + message.getComentario());
        } else if(tipoComentario == TIPO_COMENTARIO_GENERAL || tipoComentario == TIPO_COMENTARIO_AREA)  {
            messageText.setText(message.getComentario());
        }
        timeText.setText(message.getFecha());
        nameText.setText(message.getArea());

//        final Resources resource = context.getResources();
//
//        switch (message.getAreaId()) {
//            case AREA_EXPANSION:
//                profileImage.setImageDrawable(resource.getDrawable(R.drawable.expansioazul));
//                break;
//            case AREA_GESTORIA:
//                profileImage.setImageDrawable(resource.getDrawable(R.drawable.gestoriaazul));
//                break;
//            case AREA_CONSTRUCCION:
//                profileImage.setImageDrawable(resource.getDrawable(R.drawable.construccionazul));
//                break;
//            case AREA_OPERACIONES:
//                profileImage.setImageDrawable(resource.getDrawable(R.drawable.operacionesazul));
//                break;
//        };
    }
}
