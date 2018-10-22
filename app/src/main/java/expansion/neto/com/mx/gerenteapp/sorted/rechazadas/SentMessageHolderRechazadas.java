package expansion.neto.com.mx.gerenteapp.sorted.rechazadas;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatProceso;

public class SentMessageHolderRechazadas extends RecyclerView.ViewHolder {
    TextView messageText, timeText;

    private static final int TIPO_COMENTARIO_EVALUACIONES = 1;
    private static final int TIPO_COMENTARIO_GENERAL = 2;

    public SentMessageHolderRechazadas(View itemView) {
        super(itemView);

        messageText = (TextView) itemView.findViewById(R.id.text_message_body);
        timeText = (TextView) itemView.findViewById(R.id.text_message_time);
    }

    public void bind(ChatProceso.MensajeChat message, Context context, int tipoComentario) {
        if(tipoComentario == TIPO_COMENTARIO_EVALUACIONES) {
            messageText.setText("En " + message.getNombreFactor() + ". " + message.getComentario());
        } else if(tipoComentario == TIPO_COMENTARIO_GENERAL) {
            messageText.setText(message.getComentario());
        }
        messageText.setText(message.getComentario());
        timeText.setText(message.getFecha());
    }
}
