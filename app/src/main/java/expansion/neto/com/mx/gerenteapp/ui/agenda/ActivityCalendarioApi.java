package expansion.neto.com.mx.gerenteapp.ui.agenda;

import android.widget.Toast;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.ui.agenda.model.CallbackInterface;
import expansion.neto.com.mx.gerenteapp.ui.agenda.model.Event;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ActivityCalendarioApi extends ActivityCalendarioSemana implements Callback<List<Event>> {

    private List<WeekViewEvent> events = new ArrayList<WeekViewEvent>();
    boolean calledNetwork = false;

    /**
     *  Verifica si un evento cae en un año y mes específico.
     * @param event
     * @param year
     * @param month
     * @return
     */
    private boolean eventMatches(WeekViewEvent event, int year, int month) {
        return (event.getStartTime().get(Calendar.YEAR) == year
                && event.getStartTime().get(Calendar.MONTH) == month - 1)
                || (event.getEndTime().get(Calendar.YEAR) == year
                && event.getEndTime().get(Calendar.MONTH) == month - 1);
    }

    @Override
    public List<? extends WeekViewEvent> onMonthChange(int newYear, int newMonth) {
        // Download events from network if it hasn't been done already. To understand how events are
        // downloaded using retrofit, visit http://square.github.io/retrofit
        if (!calledNetwork) {
            RestAdapter retrofit = new RestAdapter.Builder()
                    .setEndpoint("https://api.myjson.com/bins")
                    .build();
            CallbackInterface service = retrofit.create(CallbackInterface.class);
            service.listEvents("1",this);
            calledNetwork = true;
        }
        // Return only the events that matches newYear and newMonth.
        List<WeekViewEvent> matchedEvents = new ArrayList<WeekViewEvent>();
        for (WeekViewEvent event : events) {
            if (eventMatches(event, newYear, newMonth)) {
                matchedEvents.add(event);
            }
        }
        return matchedEvents;
    }

    @Override
    public void success(List<Event> events, Response response) {
        this.events.clear();
        for (Event event : events) {
            this.events.add(event.toWeekViewEvent());
        }
        getWeekView().notifyDatasetChanged();
    }

    @Override
    public void failure(RetrofitError error) {
        error.printStackTrace();
        Toast.makeText(this, R.string.salir, Toast.LENGTH_SHORT).show();
    }

}
