package expansion.neto.com.mx.gerenteapp.modelView.dashboardModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import expansion.neto.com.mx.gerenteapp.sorted.SortedListAdapter;

/**
 * Created by marcosmarroquin on 23/03/18.
 */

public class Dashboard implements SortedListAdapter.ViewModel {

    @SerializedName("totales")
    @Expose
    private List<Totales> totales = null;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("productividad")
    @Expose
    private List<Productividad> productividad = null;


    public List<Productividad> getProductividad() {
        return productividad;
    }

    public void setProductividad(List<Productividad> productividad) {
        this.productividad = productividad;
    }

    public List<Totales> getTotales() {
        return totales;
    }

    public void setTotales(List<Totales> totales) {
        this.totales = totales;
    }

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


    public class Totales {

        @SerializedName("descripcion")
        @Expose
        private String descripcion;
        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("estatusid")
        @Expose
        private Integer estatusid;

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getEstatusid() {
            return estatusid;
        }

        public void setEstatusid(Integer estatusid) {
            this.estatusid = estatusid;
        }

    }

    public class Productividad {

        @SerializedName("realSem")
        @Expose
        private Integer realSem;
        @SerializedName("planSem")
        @Expose
        private Integer planSem;
        @SerializedName("realMes")
        @Expose
        private Integer realMes;
        @SerializedName("planMes")
        @Expose
        private Integer planMes;
        @SerializedName("mes")
        @Expose
        private Integer mes;
        @SerializedName("semana")
        @Expose
        private Integer semana;

        public Integer getRealSem() {
            return realSem;
        }

        public void setRealSem(Integer realSem) {
            this.realSem = realSem;
        }

        public Integer getPlanSem() {
            return planSem;
        }

        public void setPlanSem(Integer planSem) {
            this.planSem = planSem;
        }

        public Integer getRealMes() {
            return realMes;
        }

        public void setRealMes(Integer realMes) {
            this.realMes = realMes;
        }

        public Integer getPlanMes() {
            return planMes;
        }

        public void setPlanMes(Integer planMes) {
            this.planMes = planMes;
        }

        public Integer getMes() {
            return mes;
        }

        public void setMes(Integer mes) {
            this.mes = mes;
        }

        public Integer getSemana() {
            return semana;
        }

        public void setSemana(Integer semana) {
            this.semana = semana;
        }

    }

}
