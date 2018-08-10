package expansion.neto.com.mx.gerenteapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class AutorizaModel implements SortedListAdapter.ViewModel {

    private long mId;
    @SerializedName("imagen")
    @Expose
    private String mImageURL;
    @SerializedName("numMD")
    @Expose
    private String numMD;
    @SerializedName("puntos")
    @Expose
    private Integer puntos;
    @SerializedName("categoria")
    @Expose
    private String categoria;
    @SerializedName("nombreCrea")
    @Expose
    private String nombreCrea;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lot")
    @Expose
    private Double lot;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("nombrePropietario")
    @Expose
    private String nombrePropietario;
    @SerializedName("telefonoPropietario")
    @Expose
    private String telefonoPropietario;
    @SerializedName("rentaNeto")
    @Expose
    private Boolean rentaNeto;
    @SerializedName("superficie")
    @Expose
    private Integer superficie;
    @SerializedName("frente")
    @Expose
    private Integer frente;
    @SerializedName("fondo")
    @Expose
    private Integer fondo;
    @SerializedName("imgFrontal")
    @Expose
    private String imgFrontal;
    @SerializedName("imgLateralIzquierda")
    @Expose
    private String imgLateralIzquierda;
    @SerializedName("imgLateralDerecha")
    @Expose
    private String imgLateralDerecha;
    @SerializedName("zonificacion")
    @Expose
    private List<Zonificaciones> zonificacion = null;
    @SerializedName("condicion")
    @Expose
    private String condicion;
    @SerializedName("zonas")
    @Expose
    private String zonas;
    @SerializedName("renta")
    @Expose
    private String renta;
    @SerializedName("disponibilidad")
    @Expose
    private String disponibilidad;
    @SerializedName("amortizacion")
    @Expose
    private String amortizacion;
    @SerializedName("periodoGracia")
    @Expose
    private String periodoGracia;
    @SerializedName("peatonal")
    @Expose
    private List<Peatonal> peatonal = null;

    public String getNumMD() {
        return numMD;
    }

    public void setNumMD(String numMD) {
        this.numMD = numMD;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombreCrea() {
        return nombreCrea;
    }

    public void setNombreCrea(String nombreCrea) {
        this.nombreCrea = nombreCrea;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLot() {
        return lot;
    }

    public void setLot(Double lot) {
        this.lot = lot;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombrePropietario() {
        return nombrePropietario;
    }

    public void setNombrePropietario(String nombrePropietario) {
        this.nombrePropietario = nombrePropietario;
    }

    public String getTelefonoPropietario() {
        return telefonoPropietario;
    }

    public void setTelefonoPropietario(String telefonoPropietario) {
        this.telefonoPropietario = telefonoPropietario;
    }

    public Boolean getRentaNeto() {
        return rentaNeto;
    }

    public void setRentaNeto(Boolean rentaNeto) {
        this.rentaNeto = rentaNeto;
    }

    public Integer getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Integer superficie) {
        this.superficie = superficie;
    }

    public Integer getFrente() {
        return frente;
    }

    public void setFrente(Integer frente) {
        this.frente = frente;
    }

    public Integer getFondo() {
        return fondo;
    }

    public void setFondo(Integer fondo) {
        this.fondo = fondo;
    }

    public String getImgFrontal() {
        return imgFrontal;
    }

    public void setImgFrontal(String imgFrontal) {
        this.imgFrontal = imgFrontal;
    }

    public String getImgLateralIzquierda() {
        return imgLateralIzquierda;
    }

    public void setImgLateralIzquierda(String imgLateralIzquierda) {
        this.imgLateralIzquierda = imgLateralIzquierda;
    }

    public String getImgLateralDerecha() {
        return imgLateralDerecha;
    }

    public void setImgLateralDerecha(String imgLateralDerecha) {
        this.imgLateralDerecha = imgLateralDerecha;
    }

    public List<Zonificaciones> getZonificacion() {
        return zonificacion;
    }

    public void setZonificacion(ArrayList<Zonificaciones> zonificacion) {
        this.zonificacion = zonificacion;
    }

    public String getCondicion() {
        return condicion;
    }

    public void setCondicion(String condicion) {
        this.condicion = condicion;
    }

    public String getZonas() {
        return zonas;
    }

    public void setZonas(String zonas) {
        this.zonas = zonas;
    }

    public String getRenta() {
        return renta;
    }

    public void setRenta(String renta) {
        this.renta = renta;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getAmortizacion() {
        return amortizacion;
    }

    public void setAmortizacion(String amortizacion) {
        this.amortizacion = amortizacion;
    }

    public String getPeriodoGracia() {
        return periodoGracia;
    }

    public void setPeriodoGracia(String periodoGracia) {
        this.periodoGracia = periodoGracia;
    }

    public List<Peatonal> getPeatonal() {
        return peatonal;
    }

    public void setPeatonal(ArrayList<Peatonal> peatonal) {
        this.peatonal = peatonal;
    }
    public long getmId() {
        return mId;
    }

    public void setmId(long mId) {
        this.mId = mId;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public void setmImageURL(String mImageURL) {
        this.mImageURL = mImageURL;
    }



}


