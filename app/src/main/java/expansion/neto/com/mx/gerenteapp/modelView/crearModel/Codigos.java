package expansion.neto.com.mx.gerenteapp.modelView.crearModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Codigos {

    @SerializedName("mdId")
    @Expose
    private long mdId;
    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("resultado")
    @Expose
    private Resultado resultado;

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
    }

    public long getMdId() {
        return mdId;
    }

    public void setMdId(long mdId) {
        this.mdId = mdId;
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


    public class Resultado {
        @SerializedName("secure_url")
        @Expose
        private String secureUrl;

        public String getSecureUrl() {
            return secureUrl;
        }

        public void setSecureUrl(String secureUrl) {
            this.secureUrl = secureUrl;
        }
    }

}
