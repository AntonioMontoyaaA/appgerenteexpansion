package expansion.neto.com.mx.gerenteapp.radios.sorted.radios.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.Competencia;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.DetalleRadio;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.GeneradoresRadio;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.MdsVO;
import expansion.neto.com.mx.gerenteapp.radios.modelView.radiosModel.TiendasVO;
import expansion.neto.com.mx.gerenteapp.radios.ui.radios.ActivityDetalleRadios;

import static expansion.neto.com.mx.gerenteapp.radios.ui.radios.ActivityDetalleRadios.NOMBRERADIO;

public class AdapterRadios extends RecyclerView.Adapter<AdapterRadios.ViewHolder>{

    private Context context;
    private List<DetalleRadio> listDetalleRadio;
    //private List<RadioLista> listDetalleRadio;
    private LayoutInflater layoutInflater;

    public AdapterRadios(Context context, List<DetalleRadio> listDetalleRadio){
        this.context = context;
        this.listDetalleRadio = listDetalleRadio;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.item_radios,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DetalleRadio detalleRadio = listDetalleRadio.get(position);

        holder.setData(detalleRadio);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //adapter radios
                ((Activity)context).finish();
                Intent intent = new Intent(context, ActivityDetalleRadios.class);
                intent.putExtra(ActivityDetalleRadios.LATITUD, detalleRadio.getLatitud());
                intent.putExtra(ActivityDetalleRadios.FECHAASIGANDO, detalleRadio.getFechaAsignado());
                intent.putExtra(ActivityDetalleRadios.USUARIO_ASIGNADO, detalleRadio.getUsuarioAsignado());
                intent.putExtra(ActivityDetalleRadios.RADIOID, detalleRadio.getRadioId());
                intent.putExtra(ActivityDetalleRadios.RECHAZADO, detalleRadio.getRechazado());
                intent.putExtra(ActivityDetalleRadios.SIN_SITIO, detalleRadio.getSinSitio());
                intent.putExtra(ActivityDetalleRadios.CANCELADO, detalleRadio.getCancelado());

                List<Competencia> listaCompetencia = detalleRadio.getCompetencia();
                ArrayList<Competencia> arrayListCompetencia = new ArrayList<Competencia>(listaCompetencia);
                Bundle bundleComp = new Bundle( );
                bundleComp.putParcelableArrayList( ActivityDetalleRadios.COMPETENCIA,arrayListCompetencia );
                intent.putExtras( bundleComp );

                List<TiendasVO> listaTiendas = detalleRadio.getTiendas();
                ArrayList<TiendasVO> arrayListTiendas = new ArrayList<TiendasVO>(listaTiendas);
                Bundle bundleTiendas = new Bundle( );
                bundleTiendas.putParcelableArrayList( ActivityDetalleRadios.TIENDAS,arrayListTiendas );
                intent.putExtras( bundleTiendas );

                List<MdsVO> listaMds = detalleRadio.getMds();
                ArrayList<MdsVO> arrayListMds = new ArrayList<MdsVO>(listaMds);
                Bundle bundleMds = new Bundle( );
                bundleMds.putParcelableArrayList( ActivityDetalleRadios.MDS, arrayListMds );
                intent.putExtras( bundleMds );

                intent.putExtra(ActivityDetalleRadios.LONGITUD, detalleRadio.getLongitud());
                intent.putExtra(ActivityDetalleRadios.STATUSID, detalleRadio.getEstatusRadioId());
                intent.putExtra(ActivityDetalleRadios.fcTotalCompetencia, detalleRadio.getDatosRadio().getFcTotalCompetencia());
                intent.putExtra(ActivityDetalleRadios.fcPobFlotante, detalleRadio.getDatosRadio().getFcPobFlotante());
                intent.putExtra(ActivityDetalleRadios.fcPobTotal, detalleRadio.getDatosRadio().getFcPobTotal());
                intent.putExtra(ActivityDetalleRadios.fcTotalGeneradores, detalleRadio.getDatosRadio().getFcTotalGeneradores());
                intent.putExtra(ActivityDetalleRadios.fcMas60, detalleRadio.getDatosRadio().getFcMas60());
                intent.putExtra(ActivityDetalleRadios.fcAmaDeCasa, detalleRadio.getDatosRadio().getFcAmaDeCasa());
                intent.putExtra(ActivityDetalleRadios.fcTrabajoPorCuenta, detalleRadio.getDatosRadio().getFcTrabajoPorCuenta());
                intent.putExtra(ActivityDetalleRadios.fcPea, detalleRadio.getDatosRadio().getFcPea());
                intent.putExtra(ActivityDetalleRadios.fcTemplos, detalleRadio.getDatosRadio().getFcTemplos());
                intent.putExtra(ActivityDetalleRadios.fcJubilado, detalleRadio.getDatosRadio().getFcJubilado());
                intent.putExtra(ActivityDetalleRadios.fcDesempleado, detalleRadio.getDatosRadio().getFcDesempleado());
                intent.putExtra(ActivityDetalleRadios.fcOficinasGobierno, detalleRadio.getDatosRadio().getFcOficinasGobierno());
                intent.putExtra(ActivityDetalleRadios.fcUrl, detalleRadio.getDatosRadio().getFcUrl());
                intent.putExtra(ActivityDetalleRadios.fcMunicipio, detalleRadio.getDatosRadio().getFcMunicipio());
                intent.putExtra(ActivityDetalleRadios.fcBAE, detalleRadio.getDatosRadio().getFcBAE());
                intent.putExtra(ActivityDetalleRadios.fcMercados, detalleRadio.getDatosRadio().getFcMercados());
                intent.putExtra(ActivityDetalleRadios.fcHospitales, detalleRadio.getDatosRadio().getFcHospitales());
                intent.putExtra(ActivityDetalleRadios.fcMenos26, detalleRadio.getDatosRadio().getFcMenos26());
                intent.putExtra(ActivityDetalleRadios.fcOtraOcupacion, detalleRadio.getDatosRadio().getFcOtraOcupacion());
                intent.putExtra(ActivityDetalleRadios.fcServidorPublico, detalleRadio.getDatosRadio().getFcServidorPublico());
                intent.putExtra(ActivityDetalleRadios.fcEstudiante, detalleRadio.getDatosRadio().getFcEstudiante());
                intent.putExtra(ActivityDetalleRadios.fcDe31a40, detalleRadio.getDatosRadio().getFcDe31a40());
                intent.putExtra(ActivityDetalleRadios.fcVentaArticulos, detalleRadio.getDatosRadio().getFcVentaArticulos());
                intent.putExtra(ActivityDetalleRadios.fcServicios, detalleRadio.getDatosRadio().getFcServicios());
                intent.putExtra(ActivityDetalleRadios.fcTotalUE, detalleRadio.getDatosRadio().getFcTotalUE());
                intent.putExtra(ActivityDetalleRadios.fcDe51a60, detalleRadio.getDatosRadio().getFcDe51a60());
                intent.putExtra(ActivityDetalleRadios.fcJornalero, detalleRadio.getDatosRadio().getFcJornalero());
                intent.putExtra(ActivityDetalleRadios.fcMIBA, detalleRadio.getDatosRadio().getFcMIBA());
                intent.putExtra(ActivityDetalleRadios.fcEmpresario, detalleRadio.getDatosRadio().getFcEmpresario());
                intent.putExtra(ActivityDetalleRadios.fcCallePrincipal, detalleRadio.getDatosRadio().getFcCallePrincipal());
                intent.putExtra(ActivityDetalleRadios.fcObrero, detalleRadio.getDatosRadio().getFcObrero());
                intent.putExtra(ActivityDetalleRadios.fcEmpleado, detalleRadio.getDatosRadio().getFcEmpleado());
                intent.putExtra(ActivityDetalleRadios.fcDe41a50, detalleRadio.getDatosRadio().getFcDe41a50());
                intent.putExtra(ActivityDetalleRadios.fcProfesionista, detalleRadio.getDatosRadio().getFcProfesionista());
                intent.putExtra(ActivityDetalleRadios.fcTotalHogares, detalleRadio.getDatosRadio().getFcTotalHogares());
                intent.putExtra(ActivityDetalleRadios.fcBBB, detalleRadio.getDatosRadio().getFcBBB());
                intent.putExtra(ActivityDetalleRadios.fcDe26a30, detalleRadio.getDatosRadio().getFcDe26a30());
                intent.putExtra(ActivityDetalleRadios.fcCalle2, detalleRadio.getDatosRadio().getFcCalle2());
                intent.putExtra(ActivityDetalleRadios.fcVentaAlimentos, detalleRadio.getDatosRadio().getFcVentaAlimentos());
                intent.putExtra(ActivityDetalleRadios.fcCalle1, detalleRadio.getDatosRadio().getFcCalle1());
                intent.putExtra(ActivityDetalleRadios.fcEscuelas, detalleRadio.getDatosRadio().getFcEscuelas());
                intent.putExtra(ActivityDetalleRadios.fcColonia, detalleRadio.getDatosRadio().getFcColonia());
                intent.putExtra(ActivityDetalleRadios.fcEjidatario, detalleRadio.getDatosRadio().getFcEjidatario());
                intent.putExtra(ActivityDetalleRadios.VISITAS_RADIO, detalleRadio.getVisitasRadio());

                List<GeneradoresRadio> listaGeneradores = detalleRadio.getGeneradores();
                ArrayList<GeneradoresRadio> arrayListGeneradores = new ArrayList<GeneradoresRadio>(listaGeneradores);
                Bundle bundle = new Bundle( );
                bundle.putParcelableArrayList( ActivityDetalleRadios.GENERADORES_RADIOS,arrayListGeneradores );
                intent.putExtras( bundle );

                intent.putExtra(ActivityDetalleRadios.STATUS, detalleRadio.getEstatusRadio());
                intent.putExtra(ActivityDetalleRadios.LONG_RADIO, detalleRadio.getLongRadio());
                intent.putExtra(NOMBRERADIO, detalleRadio.getNombreRadio());


                view.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return listDetalleRadio.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvNombreRadio, tvFechaAsignado, tvVecesCancelado,tvVecesSinSitios,tvStatus,tvNombreAsignado;
        public ViewHolder(View itemView) {
            super(itemView);
            tvNombreRadio = itemView.findViewById(R.id.tvNombreRadio);
            tvFechaAsignado = itemView.findViewById(R.id.tvFechaAsignado);
            tvVecesCancelado = itemView.findViewById(R.id.tv_veces_cancelado);
            tvVecesSinSitios = itemView.findViewById(R.id.tv_veces_sin_sitios);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvNombreAsignado = itemView.findViewById(R.id.tv_nombre_asignado);
        }

        public void setData (DetalleRadio detalleradio){
            DetalleRadio radios1 = detalleradio;
            tvNombreRadio.setText(radios1.getNombreRadio());
            tvFechaAsignado.setText(radios1.getFechaAsignado());
            tvVecesCancelado.setText( radios1.getCancelado() );
            tvVecesSinSitios.setText( radios1.getSinSitio() );
            tvNombreAsignado.setText( radios1.getUsuarioAsignado() );
            if (radios1.getRechazado().equals( "2" )||radios1.getRechazado().equals( "4" )){
                tvStatus.setText("En proceso de Autorización");
            }else {
                tvStatus.setText(radios1.getEstatusRadio());
            }
        }
    }
}
