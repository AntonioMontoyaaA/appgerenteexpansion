package expansion.neto.com.mx.gerenteapp.modelView.autorizaModel;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class Peatonal implements SortedListAdapter.ViewModel {


    @SerializedName("numConteo")
    @Expose
    private Integer numConteo;
    @SerializedName("fechaPeatonal")
    @Expose
    private String fechaPeatonal;
    @SerializedName("numPeatones")
    @Expose
    private Integer numPeatones;
    @SerializedName("horaInicio")
    @Expose
    private Double horaInicio;
    @SerializedName("horaFinal")
    @Expose
    private Double horaFinal;
    @SerializedName("nombreGenerador")
    @Expose
    private String nombreGenerador;


    public Peatonal(Integer numConteo, String fechaPeatonal, Integer numPeatones, Double horaInicio, Double horaFinal, String nombreGenerador) {
        this.numConteo = numConteo;
        this.fechaPeatonal = fechaPeatonal;
        this.numPeatones = numPeatones;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.nombreGenerador = nombreGenerador;
    }

    public String getNombreGenerador() {
        return nombreGenerador;
    }

    public void setNombreGenerador(String nombreGenerador) {
        this.nombreGenerador = nombreGenerador;
    }

    public Integer getNumConteo() {
        return numConteo;
    }

    public void setNumConteo(Integer numConteo) {
        this.numConteo = numConteo;
    }

    public String getFechaPeatonal() {
        return fechaPeatonal;
    }

    public void setFechaPeatonal(String fechaPeatonal) {
        this.fechaPeatonal = fechaPeatonal;
    }

    public Integer getNumPeatones() {
        return numPeatones;
    }

    public void setNumPeatones(Integer numPeatones) {
        this.numPeatones = numPeatones;
    }

    public Double getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Double horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Double getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Double horaFinal) {
        this.horaFinal = horaFinal;
    }


    public void onClickCalificar(String algo) {
    }


}
