package expansion.neto.com.mx.gerenteapp.modelView.loginModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Permiso {

    @SerializedName("BLOQUEASEGUIMIENTO")
    @Expose
    private int bloqueaseguimiento;

    @SerializedName("FIESTATUS")
    @Expose
    private int fiestatus;

    @SerializedName("FDFECHAREGISTRO")
    @Expose
    private String fdfecharegistro;

    @SerializedName("FIMODULOID")
    @Expose
    private int fimoduloid;

    @SerializedName("PERMITEEDITAR")
    @Expose
    private int permiteeditar;

    @SerializedName("FIUSUARIOREGISTRA")
    @Expose
    private int fiusuarioregistra;

    @SerializedName("FISUBMODULO")
    @Expose
    private int fisubmodulo;

    @SerializedName("PERMITECOMENTAR")
    @Expose
    private int permitecomentar;

    @SerializedName("PERMITERECHAZAR")
    @Expose
    private int permiterechazar;

    @SerializedName("PERMITEAUTORIZAR")
    @Expose
    private int permiteautorizar;

    public int getBloqueaseguimiento() {
        return bloqueaseguimiento;
    }

    public void setBloqueaseguimiento(int bloqueaseguimiento) {
        this.bloqueaseguimiento = bloqueaseguimiento;
    }

    public int getFiestatus() {
        return fiestatus;
    }

    public void setFiestatus(int fiestatus) {
        this.fiestatus = fiestatus;
    }

    public String getFdfecharegistro() {
        return fdfecharegistro;
    }

    public void setFdfecharegistro(String fdfecharegistro) {
        this.fdfecharegistro = fdfecharegistro;
    }

    public int getFimoduloid() {
        return fimoduloid;
    }

    public void setFimoduloid(int fimoduloid) {
        this.fimoduloid = fimoduloid;
    }

    public int getPermiteeditar() {
        return permiteeditar;
    }

    public void setPermiteeditar(int permiteeditar) {
        this.permiteeditar = permiteeditar;
    }

    public int getFiusuarioregistra() {
        return fiusuarioregistra;
    }

    public void setFiusuarioregistra(int fiusuarioregistra) {
        this.fiusuarioregistra = fiusuarioregistra;
    }

    public int getFisubmodulo() {
        return fisubmodulo;
    }

    public void setFisubmodulo(int fisubmodulo) {
        this.fisubmodulo = fisubmodulo;
    }

    public int getPermitecomentar() {
        return permitecomentar;
    }

    public void setPermitecomentar(int permitecomentar) {
        this.permitecomentar = permitecomentar;
    }

    public int getPermiterechazar() {
        return permiterechazar;
    }

    public void setPermiterechazar(int permiterechazar) {
        this.permiterechazar = permiterechazar;
    }

    public int getPermiteautorizar() {
        return permiteautorizar;
    }

    public void setPermiteautorizar(int permiteautorizar) {
        this.permiteautorizar = permiteautorizar;
    }
}