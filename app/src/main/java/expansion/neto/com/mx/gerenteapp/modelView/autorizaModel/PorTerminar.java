package expansion.neto.com.mx.gerenteapp.modelView.autorizaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class PorTerminar implements SortedListAdapter.ViewModel {


    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("memorias")
    @Expose
    private List<Memoria> memorias = null;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Memoria> getMemorias() {
        return memorias;
    }

    public void setMemorias(List<Memoria> memorias) {
        this.memorias = memorias;
    }

    public class Memoria implements SortedListAdapter.ViewModel {

        @SerializedName("memoriaid")
        @Expose
        private String memoriaid;
        @SerializedName("categoria")
        @Expose
        private String categoria;
        @SerializedName("parciales")
        @Expose
        private Integer parciales;
        @SerializedName("totales")
        @Expose
        private Integer totales;
        @SerializedName("creador")
        @Expose
        private String creador;
        @SerializedName("puntosxcategoria")
        @Expose
        private Integer puntosxcategoria;
        @SerializedName("puntuacionmd")
        @Expose
        private Float puntuacionmd;
        @SerializedName("faltantes")
        @Expose
        private List<Object> faltantes = null;
        @SerializedName("nombresitio")
        @Expose
        private String nombresitio;
        @SerializedName("fechaCreacion")
        @Expose
        private String fechaCreacion;
        @SerializedName("fechaVencimiento")
        @Expose
        private String fechaVencimiento;
        @SerializedName("atrasada")
        @Expose
        private int atrasada;

        public String getFechaVencimiento(){

            if(fechaVencimiento!=null){
                Date date = null;
                try {
                    date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(fechaVencimiento);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
                return ""+formattedDate;
            }

            return "";
        }

        public void setFechaVencimiento(String fechaVencimiento) {
            this.fechaVencimiento = fechaVencimiento;
        }

        public int getAtrasada() {
            return atrasada;
        }

        public void setAtrasada(int atrasada) {
            this.atrasada = atrasada;
        }

        public String getFechaCreacion() {

            Date date = null;
            try {
                date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(fechaCreacion);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String formattedDate = new SimpleDateFormat("dd/MM/yyyy").format(date);

            return ""+formattedDate;

        }

        public void setFechaCreacion(String fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
        }

        public String getMemoriaid() {


            return memoriaid;
        }

        public void setMemoriaid(String memoriaid) {
            this.memoriaid = memoriaid;
        }

        public String getCategoria() {
            if(categoria==null){
                categoria = "-";
            }
            return categoria;
        }

        public void setCategoria(String categoria) {
            this.categoria = categoria;
        }

        public Integer getParciales() {
            return parciales;
        }

        public void setParciales(Integer parciales) {
            this.parciales = parciales;
        }

        public Integer getTotales() {
            return totales;
        }

        public void setTotales(Integer totales) {
            this.totales = totales;
        }

        public String getCreador() {
            return creador;
        }

        public void setCreador(String creador) {
            this.creador = creador;
        }

        public Integer getPuntosxcategoria() {
            return puntosxcategoria;
        }

        public void setPuntosxcategoria(Integer puntosxcategoria) {
            this.puntosxcategoria = puntosxcategoria;
        }

        public Float getPuntuacionmd() {
            return puntuacionmd;
        }

        public void setPuntuacionmd(Float puntuacionmd) {
            this.puntuacionmd = puntuacionmd;
        }

        public List<Object> getFaltantes() {
            return faltantes;
        }

        public void setFaltantes(List<Object> faltantes) {
            this.faltantes = faltantes;
        }

        public String getNombresitio() {
            return nombresitio;
        }

        public void setNombresitio(String nombresitio) {
            this.nombresitio = nombresitio;
        }
    }
}
