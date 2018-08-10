package expansion.neto.com.mx.gerenteapp.ui.agenda.model;

import android.annotation.SuppressLint;
import android.graphics.Color;

import com.alamkanak.weekview.WeekViewEvent;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Event {

    @Expose
    @SerializedName("nombre")
    private String nombre;
    @Expose
    @SerializedName("diaMes")
    private int diaMes;
    @Expose
    @SerializedName("tiempoInicio")
    private String tiempoInicio;
    @Expose
    @SerializedName("tiempoFinal")
    private String tiempoFinal;
    @Expose
    @SerializedName("color")
    private String color;

    public String getName() {
        return nombre;
    }

    public void setName(String name) {
        this.nombre = name;
    }

    public int getDayOfMonth() {
        return diaMes;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.diaMes = dayOfMonth;
    }

    public String getStartTime() {
        return tiempoInicio;
    }

    public void setStartTime(String startTime) {
        this.tiempoInicio = startTime;
    }

    public String getEndTime() {
        return tiempoFinal;
    }

    public void setEndTime(String endTime) {
        this.tiempoFinal = endTime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @SuppressLint("SimpleDateFormat")
    public WeekViewEvent toWeekViewEvent(){

        // Parse time.
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date start = new Date();
        Date end = new Date();
        try {
            start = sdf.parse(getStartTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            end = sdf.parse(getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Initialize start and end time.
        Calendar now = Calendar.getInstance();
        Calendar startTime = (Calendar) now.clone();
        startTime.setTimeInMillis(start.getTime());
        startTime.set(Calendar.YEAR, now.get(Calendar.YEAR));
        startTime.set(Calendar.MONTH, now.get(Calendar.MONTH));
        startTime.set(Calendar.DAY_OF_MONTH, getDayOfMonth());
        Calendar endTime = (Calendar) startTime.clone();
        endTime.setTimeInMillis(end.getTime());
        endTime.set(Calendar.YEAR, startTime.get(Calendar.YEAR));
        endTime.set(Calendar.MONTH, startTime.get(Calendar.MONTH));
        endTime.set(Calendar.DAY_OF_MONTH, startTime.get(Calendar.DAY_OF_MONTH));

        // Create an week view event.
        WeekViewEvent weekViewEvent = new WeekViewEvent();
        weekViewEvent.setName(getName());
        weekViewEvent.setStartTime(startTime);
        weekViewEvent.setEndTime(endTime);
        weekViewEvent.setColor(Color.parseColor(getColor()));

        return weekViewEvent;
    }
}
