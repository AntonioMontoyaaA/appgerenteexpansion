package expansion.neto.com.mx.gerenteapp.ui.agenda.model;


import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

public interface CallbackInterface {

    @FormUrlEncoded
    @POST("/ppifn")
    void listEvents(@Field("id") String id, Callback<List<Event>> eventsCallback);

}
