package expansion.neto.com.mx.gerenteapp.modelView.crearModel;

public class CrearGeneralidades {


    String mdId;
    String usuarioId;
    String renta;
    String disponibilidad;
    String fechadisponible;
    String porcentajeamortiza;
    String periodoamortizacion;
    String periodogracia;
    String numtelefono;
    String versionapp;

    public CrearGeneralidades(){}

    public CrearGeneralidades(String mdId, String usuarioId, String renta,
                              String disponibilidad, String fechadisponible, String porcentajeamortiza,
                              String periodoamortizacion, String periodogracia, String numtelefono,
                              String versionapp) {
        this.mdId = mdId;
        this.usuarioId = usuarioId;
        this.renta = renta;
        this.disponibilidad = disponibilidad;
        this.fechadisponible = fechadisponible;
        this.porcentajeamortiza = porcentajeamortiza;
        this.periodoamortizacion = periodoamortizacion;
        this.periodogracia = periodogracia;
        this.numtelefono = numtelefono;
        this.versionapp = versionapp;
    }

    public String getMdId() {
        return mdId;
    }

    public void setMdId(String mdId) {
        this.mdId = mdId;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
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

    public String getFechadisponible() {
        return fechadisponible;
    }

    public void setFechadisponible(String fechadisponible) {
        this.fechadisponible = fechadisponible;
    }

    public String getPorcentajeamortiza() {
        return porcentajeamortiza;
    }

    public void setPorcentajeamortiza(String porcentajeamortiza) {
        this.porcentajeamortiza = porcentajeamortiza;
    }

    public String getPeriodoamortizacion() {
        return periodoamortizacion;
    }

    public void setPeriodoamortizacion(String periodoamortizacion) {
        this.periodoamortizacion = periodoamortizacion;
    }

    public String getPeriodogracia() {
        return periodogracia;
    }

    public void setPeriodogracia(String periodogracia) {
        this.periodogracia = periodogracia;
    }

    public String getNumtelefono() {
        return numtelefono;
    }

    public void setNumtelefono(String numtelefono) {
        this.numtelefono = numtelefono;
    }

    public String getVersionapp() {
        return versionapp;
    }

    public void setVersionapp(String versionapp) {
        this.versionapp = versionapp;
    }
}
