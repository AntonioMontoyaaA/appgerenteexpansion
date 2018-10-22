package expansion.neto.com.mx.gerenteapp.sorted.rechazadas.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.modelView.procesoModel.ChatProceso.MensajeChat;
import expansion.neto.com.mx.gerenteapp.sorted.rechazadas.EventoMessageHolderRechazadas;
import expansion.neto.com.mx.gerenteapp.sorted.rechazadas.ReceivedMessageHolderRechazadas;
import expansion.neto.com.mx.gerenteapp.sorted.rechazadas.SentMessageHolderRechazadas;

public class MensajeChatAdapterRechazadas extends RecyclerView.Adapter  {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    private static final int VIEW_TYPE_MESSAGE_EVENTOS = 3;

    private static final int TIPO_COMENTARIO_EVENTOS = 3;

    private Context mContext;
    private List<MensajeChat> mMessageList;
    private int tipoComentario;

    public MensajeChatAdapterRechazadas(Context context, List<MensajeChat> messageList) {
        mContext = context;
        mMessageList = messageList;
    }

    @Override
    public int getItemCount() {
        return mMessageList.size();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        MensajeChat message = mMessageList.get(position);
        tipoComentario = message.getTipoComentario();

        SharedPreferences preferences = mContext.getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        int usuarioId = 0;
        if(preferences.getString("usuario","") != null) {
            usuarioId = Integer.parseInt(preferences.getString("usuario",""));
        }

        if(tipoComentario == TIPO_COMENTARIO_EVENTOS) {
            return VIEW_TYPE_MESSAGE_EVENTOS;
        } else if(message.getUsuarioId() == usuarioId) {
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    // Inflates the appropriate layout according to the ViewType.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sent, parent, false);
            return new SentMessageHolderRechazadas(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolderRechazadas(view);
        } else if(viewType == VIEW_TYPE_MESSAGE_EVENTOS) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_eventos, parent, false);
            return new EventoMessageHolderRechazadas(view);
        }

        return null;
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MensajeChat message = mMessageList.get(position);

        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolderRechazadas) holder).bind(message, mContext, tipoComentario);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolderRechazadas) holder).bind(message, mContext, tipoComentario);
                break;
            case VIEW_TYPE_MESSAGE_EVENTOS:
                ((EventoMessageHolderRechazadas) holder).bind(message, mContext);
                break;
        }
    }

}
