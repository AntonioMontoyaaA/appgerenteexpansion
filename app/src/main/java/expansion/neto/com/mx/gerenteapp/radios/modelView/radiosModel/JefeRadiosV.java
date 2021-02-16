package expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JefeRadiosV {

    @SerializedName("codigo")
    @Expose
    private Integer codigo;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("usuarios")
    @Expose
    private List<UsuarioVO> usuarios;

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

    public List<UsuarioVO> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioVO> usuarios) {
        this.usuarios = usuarios;
    }

    public class UsuarioVO {
        @SerializedName("usuarioId")
        @Expose
        private Integer usuarioId;
        @SerializedName("usuario")
        @Expose
        private String usuario;

        public Integer getUsuarioId() {
            return usuarioId;
        }

        public void setUsuarioId(Integer usuarioId) {
            this.usuarioId = usuarioId;
        }

        public String getUsuario() {
            return usuario;
        }

        public void setUsuario(String usuario) {
            this.usuario = usuario;
        }
    }
}
