package expansion.neto.com.mx.gerenteapp.modelView.agendaModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Empleados {

    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("empleados")
    @Expose
    private Empleado empleados;

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

    public Empleado getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleado empleados) {
        this.empleados = empleados;
    }

    public class Empleado {

        @SerializedName("EXPANSION")
        @Expose
        private EXPANSION eXPANSION;

        public EXPANSION getEXPANSION() {
            return eXPANSION;
        }

        public void setEXPANSION(EXPANSION eXPANSION) {
            this.eXPANSION = eXPANSION;
        }

        public class EXPANSION {

            @SerializedName("GERENTE")
            @Expose
            private List<GERENTE> gERENTE = null;
            @SerializedName("JEFE")
            @Expose
            private List<JEFE> jEFE = null;

            public List<GERENTE> getGERENTE() {
                return gERENTE;
            }

            public void setGERENTE(List<GERENTE> gERENTE) {
                this.gERENTE = gERENTE;
            }

            public List<JEFE> getJEFE() {
                return jEFE;
            }

            public void setJEFE(List<JEFE> jEFE) {
                this.jEFE = jEFE;
            }

            public class GERENTE {

                @SerializedName("puesto")
                @Expose
                private String puesto;
                @SerializedName("nombre")
                @Expose
                private String nombre;
                @SerializedName("empleadoId")
                @Expose
                private String empleadoId;
                @SerializedName("puestoId")
                @Expose
                private Integer puestoId;
                @SerializedName("areas")
                @Expose
                private List<Area> areas = null;

                public String getPuesto() {
                    return puesto;
                }

                public void setPuesto(String puesto) {
                    this.puesto = puesto;
                }

                public String getNombre() {
                    return nombre;
                }

                public void setNombre(String nombre) {
                    this.nombre = nombre;
                }

                public String getEmpleadoId() {
                    return empleadoId;
                }

                public void setEmpleadoId(String empleadoId) {
                    this.empleadoId = empleadoId;
                }

                public Integer getPuestoId() {
                    return puestoId;
                }

                public void setPuestoId(Integer puestoId) {
                    this.puestoId = puestoId;
                }

                public List<Area> getAreas() {
                    return areas;
                }

                public void setAreas(List<Area> areas) {
                    this.areas = areas;
                }

            }

            public class JEFE {

                @SerializedName("puesto")
                @Expose
                private String puesto;
                @SerializedName("nombre")
                @Expose
                private String nombre;
                @SerializedName("empleadoId")
                @Expose
                private String empleadoId;
                @SerializedName("puestoId")
                @Expose
                private Integer puestoId;
                @SerializedName("areas")
                @Expose
                private List<Area> areas = null;

                public String getPuesto() {
                    return puesto;
                }

                public void setPuesto(String puesto) {
                    this.puesto = puesto;
                }

                public String getNombre() {
                    return nombre;
                }

                public void setNombre(String nombre) {
                    this.nombre = nombre;
                }

                public String getEmpleadoId() {
                    return empleadoId;
                }

                public void setEmpleadoId(String empleadoId) {
                    this.empleadoId = empleadoId;
                }

                public Integer getPuestoId() {
                    return puestoId;
                }

                public void setPuestoId(Integer puestoId) {
                    this.puestoId = puestoId;
                }

                public List<Area> getAreas() {
                    return areas;
                }

                public void setAreas(List<Area> areas) {
                    this.areas = areas;
                }

            }

            public class Area {

                @SerializedName("nombre")
                @Expose
                private String nombre;
                @SerializedName("areaId")
                @Expose
                private Integer areaId;

                public String getNombre() {
                    return nombre;
                }

                public void setNombre(String nombre) {
                    this.nombre = nombre;
                }

                public Integer getAreaId() {
                    return areaId;
                }

                public void setAreaId(Integer areaId) {
                    this.areaId = areaId;
                }

            }


        }

    }


}
