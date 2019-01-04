package expansion.neto.com.mx.gerenteapp.constantes;

/**
 * Clase contiene constantes de las API que consumira la aplicación
 * Created by marcosmarroquin on 21/03/18.
 */
public class RestUrl {

    public static String dominioFileDes = "voksedesfiles"; // desarrollo

    //public static String dominioFileProd = "voksefiles"; // produccion

    //358781
    //289106
    public static String ip = "206.189.68.177"; //desarrollo
    //public static String ip = "167.99.102.173"; // QA
    //public static String ip = "206.189.223.154"; // produccion
    
    //cuando se finalice aparesca progress dialog de finalizado, cuando damos aceptar menu documentos

    public static String dominio = "voksedesapi"; // desarrollo
    //public static String dominio = "vokseqaapi"; // QA
    //public static String dominio = "vokseapi"; // produccion

    public static String VERSION_APP = "1.2.3";

    public static String NUM_TELEFONO = "0";

    public static String REST_ACTION_CONSULTAR_PREDIAL = "http://"+ ip +"/"+ dominio +"/superficiepreconsulta/";

    public static String REST_ACTION_CONSULTA_FINALIZA_MD = "http://"+ ip +"/"+ dominio +"/consultafinalizamd/";

    public static String REST_ACTION_CREAR_CONTEO = "http://"+ ip +"/"+ dominio +"/conteopeatonal/";

    public static String TIPO_SERVICIO = "1";

    public static String TIPO_SERVICIO_DOC = "9";

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

    public static String FORMATO_FOTO = "png";

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

    public static String REST_ACTION_GUARDAR_UBICACION = "http://"+ ip +"/"+ dominio +"/guardaubicacionact/";

    public static String REST_ACTION_CONSULTAR_DOCUMENTOS = "http://"+ ip +"/"+ dominio +"/obtienelistadoctos/";

    public static String REST_ACTION_GUARDAR_DOCUMENTOS = "http://"+ ip +"/"+ dominio +"/guardadocsmontos/";

    public static String REST_ACTION_OBTENER_FACTORES_CONSTRUCCION = "http://"+ ip +"/"+ dominio +"/consultacatfactores/";

    public static String FACTOR_ID = "5";

    public static String REST_ACTION_CONSULTAR_DATOS_COMPETENCIA_GENERADORES = "http://"+ ip +"/"+ dominio +"/obtienecomgen/";

    public static String REST_CONSULTA_TOTALES = "http://"+ ip +"/"+ dominio +"/consultamdstotales/";

    public static String STATUS_TOTALES = "1";

    public static String ID_GEXPANSION = "4";

    public static String ID_EXPANSION = "1";

    public static String ID_GESTORIA = "2";

    public static String ID_CONSTRUCCION = "3";

    public static String ID_AUDITORIA = "4";

    public static String ID_FINANZAS = "6";

    public static String ID_OPERACIONES = "5";

    public static String ANIO_ACTUAL = "2018";

    public static String TIPOSERVICIO = "11";

    public static String REST_CONSULTA_MONTO = "http://"+ ip +"/"+ dominio +"/guardadocsmontos/";

    public static String REST_ACTION_GUARDA_TODO_TIPO_DOCUMENTO = "http://"+ ip +"/"+ dominioFileDes +"/cloudinaryset/";

    public static String REST_ACTION_NUM_MENSAJES = "http://"+ ip +"/"+ dominio +"/obtienenummensajes/";

    public static String REST_ACTION_CONSULTAR_CHAT_ESTATUS = "http://"+ ip +"/"+ dominio +"/obtienemensajes/";

    public static String REST_ACTION_GUARDAR_CHAT_EN_STATUS = "http://"+ ip +"/"+ dominio +"/agregamensajenivelestatus/";

    public static String REST_ACTION_GUARDAR_VALIDACION = "http://"+ ip +"/"+ dominio +"/validacionmensajes/";

}

