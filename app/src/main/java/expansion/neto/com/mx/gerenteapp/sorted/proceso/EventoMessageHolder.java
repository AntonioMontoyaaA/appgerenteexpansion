package expansion.neto.com.mx.gerenteapp.sorted.proceso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatProceso;

public class EventoMessageHolder extends RecyclerView.ViewHolder {
    TextView messageText, timeText;

    public EventoMessageHolder(View itemView) {
        super(itemView);

        messageText = (TextView) itemView.findViewById(R.id.text_message_body);
    }

    public void bind(ChatProceso.MensajeChat message, Context context) {
        messageText.setText(message.getComentario() + " - " + message.getFecharegistro());
    }
}
