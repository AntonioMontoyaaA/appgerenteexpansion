package expansion.neto.com.mx.gerenteapp.constantes;

/**
 * Clase contiene constantes de las API que consumira la aplicación
 * Created by marcosmarroquin on 21/03/18.
 */
public class RestUrl {
    //358781
    //289106
    //public static String ip = "206.189.68.177"; // desarrollo
    public static String ip = "167.99.102.173"; // QA
    //public static String ip = "206.189.223.154"; // produccion

    //public static String dominio = "voksedesapi"; // desarrollo
    public static String dominio = "vokseqaapi"; // QA
    //public static String dominio = "vokseapi"; // produccion

    public static String REST_ACTION_CONSULTA_FINALIZA_MD = "http://"+ ip +"/"+ dominio +"/consultafinalizamd/";

    public static String REST_ACTION_CREAR_CONTEO = "http://"+ ip +"/"+ dominio +"/conteopeatonal/";

    public static String TIPO_SERVICIO = "1";

    public static String TIPO_NOTIFICACION = "3";

    public static String REST_ACTION_CONSULTAR_AGENDA = "http://"+ ip +"/"+ dominio +"/agenda";

    public static String REST_ACTION_CONSULTAR_NOTIFICACIONES = "http://"+ ip +"/"+ dominio +"/notificaciones";

    public static String REST_ACTION_CONSULTAR_LOGIN = "http://"+ ip +"/"+ dominio +"/login/";

    public static String REST_ACTION_CONSULTAR_AUTORIZA = "https://api.myjson.com/bins/c8r9z";

    public static String REST_ACTION_CONSULTAR_DATOS_SITIO = "http://"+ ip +"/"+ dominio +"/consultaDatosSitio/";

    public static String REST_ACTION_CONSULTAR_DATOS_PROPIETARIO = "http://"+ ip +"/"+ dominio +"/consultaPropietario/";

    public static String REST_ACTION_CONSULTAR_DATOS_SUPERFICIE = "http://"+ ip +"/"+ dominio +"/consultasuperficie/";

    public static String REST_ACTION_CONSULTAR_DATOS_CONSTRUCCION = "http://"+ ip +"/"+ dominio +"/consultaconstruccion/";

    public static String REST_ACTION_CONSULTAR_DATOS_ZONIFICACION = "http://"+ ip +"/"+ dominio +"/consultazonificacion/";

    public static String REST_ACTION_CONSULTAR_DATOS_GENERALIDADES_SITIO = "http://"+ ip +"/"+ dominio +"/consultageneralidades/";

    public static String REST_ACTION_CONSULTAR_CONTEO_PEATONAL = "http://"+ ip +"/"+ dominio +"/consultaConteoPeatonal/";

    public static String REST_ACTION_CONSULTAR_DASHBOARD = "http://"+ ip +"/"+ dominio +"/consultamdxestatus/";

    public static String REST_ACTION_CONSULTAR_AUTORIZADAS_LISTA = "http://"+ ip +"/"+ dominio +"/consultamdxestatus/";

    public static String REST_ACTION_CONSULTA_HORAS_PEATONAL = "http://"+ ip +"/"+ dominio +"/obtienedetallefactor/";

    public static String REST_ACTION_CONSULTA_MOTIVOS_RECHAZO = "http://"+ ip +"/"+ dominio +"/catalogomotivosrechazo/";

    public static String REST_ACTION_AUTORIZA_MODULO = "http://"+ ip +"/"+ dominio +"/validacionmodulos/";

    public static String REST_ACTION_AUTORIZA_FINAL = "http://"+ ip +"/"+ dominio +"/finalizavalidacion/";

    public static String REST_ACTION_CONSULTAR_TIEMPOS_EN_PROCESO = "http://"+ ip +"/"+ dominio +"/consultatiemposxmd/";

    public static String REST_ACTION_CONSULTAR_CHAT_EN_PROCESO = "http://"+ ip +"/"+ dominio +"/consultachat/";

    public static String REST_ACTION_GUARDAR_CHAT_EN_PROCESO = "http://"+ ip +"/"+ dominio +"/agregacomentario/";

    public static String REST_ACTION_TIPS = "http://"+ ip +"/"+ dominio +"/consultaTips/";

    public static String TIPO_APLICACION = "1";

    public static String REST_ACTION_CONSULTAR_RECHAZADAS_LISTA = "http://"+ ip +"/"+ dominio +"/consultamdxestatus/";

    public static String REST_ACTION_CONSULTAR_DATOS_AUTORIZADAS = "http://"+ ip +"/"+ dominio +"/consultamdsautorizadas/";

    public static String REST_ACTION_CONSULTAR_EVENTOS = "http://"+ ip +"/"+ dominio +"/obtieneeventos";

    public static String REST_ACTION_CONSULTAR_EMPLEADOS_AGENDA = "http://"+ ip +"/"+ dominio +"/obtieneEmpleados";

    public static String REST_ACTION_CREAR_EVENTO = "http://"+ ip +"/"+ dominio +"/agendaguardaevento/";

    public static String REST_ACTION_GUARDAR_NOTIFICACION = "http://"+ ip +"/"+ dominio +"/validaNotificacion/";

}

