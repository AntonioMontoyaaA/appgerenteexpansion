package expansion.neto.com.mx.gerenteapp.ui.documentos;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityDocumentosBinding;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentAutoriza.FragmentDialogFinalizar;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentDashboard.FragmentDialogCancelarMdRechazadas;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentDashboard.FragmentDialogTerminaDocumentacion;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentDocumentos.FragmentInicioDocumentos;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.AutorizaResponse;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.gerenteapp.modelView.documentosModel.Doctos;
import expansion.neto.com.mx.gerenteapp.modelView.documentosModel.Documentos;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderAutorizaFinal;
import expansion.neto.com.mx.gerenteapp.provider.documentosProvider.ProviderDocumentos;
import expansion.neto.com.mx.gerenteapp.provider.documentosProvider.ProviderGuardarDocumentos;
import expansion.neto.com.mx.gerenteapp.provider.loginProvider.ProviderObtenerUrl;
import expansion.neto.com.mx.gerenteapp.ui.dashboard.ActivityMain;
import expansion.neto.com.mx.gerenteapp.utils.Util;

import static java.security.AccessController.getContext;

public class ActivityDocumentos extends AppCompatActivity {

    private ActivityDocumentosBinding binding;
    String usuario;
    String md;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        initDataBinding();

        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        usuario = preferences.getString("usuario", "");
        md = preferences.getString("mdId", "");

        progressDialog = new ProgressDialog(ActivityDocumentos.this, R.style.AppCompatAlertDialogStyle);

        getDatos();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        binding.btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentDialogCancelarMdRechazadas dFragment = new FragmentDialogCancelarMdRechazadas();
                dFragment.show(fm, "Dialog Fragment");
            }
        });

        final Boolean[] permiso = {false};

        binding.guardar.setVisibility(View.VISIBLE);

        binding.guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                permiso[0] = getObligatorios();

                if (permiso[0]) {

                    String json = new Gson().toJson(documentacion);
                    json = "[" + json + "]";
                    loadingProgress(progressDialog, 0);

                    ProviderGuardarDocumentos.getInstance(ActivityDocumentos.this).guardarDocumentos(
                            usuario, md, json, getFechaHora(), new ProviderGuardarDocumentos.InterfaceGuardarDocumentos() {
                                @Override
                                public void resolve(Codigos codigo) {
                                    if (codigo != null) {
                                        loadingProgress(progressDialog, 1);
                                        Toast.makeText(getApplicationContext(), codigo.getMensaje() + " Fotos guardados correctamente",
                                                Toast.LENGTH_LONG).show();
                                    } else {
                                        loadingProgress(progressDialog, 1);
                                        Toast.makeText(getApplicationContext(), getString(R.string.faltan),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void reject(Exception e) {
                                    loadingProgress(progressDialog, 1);
                                    Toast.makeText(getApplicationContext(), getString(R.string.faltan),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                    );
                } else {
                    Toast.makeText(getApplicationContext(), R.string.faltan,
                            Toast.LENGTH_LONG).show();
                    binding.btnFinalizar.setEnabled(false);
                    binding.btnFinalizar.setAlpha(.4f);
                }

            }

        });

        binding.btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Boolean[] permiso = {false};
                permiso[0] = getObligatorios();
                if (permiso[0]) {
                    String json = new Gson().toJson(documentacion);
                    json = "[" + json + "]";
                    loadingProgressDocumentos(progressDialog, 0, "");

                    ProviderGuardarDocumentos.getInstance(ActivityDocumentos.this).guardarDocumentos(
                            usuario, md, json, getFechaHora(), new ProviderGuardarDocumentos.InterfaceGuardarDocumentos() {
                                @Override
                                public void resolve(Codigos codigo) {
                                    if (codigo != null) {

                                        loadingProgressDocumentos(progressDialog, 1, "");
                                        finalizaDocumentacion();

                                    } else {

                                        loadingProgressDocumentos(progressDialog, 1, codigo.getMensaje() + "");
                                        Toast.makeText(getApplicationContext(), getString(R.string.faltan),
                                                Toast.LENGTH_LONG).show();

                                    }
                                }

                                @Override
                                public void reject(Exception e) {
                                    loadingProgressDocumentos(progressDialog, 1, "");
                                    Toast.makeText(getApplicationContext(), getString(R.string.faltan),
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                    );
                } else {
                    Toast.makeText(getApplicationContext(), R.string.faltan,
                            Toast.LENGTH_LONG).show();
                }

            }

        });
    }

    final Boolean[] permisos = {false};

    public Boolean getObligatorios() {
        for (int i = 0; i < docto.getDatos().size(); i++) {
            if (docto.getDatos().get(i).getOpcional().equals("0")) {

                if (!documentacion.getFcurl1().isEmpty() &&
                        !documentacion.getFcurl2().isEmpty() &&
                        !documentacion.getFcurl3().isEmpty() &&
                        !documentacion.getFcurl4().isEmpty() &&
                        !documentacion.getFcurl7().isEmpty() &&
                        !documentacion.getFcurl8().isEmpty() &&
                        !documentacion.getFcurl9().isEmpty()) {
                    binding.btnFinalizar.setEnabled(true);
                    binding.btnFinalizar.setAlpha(1);
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Método que setea la vista con el binding
     */
    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_documentos);
    }

    Doctos documentacion;

    ArrayList<Doctos.Fcurl1> fcurl1s = new ArrayList<>();
    ArrayList<Doctos.Fcurl2> fcurl2s = new ArrayList<>();
    ArrayList<Doctos.Fcurl3> fcurl3s = new ArrayList<>();
    ArrayList<Doctos.Fcurl4> fcurl4s = new ArrayList<>();
    ArrayList<Doctos.Fcurl5> fcurl5s = new ArrayList<>();
    ArrayList<Doctos.Fcurl6> fcurl6s = new ArrayList<>();
    ArrayList<Doctos.Fcurl7> fcurl7s = new ArrayList<>();
    ArrayList<Doctos.Fcurl8> fcurl8s = new ArrayList<>();
    ArrayList<Doctos.Fcurl9> fcurl9s = new ArrayList<>();
    ArrayList<Doctos.Fcurl10> fcurl10s = new ArrayList<>();
    ArrayList<Doctos.Fcurl11> fcurl11s = new ArrayList<>();
    ArrayList<Doctos.Fcurl12> fcurl12s = new ArrayList<>();
    ArrayList<Doctos.Fcurl13> fcurl13s = new ArrayList<>();

    Doctos.Fcurl1 fcurl1;
    Doctos.Fcurl2 fcurl2;
    Doctos.Fcurl3 fcurl3;
    Doctos.Fcurl4 fcurl4;
    Doctos.Fcurl5 fcurl5;
    Doctos.Fcurl6 fcurl6;
    Doctos.Fcurl7 fcurl7;
    Doctos.Fcurl8 fcurl8;
    Doctos.Fcurl9 fcurl9;
    Doctos.Fcurl10 fcurl10;
    Doctos.Fcurl11 fcurl11;
    Doctos.Fcurl12 fcurl12;
    Doctos.Fcurl13 fcurl13;
    Documentos docto;


    public void finalizaDocumentacion() {
        ProviderAutorizaFinal.getInstance(ActivityDocumentos.this).autorizaFinal(md, usuario,
                1, 0, "", new ProviderAutorizaFinal.AutorizaFinal() {
                    @Override
                    public void resolve(AutorizaResponse autorizaResponse) {
                        if (autorizaResponse.getCodigo() == 200) {

                            loadingProgressDocumentos(progressDialog, 1, "Esta MD se autorizo correctamente");
                            FragmentManager fm = getSupportFragmentManager();
                            FragmentDialogTerminaDocumentacion dFragment = new FragmentDialogTerminaDocumentacion();
                            dFragment.show(fm, "Dialog Fragment");


                        } else {

                            loadingProgressDocumentos(progressDialog, 1, autorizaResponse.getMensaje() + "");

                        }
                    }

                    @Override
                    public void reject(Exception e) {
                        loadingProgressDocumentos(progressDialog, 1, "Error al conectarse al servicio que autoriza/rechaza la pantalla: ");
                    }
                });


    }

    public void getDatos() {
        loadingProgress(progressDialog, 0);
        ProviderDocumentos.getInstance(this).obtieneDocumentos(usuario, md, new ProviderDocumentos.ConsultaDocumentos() {
            @Override
            public void resolve(final Documentos documentos) {
                if (documentos != null) {
                    if (documentos.getCodigo() == 200) {
                        loadingProgress(progressDialog, 1);
                        docto = documentos;
                        documentacion = new Doctos();

                        final Resources resource = ActivityDocumentos.this.getResources();
                        TableLayout plomoTable = (TableLayout) findViewById(R.id.plomosTable);
                        plomoTable.removeAllViews();
                        TableRow rowPlomo = new TableRow(ActivityDocumentos.this);
                        rowPlomo.setBackgroundColor(resource.getColor(R.color.blanco));
                        int paddingDp = 2;

                        float density = getResources().getDisplayMetrics().density;
                        int paddingPixel = (int) (paddingDp * density);

                        plomoTable.addView(rowPlomo);

                        Collections.sort(documentos.getDatos(), new Comparator<Documentos.Dato>() {
                            @Override
                            public int compare(Documentos.Dato lhs, Documentos.Dato rhs) {
                                return lhs.getOpcional().compareTo(rhs.getOpcional());
                            }
                        });


                        for (int i = 0; i < documentos.getDatos().size(); i++) {

                            final TextView cantidad = new TextView(ActivityDocumentos.this);

                            cantidad.setText("CANTIDAD");

                            cantidad.setTextSize(9);
                            cantidad.setTextColor(resource.getColor(R.color.grisetxt));
                            cantidad.setPadding(20, paddingPixel, 0, 0);
                            cantidad.setGravity(Gravity.START);
                            cantidad.setLayoutParams(new TableRow.LayoutParams(6,
                                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0));


                            switch (documentos.getDatos().get(i).getDocumentoId()) {
                                case "1":

                                    fcurl1s = new ArrayList<>();

                                    if (documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                        for (int j = 0; j < documentos.getDatos().get(i).getDocumentos().size(); j++) {
                                            if (documentos.getDatos().get(i).getDocumentos().size() > 0) {

                                                fcurl1 = new Doctos.Fcurl1(documentos.getDatos().get(i).getDocumentos().get(j).getUrl(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getFecha(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getNombreArchivo()
                                                );
                                                fcurl1s.add(fcurl1);
                                                documentacion.setFcurl1(fcurl1s);
                                                cantidad.setVisibility(View.VISIBLE);

                                            } else {
                                                // cantidad.setVisibility(View.GONE);
                                                fcurl1s.add(fcurl1);
                                                fcurl1s.clear();
                                                documentacion.setFcurl1(fcurl1s);
                                            }
                                        }
                                    } else {
                                        //  cantidad.setVisibility(View.GONE);
                                        fcurl1s.add(fcurl1);
                                        fcurl1s.clear();
                                        documentacion.setFcurl1(fcurl1s);
                                    }


                                    documentacion.setFcurl1Id(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));
                                    documentacion.setNombreCurl1(documentos.getDatos().get(i).getDescripcion() + "");

                                    break;
                                case "2":

                                    fcurl2s = new ArrayList<>();

                                    if (documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                        for (int j = 0; j < documentos.getDatos().get(i).getDocumentos().size(); j++) {
                                            if (documentos.getDatos().get(i).getDocumentos().size() > 0) {

                                                fcurl2 = new Doctos.Fcurl2(documentos.getDatos().get(i).getDocumentos().get(j).getUrl(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getFecha(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getNombreArchivo()
                                                );
                                                fcurl2s.add(fcurl2);
                                                documentacion.setFcurl2(fcurl2s);
                                                cantidad.setVisibility(View.VISIBLE);
                                            } else {
                                                //  cantidad.setVisibility(View.GONE);
                                                fcurl2s.add(fcurl2);
                                                fcurl2s.clear();
                                                documentacion.setFcurl2(fcurl2s);
                                            }
                                        }
                                    } else {
                                        //  cantidad.setVisibility(View.GONE);
                                        fcurl2s.add(fcurl2);
                                        fcurl2s.clear();
                                        documentacion.setFcurl2(fcurl2s);
                                    }


                                    documentacion.setFcurl2Id(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));
                                    documentacion.setNombreCurl2(documentos.getDatos().get(i).getDescripcion() + "");


                                    break;
                                case "3":

                                    fcurl3s = new ArrayList<>();

                                    if (documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                        for (int j = 0; j < documentos.getDatos().get(i).getDocumentos().size(); j++) {
                                            if (documentos.getDatos().get(i).getDocumentos().size() > 0) {

                                                fcurl3 = new Doctos.Fcurl3(documentos.getDatos().get(i).getDocumentos().get(j).getUrl(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getFecha(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getNombreArchivo()
                                                );
                                                fcurl3s.add(fcurl3);
                                                documentacion.setFcurl3(fcurl3s);
                                                cantidad.setVisibility(View.VISIBLE);

                                            } else {
                                                //     cantidad.setVisibility(View.GONE);

                                                fcurl3s.add(fcurl3);
                                                fcurl3s.clear();
                                                documentacion.setFcurl3(fcurl3s);
                                            }
                                        }
                                    } else {
                                        // cantidad.setVisibility(View.GONE);

                                        fcurl3s.add(fcurl3);
                                        fcurl3s.clear();
                                        documentacion.setFcurl3(fcurl3s);
                                    }


                                    documentacion.setFcurl3Id(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));
                                    documentacion.setNombreCurl3(documentos.getDatos().get(i).getDescripcion() + "");

                                    break;
                                case "4":

                                    fcurl4s = new ArrayList<>();

                                    if (documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                        for (int j = 0; j < documentos.getDatos().get(i).getDocumentos().size(); j++) {
                                            if (documentos.getDatos().get(i).getDocumentos().size() > 0) {

                                                fcurl4 = new Doctos.Fcurl4(documentos.getDatos().get(i).getDocumentos().get(j).getUrl(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getFecha(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getNombreArchivo()
                                                );
                                                fcurl4s.add(fcurl4);
                                                documentacion.setFcurl4(fcurl4s);
                                                cantidad.setVisibility(View.VISIBLE);

                                            } else {
                                                //    cantidad.setVisibility(View.GONE);
                                                fcurl4s.add(fcurl4);
                                                fcurl4s.clear();
                                                documentacion.setFcurl4(fcurl4s);
                                            }
                                        }
                                    } else {
                                        //   cantidad.setVisibility(View.GONE);
                                        fcurl4s.add(fcurl4);
                                        fcurl4s.clear();
                                        documentacion.setFcurl4(fcurl4s);
                                    }


                                    documentacion.setFcurl4Id(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));
                                    documentacion.setNombreCurl4(documentos.getDatos().get(i).getDescripcion() + "");

                                    break;
                                case "5":

                                    fcurl5s = new ArrayList<>();

                                    if (documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                        for (int j = 0; j < documentos.getDatos().get(i).getDocumentos().size(); j++) {
                                            if (documentos.getDatos().get(i).getDocumentos().size() > 0) {

                                                fcurl5 = new Doctos.Fcurl5(documentos.getDatos().get(i).getDocumentos().get(j).getUrl(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getFecha(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getNombreArchivo()
                                                );
                                                fcurl5s.add(fcurl5);
                                                documentacion.setFcurl5(fcurl5s);
                                                cantidad.setVisibility(View.VISIBLE);

                                            } else {
                                                //    cantidad.setVisibility(View.GONE);
                                                fcurl5s.add(fcurl5);
                                                fcurl5s.clear();
                                                documentacion.setFcurl5(fcurl5s);
                                            }
                                        }
                                    } else {
                                        //   cantidad.setVisibility(View.GONE);
                                        fcurl5s.add(fcurl5);
                                        fcurl5s.clear();
                                        documentacion.setFcurl5(fcurl5s);
                                    }
                                    documentacion.setFcurl5Id(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));
                                    documentacion.setNombreCurl5(documentos.getDatos().get(i).getDescripcion() + "");
                                    break;
                                case "6":

                                    fcurl6s = new ArrayList<>();

                                    if (documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                        for (int j = 0; j < documentos.getDatos().get(i).getDocumentos().size(); j++) {
                                            if (documentos.getDatos().get(i).getDocumentos().size() > 0) {

                                                fcurl6 = new Doctos.Fcurl6(documentos.getDatos().get(i).getDocumentos().get(j).getUrl(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getFecha(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getNombreArchivo()
                                                );
                                                fcurl6s.add(fcurl6);
                                                documentacion.setFcurl6(fcurl6s);
                                                cantidad.setVisibility(View.VISIBLE);
                                            } else {
                                                //   cantidad.setVisibility(View.GONE);
                                                fcurl6s.add(fcurl6);
                                                fcurl6s.clear();
                                                documentacion.setFcurl6(fcurl6s);
                                            }
                                        }
                                    } else {
                                        //  cantidad.setVisibility(View.GONE);
                                        fcurl6s.add(fcurl6);
                                        fcurl6s.clear();
                                        documentacion.setFcurl6(fcurl6s);
                                    }


                                    documentacion.setFcurl6Id(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));
                                    documentacion.setNombreCurl6(documentos.getDatos().get(i).getDescripcion() + "");
                                    break;
                                case "7":


                                    fcurl7s = new ArrayList<>();

                                    if (documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                        for (int j = 0; j < documentos.getDatos().get(i).getDocumentos().size(); j++) {
                                            if (documentos.getDatos().get(i).getDocumentos().size() > 0) {

                                                fcurl7 = new Doctos.Fcurl7(documentos.getDatos().get(i).getDocumentos().get(j).getUrl(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getFecha(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getNombreArchivo()
                                                );
                                                fcurl7s.add(fcurl7);
                                                documentacion.setFcurl7(fcurl7s);
                                                cantidad.setVisibility(View.VISIBLE);

                                            } else {
                                                //  cantidad.setVisibility(View.GONE);

                                                fcurl7s.add(fcurl7);
                                                fcurl7s.clear();
                                                documentacion.setFcurl7(fcurl7s);
                                            }
                                        }
                                    } else {
                                        // cantidad.setVisibility(View.GONE);

                                        fcurl7s.add(fcurl7);
                                        fcurl7s.clear();
                                        documentacion.setFcurl7(fcurl7s);
                                    }


                                    documentacion.setFcurl7Id(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));
                                    documentacion.setNombreCurl7(documentos.getDatos().get(i).getDescripcion() + "");
                                    break;

                                case "8":

                                    fcurl8s = new ArrayList<>();

                                    if (documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                        for (int j = 0; j < documentos.getDatos().get(i).getDocumentos().size(); j++) {
                                            if (documentos.getDatos().get(i).getDocumentos().size() > 0) {

                                                fcurl8 = new Doctos.Fcurl8(documentos.getDatos().get(i).getDocumentos().get(j).getUrl(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getFecha(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getNombreArchivo()
                                                );
                                                fcurl8s.add(fcurl8);
                                                documentacion.setFcurl8(fcurl8s);
                                                cantidad.setVisibility(View.VISIBLE);

                                            } else {
                                                //   cantidad.setVisibility(View.GONE);

                                                fcurl8s.add(fcurl8);
                                                fcurl8s.clear();
                                                documentacion.setFcurl8(fcurl8s);
                                            }
                                        }
                                    } else {
                                        //  cantidad.setVisibility(View.GONE);

                                        fcurl8s.add(fcurl8);
                                        fcurl8s.clear();
                                        documentacion.setFcurl8(fcurl8s);
                                    }


                                    documentacion.setFcurl8Id(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));
                                    documentacion.setNombreCurl8(documentos.getDatos().get(i).getDescripcion() + "");

                                    break;

                                case "9":

                                    fcurl9s = new ArrayList<>();

                                    if (documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                        for (int j = 0; j < documentos.getDatos().get(i).getDocumentos().size(); j++) {
                                            if (documentos.getDatos().get(i).getDocumentos().size() > 0) {

                                                fcurl9 = new Doctos.Fcurl9(documentos.getDatos().get(i).getDocumentos().get(j).getUrl(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getFecha(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getNombreArchivo()
                                                );
                                                fcurl9s.add(fcurl9);
                                                documentacion.setFcurl9(fcurl9s);
                                                cantidad.setVisibility(View.VISIBLE);

                                            } else {
                                                //   cantidad.setVisibility(View.GONE);

                                                fcurl9s.add(fcurl9);
                                                fcurl9s.clear();
                                                documentacion.setFcurl9(fcurl9s);
                                            }
                                        }
                                    } else {
                                        // cantidad.setVisibility(View.GONE);

                                        fcurl9s.add(fcurl9);
                                        fcurl9s.clear();
                                        documentacion.setFcurl9(fcurl9s);
                                    }


                                    documentacion.setFcurl9Id(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));
                                    documentacion.setNombreCurl9(documentos.getDatos().get(i).getDescripcion() + "");

                                    break;

                                case "10":

                                    fcurl10s = new ArrayList<>();
                                    if (documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                        for (int j = 0; j < documentos.getDatos().get(i).getDocumentos().size(); j++) {
                                            if (documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                                fcurl10 = new Doctos.Fcurl10(documentos.getDatos().get(i).getDocumentos().get(j).getUrl(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getFecha(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getNombreArchivo()
                                                );
                                                fcurl10s.add(fcurl10);
                                                documentacion.setFcurl10(fcurl10s);
                                                cantidad.setVisibility(View.VISIBLE);

                                            } else {
                                                //   cantidad.setVisibility(View.GONE);

                                                fcurl10s.add(fcurl10);
                                                fcurl10s.clear();
                                                documentacion.setFcurl10(fcurl10s);
                                            }
                                        }
                                    } else {
                                        // cantidad.setVisibility(View.GONE);

                                        fcurl10s.add(fcurl10);
                                        fcurl10s.clear();
                                        documentacion.setFcurl10(fcurl10s);
                                    }

                                    documentacion.setFcurl10Id(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));
                                    documentacion.setNombreCurl10(documentos.getDatos().get(i).getDescripcion() + "");

                                    break;

                                case "11":

                                    fcurl11s = new ArrayList<>();

                                    if (documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                        for (int j = 0; j < documentos.getDatos().get(i).getDocumentos().size(); j++) {
                                            if (documentos.getDatos().get(i).getDocumentos().size() > 0) {

                                                fcurl11 = new Doctos.Fcurl11(documentos.getDatos().get(i).getDocumentos().get(j).getUrl(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getFecha(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getNombreArchivo()
                                                );
                                                fcurl11s.add(fcurl11);
                                                documentacion.setFcurl11(fcurl11s);
                                                cantidad.setVisibility(View.VISIBLE);

                                            } else {
                                                fcurl11s.add(fcurl11);
                                                fcurl11s.clear();
                                                documentacion.setFcurl11(fcurl11s);
                                                //  cantidad.setVisibility(View.GONE);

                                            }
                                        }
                                    } else {
                                        // cantidad.setVisibility(View.GONE);

                                        fcurl11s.add(fcurl11);
                                        fcurl11s.clear();
                                        documentacion.setFcurl11(fcurl11s);
                                    }


                                    documentacion.setFcurl11Id(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));
                                    documentacion.setNombreCurl11(documentos.getDatos().get(i).getDescripcion() + "");

                                    break;

                                case "12":

                                    fcurl12s = new ArrayList<>();

                                    if (documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                        for (int j = 0; j < documentos.getDatos().get(i).getDocumentos().size(); j++) {
                                            if (documentos.getDatos().get(i).getDocumentos().size() > 0) {

                                                fcurl12 = new Doctos.Fcurl12(documentos.getDatos().get(i).getDocumentos().get(j).getUrl(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getFecha(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getNombreArchivo()
                                                );
                                                fcurl12s.add(fcurl12);
                                                documentacion.setFcurl12(fcurl12s);
                                                cantidad.setVisibility(View.VISIBLE);

                                            } else {
                                                //    cantidad.setVisibility(View.GONE);
                                                fcurl12s.add(fcurl12);
                                                fcurl12s.clear();
                                                documentacion.setFcurl12(fcurl12s);
                                            }
                                        }
                                    } else {
                                        //  cantidad.setVisibility(View.GONE);

                                        fcurl12s.add(fcurl12);
                                        fcurl12s.clear();
                                        documentacion.setFcurl12(fcurl12s);
                                    }

                                    documentacion.setFcurl12Id(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));
                                    documentacion.setNombreCurl12(documentos.getDatos().get(i).getDescripcion() + "");

                                    break;

                                case "13":

                                    fcurl13s = new ArrayList<>();


                                    if (documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                        for (int j = 0; j < documentos.getDatos().get(i).getDocumentos().size(); j++) {
                                            if (documentos.getDatos().get(i).getDocumentos().size() > 0) {

                                                fcurl13 = new Doctos.Fcurl13(documentos.getDatos().get(i).getDocumentos().get(j).getUrl(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getFecha(),
                                                        documentos.getDatos().get(i).getDocumentos().get(j).getNombreArchivo()
                                                );
                                                fcurl13s.add(fcurl13);
                                                documentacion.setFcurl13(fcurl13s);
                                                cantidad.setVisibility(View.VISIBLE);

                                            } else {
                                                //  cantidad.setVisibility(View.GONE);

                                                fcurl13s.add(fcurl13);
                                                fcurl13s.clear();
                                                documentacion.setFcurl13(fcurl13s);
                                            }
                                        }
                                    } else {
                                        //cantidad.setVisibility(View.GONE);

                                        fcurl13s.add(fcurl13);
                                        fcurl13s.clear();
                                        documentacion.setFcurl13(fcurl13s);
                                    }


                                    documentacion.setFcurl13Id(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));
                                    documentacion.setNombreCurl13(documentos.getDatos().get(i).getDescripcion() + "");

                                    break;
                                default:
                                    break;
                            }

                            TableRow tbrow = new TableRow(ActivityDocumentos.this);
                            tbrow.setBackgroundColor(resource.getColor(R.color.blanco));
                            TextView t1v1 = new TextView(ActivityDocumentos.this);
                            if (documentos.getDatos().get(i).getOpcional().equals("0")) {

                                String sourceString = "<b>" + documentos.getDatos().get(i).getDescripcion() + "  " + getString(R.string.obligatorios) + "</b> ";
                                t1v1.setText(Html.fromHtml(sourceString));

                            } else {
                                String sourceString = "<b>" + documentos.getDatos().get(i).getDescripcion() + "</b> ";
                                t1v1.setText("  " + documentos.getDatos().get(i).getDescripcion() + "  ");
                                t1v1.setText(Html.fromHtml(sourceString));

                            }


                            t1v1.setTextColor(resource.getColor(R.color.azul));
                            t1v1.setPadding(0, paddingPixel, 0, 0);
                            t1v1.setGravity(Gravity.START);
                            t1v1.setLayoutParams(new TableRow.LayoutParams(600,
                                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0));

                            final TextView imageView = new TextView(ActivityDocumentos.this);
                            imageView.setPadding(0, paddingPixel, 0, 0);
                            imageView.setGravity(Gravity.START);

//                            imageView.setLayoutParams( new TableRow.LayoutParams( android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
//                                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );

                            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(100, 80);
                            imageView.setLayoutParams(layoutParams);

                            imageView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.cam, 0, 0, 0);
                            imageView.setText(" ");


                            TextView upload = new TextView(ActivityDocumentos.this);
                            final TextView masFotos = new TextView(ActivityDocumentos.this);
                            masFotos.setPadding(0, paddingPixel, 0, 0);
                            masFotos.setGravity(Gravity.END);

//                            masFotos.setLayoutParams( new TableRow.LayoutParams( android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
//                                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );

                            masFotos.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ot, 0, 0, 0);

                            masFotos.setLayoutParams(layoutParams);

                            masFotos.setVisibility(View.GONE);
                            upload.setVisibility(View.GONE);

                            if (documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                //if(documentos.getDatos().get(i).getDocumentos().get(0).getUrl().equals("")){
                                upload.setVisibility(View.VISIBLE);
                                upload.setPadding(0, paddingPixel, 0, 0);
                                upload.setCompoundDrawablesWithIntrinsicBounds(R.drawable.palomita_azul, 0, 0, 0);
                                upload.setGravity(Gravity.CENTER);
                                upload.setLayoutParams(new TableRow.LayoutParams(35,
                                        android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0));
                                masFotos.setVisibility(View.VISIBLE);
                                //}
                            }

                            final TextView size = new TextView(ActivityDocumentos.this);
                            //if(documentos.getDatos().get(i).getDocumentos().size()>0){
                            size.setText(documentos.getDatos().get(i).getDocumentos().size() + "");
                            //}else{
                            size.setVisibility(View.VISIBLE);
                            //}

                            size.setTextSize(9);
                            size.setTextColor(resource.getColor(R.color.azul));
                            size.setPadding(20, paddingPixel, 0, 0);
                            size.setGravity(Gravity.START);
                            size.setLayoutParams(new TableRow.LayoutParams(6,
                                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0));


                            tbrow.addView(t1v1);
                            tbrow.addView(imageView);
                            tbrow.addView(masFotos);


                            final int finalI = i;

                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityDocumentos.this, R.style.AlertDialogCustom);
                                    builder.setMessage(resource.getString(R.string.camara))
                                            .setCancelable(false)
                                            .setPositiveButton(resource.getString(R.string.galeria), new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    Intent intent = new Intent();
                                                    intent.setType("image/*");
                                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                                    startActivityForResult(Intent.createChooser(intent, "Select Imagen"),
                                                            Integer.parseInt(documentos.getDatos().get(finalI).getDocumentoId()));
                                                    sumSize(size, documentos.getDatos().get(finalI).getDocumentoId());
                                                    banderaCam = 0;
                                                    masFotos.setVisibility(View.VISIBLE);
                                                }
                                            })
                                            .setNegativeButton(resource.getString(R.string.foto), new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                                    if (pictureIntent.resolveActivity(getPackageManager()) != null) {
                                                        File photoFile = null;
                                                        try {
                                                            photoFile = createImageFile(ActivityDocumentos.this);
                                                        } catch (IOException ex) {
                                                        }
                                                        if (photoFile != null) {
                                                            Uri photoURI = FileProvider.getUriForFile(ActivityDocumentos.this, getString(R.string.file_provider_authority), photoFile);
                                                            pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                                            startActivityForResult(pictureIntent,
                                                                    Integer.parseInt(documentos.getDatos().get(finalI).getDocumentoId()));
                                                            sumSize(size, documentos.getDatos().get(finalI).getDocumentoId());

                                                            banderaCam = 0;
                                                        }
                                                    }
                                                    masFotos.setVisibility(View.VISIBLE);
                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.setTitle("Documentos");
                                    alert.show();




                                }
                            });


                            final int finalI1 = i;
                            masFotos.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {


                                    sumSize(size, documentos.getDatos().get(finalI1).getDocumentoId());

                                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityDocumentos.this, R.style.AlertDialogCustom);
                                    builder.setMessage(resource.getString(R.string.camara))
                                            .setCancelable(false)
                                            .setPositiveButton(resource.getString(R.string.galeria), new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    Intent intent = new Intent();
                                                    intent.setType("image/*");
                                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                                    startActivityForResult(Intent.createChooser(intent, "Select Imagen"), Integer.parseInt(documentos.getDatos().get(finalI).getDocumentoId()));
                                                    banderaCam = 1;
                                                }
                                            })
                                            .setNegativeButton(resource.getString(R.string.foto), new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                                    if (pictureIntent.resolveActivity(getPackageManager()) != null) {
                                                        File photoFile = null;
                                                        try {
                                                            photoFile = createImageFile(ActivityDocumentos.this);
                                                        } catch (IOException ex) {
                                                        }
                                                        if (photoFile != null) {
                                                            Uri photoURI = FileProvider.getUriForFile(ActivityDocumentos.this, getString(R.string.file_provider_authority), photoFile);
                                                            pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                                            startActivityForResult(pictureIntent, Integer.parseInt(documentos.getDatos().get(finalI).getDocumentoId()));
                                                            banderaCam = 1;
                                                        }
                                                    }
                                                }
                                            });
                                    AlertDialog alert = builder.create();
                                    alert.setTitle("Documentos");
                                    alert.show();

                                    masFotos.setVisibility(View.VISIBLE);
                                    cantidad.setVisibility(View.VISIBLE);
                                }
                            });

                            plomoTable.addView(tbrow);
                            plomoTable.addView(cantidad);
                            plomoTable.addView(size);
                        }


                        for (int i = 0; i < docto.getDatos().size(); i++) {
                            if (docto.getDatos().get(i).getOpcional().equals("0")) {
                                if (!documentacion.getFcurl1().isEmpty() &&
                                        !documentacion.getFcurl2().isEmpty() &&
                                        !documentacion.getFcurl3().isEmpty() &&
                                        !documentacion.getFcurl4().isEmpty() &&
                                        !documentacion.getFcurl7().isEmpty() &&
                                        !documentacion.getFcurl8().isEmpty() &&
                                        !documentacion.getFcurl9().isEmpty()) {
                                    permisos[0] = true;

                                }
                            }
                        }

                        if (permisos[0]) {
                            binding.btnFinalizar.setEnabled(true);
                            binding.btnFinalizar.setAlpha(1);
                        } else {
                            binding.btnFinalizar.setEnabled(false);
                            binding.btnFinalizar.setAlpha(.4f);
                        }
                        loadingProgress(progressDialog, 1);
                    } else {
                        loadingProgress(progressDialog, 1);
                    }

                } else {
                    loadingProgress(progressDialog, 1);
                }

            }

            @Override
            public void reject(Exception e) {
                loadingProgress(progressDialog, 1);
            }
        });
    }

    TextView sizeBandera;

    public void sumSize(TextView view, String id) {
        sizeBandera = view;
        if (documentacion != null) {
            switch (id) {
                case "1":
                    view.setText(documentacion.getFcurl1().size() + "");
                    break;
                case "2":
                    view.setText(documentacion.getFcurl2().size() + "");
                    break;
                case "3":
                    view.setText(documentacion.getFcurl3().size() + "");
                    break;
                case "4":
                    view.setText(documentacion.getFcurl4().size() + "");
                    break;
                case "5":
                    view.setText(documentacion.getFcurl5().size() + "");
                    break;
                case "6":
                    view.setText(documentacion.getFcurl6().size() + "");
                    break;
                case "7":
                    view.setText(documentacion.getFcurl7().size() + "");
                    break;
                case "8":
                    view.setText(documentacion.getFcurl8().size() + "");
                    break;
                case "9":
                    view.setText(documentacion.getFcurl9().size() + "");
                    break;
                case "10":
                    view.setText(documentacion.getFcurl10().size() + "");
                    break;
                case "11":
                    view.setText(documentacion.getFcurl11().size() + "");
                    break;
                case "12":
                    view.setText(documentacion.getFcurl12().size() + "");
                    break;
                case "13":
                    view.setText(documentacion.getFcurl3().size() + "");
                    break;
                default:
                    break;
            }
        }

    }


    private int CAMERA_CONTRATO = 1;
    private int CAMERA_TITULO_PROPIEDAD = 2;
    private int CAMERA_IDENTIFICACION_OFICIAL = 3;
    private int CAMERA_PREDIAL = 4;
    private int CAMERA_RECIBO_LUZ = 5;
    private int CAMERA_RECIBO_AGUA = 6;
    private int CAMERA_RFC_R1 = 7;
    private int CAMERA_ESTADO_CUENTA = 8;
    private int CAMERA_COMPROBANTE_DOMICILIO = 9;
    private int CAMERA_ACTA_MATRIMONIO = 10;
    private int CAMERA_CONSTITUTIVA = 11;
    private int CAMERA_CARTA_PODER_REPRESENTANTE = 12;
    private int ID_DEL_REPRE = 13;

    String imageFilePath;
    String nombreImagen;
    private File createImageFile(Context c) throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = c.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        nombreImagen = imageFileName;
        imageFilePath = image.getAbsolutePath();
        return image;
    }

    int banderaCam = 0;

    /**
     * método para realizar la respuesta de cada intent que se hace en la actividad (ver pdf, tomar foto)
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        SharedPreferences preferences = getSharedPreferences("datosExpansion", Context.MODE_PRIVATE);
        String mdid = preferences.getString("mdId", "");

        if (requestCode == CAMERA_CONTRATO && resultCode == -1) {
            if (resultCode == 0) {

            } else {
                nombreImagen = Util.random() + "CAMERA_CONTRATO";
                if (getBitmap(imageFilePath) != null) {
                    Bitmap bitfromPath = getBitmap(imageFilePath);
                    String base64 = getStringImage(compressImage(bitfromPath, 1000));
                    obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                } else {

                    Uri filePath = data.getData();
                    try {
                        //Cómo obtener el mapa de bits de la Galería
                        Bitmap bitfromPath = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                        String base64 = getStringImage(compressImage(bitfromPath, 1200));
                        obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else if (requestCode == CAMERA_TITULO_PROPIEDAD && resultCode == -1) {
            if (resultCode == 0) {

            } else {
                nombreImagen = Util.random() + "CAMERA_TITULO_PROPIEDAD";
                if (getBitmap(imageFilePath) != null) {
                    Bitmap bitfromPath = getBitmap(imageFilePath);
                    String base64 = getStringImage(compressImage(bitfromPath, 1000));
                    obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                } else {
                    Uri filePath = data.getData();
                    try {
                        //Cómo obtener el mapa de bits de la Galería
                        Bitmap bitfromPath = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                        String base64 = getStringImage(compressImage(bitfromPath, 1200));
                        obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else if (requestCode == CAMERA_IDENTIFICACION_OFICIAL && resultCode == -1) {
            if (resultCode == 0) {

            } else {
                nombreImagen = Util.random() + "CAMERA_IDENTIFICACION_OFICIAL";
                if (getBitmap(imageFilePath) != null) {
                    Bitmap bitfromPath = getBitmap(imageFilePath);
                    String base64 = getStringImage(compressImage(bitfromPath, 1000));
                    obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                } else {
                    Uri filePath = data.getData();
                    try {
                        //Cómo obtener el mapa de bits de la Galería
                        Bitmap bitfromPath = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                        String base64 = getStringImage(compressImage(bitfromPath, 1200));
                        obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else if (requestCode == CAMERA_PREDIAL && resultCode == -1) {
            if (resultCode == 0) {

            } else {
                nombreImagen = Util.random() + "CAMERA_PREDIAL";
                if (getBitmap(imageFilePath) != null) {
                    Bitmap bitfromPath = getBitmap(imageFilePath);
                    String base64 = getStringImage(compressImage(bitfromPath, 1000));
                    obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                } else {

                    Uri filePath = data.getData();
                    try {
                        //Cómo obtener el mapa de bits de la Galería
                        Bitmap bitfromPath = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                        String base64 = getStringImage(compressImage(bitfromPath, 1200));
                        obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else if (requestCode == CAMERA_RECIBO_LUZ && resultCode == -1) {
            if (resultCode == 0) {

            } else {
                nombreImagen = Util.random() + "CAMERA_RECIBO_LUZ";
                if (getBitmap(imageFilePath) != null) {
                    Bitmap bitfromPath = getBitmap(imageFilePath);
                    String base64 = getStringImage(compressImage(bitfromPath, 1000));
                    obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                } else {
                    Uri filePath = data.getData();
                    try {
                        //Cómo obtener el mapa de bits de la Galería
                        Bitmap bitfromPath = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                        String base64 = getStringImage(compressImage(bitfromPath, 1200));
                        obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else if (requestCode == CAMERA_RECIBO_AGUA && resultCode == -1) {
            if (resultCode == 0) {

            } else {
                nombreImagen = Util.random() + "CAMERA_RECIBO_AGUA";
                if (getBitmap(imageFilePath) != null) {
                    Bitmap bitfromPath = getBitmap(imageFilePath);
                    String base64 = getStringImage(compressImage(bitfromPath, 1000));
                    obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                } else {

                    Uri filePath = data.getData();
                    try {
                        //Cómo obtener el mapa de bits de la Galería
                        Bitmap bitfromPath = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                        String base64 = getStringImage(compressImage(bitfromPath, 1200));
                        obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } else if (requestCode == CAMERA_RFC_R1 && resultCode == -1) {
            if (resultCode == 0) {

            } else {
                nombreImagen = Util.random() + "CAMERA_RFC_R1";
                if (getBitmap(imageFilePath) != null) {
                    Bitmap bitfromPath = getBitmap(imageFilePath);
                    String base64 = getStringImage(compressImage(bitfromPath, 1000));
                    obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                } else {

                    Uri filePath = data.getData();
                    try {
                        //Cómo obtener el mapa de bits de la Galería
                        Bitmap bitfromPath = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                        String base64 = getStringImage(compressImage(bitfromPath, 1200));
                        obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else if (requestCode == CAMERA_ESTADO_CUENTA && resultCode == -1) {
            if (resultCode == 0) {

            } else {
                nombreImagen = Util.random() + "CAMERA_ESTADO_CUENTA";
                if (getBitmap(imageFilePath) != null) {
                    Bitmap bitfromPath = getBitmap(imageFilePath);
                    String base64 = getStringImage(compressImage(bitfromPath, 1000));
                    obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                } else {
                    Uri filePath = data.getData();
                    try {
                        //Cómo obtener el mapa de bits de la Galería
                        Bitmap bitfromPath = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                        String base64 = getStringImage(compressImage(bitfromPath, 1200));
                        obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else if (requestCode == CAMERA_COMPROBANTE_DOMICILIO && resultCode == -1) {
            if (resultCode == 0) {

            } else {
                nombreImagen = Util.random() + "CAMERA_COMPROBANTE_DOMICILIO";
                if (getBitmap(imageFilePath) != null) {
                    Bitmap bitfromPath = getBitmap(imageFilePath);
                    String base64 = getStringImage(compressImage(bitfromPath, 1000));
                    obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                } else {
                    Uri filePath = data.getData();
                    try {
                        //Cómo obtener el mapa de bits de la Galería
                        Bitmap bitfromPath = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                        String base64 = getStringImage(compressImage(bitfromPath, 1200));
                        obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else if (requestCode == CAMERA_ACTA_MATRIMONIO && resultCode == -1) {
            if (resultCode == 0) {

            } else {
                nombreImagen = Util.random() + "CAMERA_ACTA_MATRIMONIO";
                if (getBitmap(imageFilePath) != null) {
                    Bitmap bitfromPath = getBitmap(imageFilePath);
                    String base64 = getStringImage(compressImage(bitfromPath, 1000));
                    obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                } else {
                    Uri filePath = data.getData();
                    try {
                        Bitmap bitfromPath = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                        String base64 = getStringImage(compressImage(bitfromPath, 1200));
                        obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else if (requestCode == CAMERA_CONSTITUTIVA && resultCode == -1) {
            if (resultCode == 0) {

            } else {
                nombreImagen = Util.random() + "CAMERA_CONSTITUTIVA";
                if (getBitmap(imageFilePath) != null) {
                    Bitmap bitfromPath = getBitmap(imageFilePath);
                    String base64 = getStringImage(compressImage(bitfromPath, 1000));
                    obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                } else {

                        Uri filePath = data.getData();
                        try {
                            //Cómo obtener el mapa de bits de la Galería
                            Bitmap bitfromPath = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                            String base64 = getStringImage(compressImage(bitfromPath, 1200));
                            obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        } else if (requestCode == CAMERA_CARTA_PODER_REPRESENTANTE && resultCode == -1) {
            if (resultCode == 0) {

            } else {
                nombreImagen = Util.random() + "CAMERA_CARTA_PODER_REPRESENTANTE";
                if (getBitmap(imageFilePath) != null) {
                    Bitmap bitfromPath = getBitmap(imageFilePath);
                    String base64 = getStringImage(compressImage(bitfromPath, 1000));
                    obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                } else {
                    Uri filePath = data.getData();
                    try {
                        //Cómo obtener el mapa de bits de la Galería
                        Bitmap bitfromPath = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                        String base64 = getStringImage(compressImage(bitfromPath, 1200));
                        obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        } else if (requestCode == ID_DEL_REPRE && resultCode == -1) {
            if (resultCode == 0) {

            } else {
                //TODO MAKE A CONDITION IF PHOTO FROM GALLERY
                nombreImagen = Util.random() + "ID_DEL_REPRE";
                if (getBitmap(imageFilePath) != null) {
                    Bitmap bitfromPath = getBitmap(imageFilePath);
                    String base64 = getStringImage(compressImage(bitfromPath, 1000));
                    obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                } else {

                    Uri filePath = data.getData();
                    try {
                        //Cómo obtener el mapa de bits de la Galería
                        Bitmap bitfromPath = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                        String base64 = getStringImage(compressImage(bitfromPath, 1200));
                        obtenerUrl(nombreImagen, base64, String.valueOf(mdid));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else if (resultCode == 0) {


        }

    }

    public static Bitmap getBitmap(String path) {
        Bitmap bitmap = null;
        try {
            File f = new File(path);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bitmap;
    }

    public static Bitmap compressImage(Bitmap image, int maxSize) {
        if (image.getWidth() != 0) {
            int width = image.getWidth();
            int height = image.getHeight();

            float bitmapRatio = (float) width / (float) height;
            if (bitmapRatio > 1) {
                width = maxSize;
                height = (int) (width / bitmapRatio);
            } else {
                height = maxSize;
                width = (int) (height * bitmapRatio);
            }
            return Bitmap.createScaledBitmap(image, width, height, true);
        }
        return null;
    }


    public String getFechaHora() {
        long timeInMillis = System.currentTimeMillis();
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(timeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy kk:mm:ss");
        String dateforrow = dateFormat.format(cal1.getTime());
        return dateforrow;
    }

    ProgressDialog progressDialog;

    public void obtenerUrl(String foto, String b64, String mdId) {
        loadingProgress(progressDialog, 0);
        ProviderObtenerUrl.getInstance(ActivityDocumentos.this).obtenerUrl(mdId, foto, b64, new ProviderObtenerUrl.ConsultaUrl() {
            @Override
            public void resolve(Codigos codigo) {
                if (codigo != null) {
                    final Boolean[] permisos = {false};
                    if (codigo.getResultado().getSecureUrl() != null) {
                        if (codigo.getResultado().getSecureUrl().contains("CAMERA_CONTRATO")) {

                            if (banderaCam == 0) {

                                fcurl1s.clear();
                                fcurl1 = new Doctos.Fcurl1(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl1s.add(fcurl1);
                                documentacion.setFcurl1(fcurl1s);
                                sumSize(sizeBandera, String.valueOf(CAMERA_CONTRATO));

                            } else {

                                fcurl1 = new Doctos.Fcurl1(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl1s.add(fcurl1);
                                documentacion.setFcurl1(fcurl1s);
                                sumSize(sizeBandera, String.valueOf(CAMERA_CONTRATO));

                            }


                            permisos[0] = getObligatorios();

                            if (permisos[0]) {
                                binding.btnFinalizar.setAlpha(1);
                                binding.btnFinalizar.setEnabled(true);
                            } else {
                                binding.btnFinalizar.setAlpha(.4f);
                                binding.btnFinalizar.setEnabled(false);
                            }
                            loadingProgress(progressDialog, 1);

                        } else if (codigo.getResultado().getSecureUrl().contains("CAMERA_TITULO_PROPIEDAD")) {

                            if (banderaCam == 0) {

                                fcurl2s.clear();
                                fcurl2 = new Doctos.Fcurl2(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl2s.add(fcurl2);
                                documentacion.setFcurl2(fcurl2s);
                                sumSize(sizeBandera, String.valueOf(CAMERA_TITULO_PROPIEDAD));

                            } else {
                                fcurl2 = new Doctos.Fcurl2(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl2s.add(fcurl2);
                                documentacion.setFcurl2(fcurl2s);
                                sumSize(sizeBandera, String.valueOf(CAMERA_TITULO_PROPIEDAD));

                            }
                            permisos[0] = getObligatorios();

                            if (permisos[0]) {
                                binding.btnFinalizar.setAlpha(1);
                                binding.btnFinalizar.setEnabled(true);
                            } else {
                                binding.btnFinalizar.setAlpha(.4f);
                                binding.btnFinalizar.setEnabled(false);
                            }
                            loadingProgress(progressDialog, 1);

                        } else if (codigo.getResultado().getSecureUrl().contains("CAMERA_IDENTIFICACION_OFICIAL")) {

                            if (banderaCam == 0) {
                                fcurl3s.clear();
                                fcurl3 = new Doctos.Fcurl3(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl3s.add(fcurl3);
                                documentacion.setFcurl3(fcurl3s);
                                sumSize(sizeBandera, String.valueOf(CAMERA_IDENTIFICACION_OFICIAL));

                            } else {
                                fcurl3 = new Doctos.Fcurl3(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl3s.add(fcurl3);
                                documentacion.setFcurl3(fcurl3s);

                                sumSize(sizeBandera, String.valueOf(CAMERA_IDENTIFICACION_OFICIAL));

                            }

                            permisos[0] = getObligatorios();

                            if (permisos[0]) {
                                binding.btnFinalizar.setAlpha(1);
                                binding.btnFinalizar.setEnabled(true);
                            } else {
                                binding.btnFinalizar.setAlpha(.4f);
                                binding.btnFinalizar.setEnabled(false);
                            }

                            loadingProgress(progressDialog, 1);

                        } else if (codigo.getResultado().getSecureUrl().contains("CAMERA_PREDIAL")) {


                            if (banderaCam == 0) {
                                fcurl4s.clear();
                                fcurl4 = new Doctos.Fcurl4(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl4s.add(fcurl4);
                                documentacion.setFcurl4(fcurl4s);

                                sumSize(sizeBandera, String.valueOf(CAMERA_PREDIAL));

                            } else {
                                fcurl4 = new Doctos.Fcurl4(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl4s.add(fcurl4);
                                documentacion.setFcurl4(fcurl4s);

                                sumSize(sizeBandera, String.valueOf(CAMERA_PREDIAL));

                            }
                            permisos[0] = getObligatorios();

                            if (permisos[0]) {
                                binding.btnFinalizar.setAlpha(1);
                                binding.btnFinalizar.setEnabled(true);
                            } else {
                                binding.btnFinalizar.setAlpha(.4f);
                                binding.btnFinalizar.setEnabled(false);
                            }

                            loadingProgress(progressDialog, 1);

                        } else if (codigo.getResultado().getSecureUrl().contains("CAMERA_RECIBO_LUZ")) {

                            if (banderaCam == 0) {
                                fcurl5s.clear();
                                fcurl5 = new Doctos.Fcurl5(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl5s.add(fcurl5);
                                documentacion.setFcurl5(fcurl5s);

                                sumSize(sizeBandera, String.valueOf(CAMERA_RECIBO_LUZ));
                            } else {
                                fcurl5 = new Doctos.Fcurl5(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl5s.add(fcurl5);
                                documentacion.setFcurl5(fcurl5s);

                                String json = new Gson().toJson(documentacion);
                                sumSize(sizeBandera, String.valueOf(CAMERA_RECIBO_LUZ));

                            }

                            permisos[0] = getObligatorios();

                            if (permisos[0]) {
                                binding.btnFinalizar.setAlpha(1);
                                binding.btnFinalizar.setEnabled(true);
                            } else {
                                binding.btnFinalizar.setAlpha(.4f);
                                binding.btnFinalizar.setEnabled(false);
                            }

                            loadingProgress(progressDialog, 1);

                        } else if (codigo.getResultado().getSecureUrl().contains("CAMERA_RECIBO_AGUA")) {

                            if (banderaCam == 0) {
                                fcurl6s.clear();
                                fcurl6 = new Doctos.Fcurl6(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl6s.add(fcurl6);
                                documentacion.setFcurl6(fcurl6s);

                                sumSize(sizeBandera, String.valueOf(CAMERA_RECIBO_AGUA));

                            } else {
                                fcurl6 = new Doctos.Fcurl6(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl6s.add(fcurl6);
                                documentacion.setFcurl6(fcurl6s);

                                sumSize(sizeBandera, String.valueOf(CAMERA_RECIBO_AGUA));

                            }

                            permisos[0] = getObligatorios();

                            if (permisos[0]) {
                                binding.btnFinalizar.setAlpha(1);
                                binding.btnFinalizar.setEnabled(true);
                            } else {
                                binding.btnFinalizar.setAlpha(.4f);
                                binding.btnFinalizar.setEnabled(false);
                            }

                            loadingProgress(progressDialog, 1);

                        } else if (codigo.getResultado().getSecureUrl().contains("CAMERA_RFC_R1")) {

                            if (banderaCam == 0) {
                                fcurl7s.clear();
                                fcurl7 = new Doctos.Fcurl7(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl7s.add(fcurl7);
                                documentacion.setFcurl7(fcurl7s);
                                sumSize(sizeBandera, String.valueOf(CAMERA_RFC_R1));

                            } else {
                                fcurl7 = new Doctos.Fcurl7(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl7s.add(fcurl7);
                                documentacion.setFcurl7(fcurl7s);
                                sumSize(sizeBandera, String.valueOf(CAMERA_RFC_R1));
                            }

                            permisos[0] = getObligatorios();

                            if (permisos[0]) {
                                binding.btnFinalizar.setAlpha(1);
                                binding.btnFinalizar.setEnabled(true);
                            } else {
                                binding.btnFinalizar.setAlpha(.4f);
                                binding.btnFinalizar.setEnabled(false);
                            }

                            loadingProgress(progressDialog, 1);

                        } else if (codigo.getResultado().getSecureUrl().contains("CAMERA_ESTADO_CUENTA")) {
                            if (banderaCam == 0) {
                                fcurl8s.clear();
                                fcurl8 = new Doctos.Fcurl8(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl8s.add(fcurl8);
                                documentacion.setFcurl8(fcurl8s);
                                sumSize(sizeBandera, String.valueOf(CAMERA_ESTADO_CUENTA));
                            } else {
                                fcurl8 = new Doctos.Fcurl8(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl8s.add(fcurl8);
                                documentacion.setFcurl8(fcurl8s);
                                sumSize(sizeBandera, String.valueOf(CAMERA_ESTADO_CUENTA));
                            }
                            permisos[0] = getObligatorios();

                            if (permisos[0]) {
                                binding.btnFinalizar.setAlpha(1);
                                binding.btnFinalizar.setEnabled(true);
                            } else {
                                binding.btnFinalizar.setAlpha(.4f);
                                binding.btnFinalizar.setEnabled(false);
                            }

                            loadingProgress(progressDialog, 1);

                        } else if (codigo.getResultado().getSecureUrl().contains("CAMERA_COMPROBANTE_DOMICILIO")) {
                            if (banderaCam == 0) {
                                fcurl9s.clear();
                                fcurl9 = new Doctos.Fcurl9(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl9s.add(fcurl9);
                                documentacion.setFcurl9(fcurl9s);
                                sumSize(sizeBandera, String.valueOf(CAMERA_COMPROBANTE_DOMICILIO));

                            } else {
                                fcurl9 = new Doctos.Fcurl9(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl9s.add(fcurl9);
                                documentacion.setFcurl9(fcurl9s);
                                sumSize(sizeBandera, String.valueOf(CAMERA_COMPROBANTE_DOMICILIO));
                            }

                            permisos[0] = getObligatorios();

                            if (permisos[0]) {
                                binding.btnFinalizar.setAlpha(1);
                                binding.btnFinalizar.setEnabled(true);
                            } else {
                                binding.btnFinalizar.setAlpha(.4f);
                                binding.btnFinalizar.setEnabled(false);
                            }
                            loadingProgress(progressDialog, 1);

                        } else if (codigo.getResultado().getSecureUrl().contains("CAMERA_ACTA_MATRIMONIO")) {

                            if (banderaCam == 0) {
                                fcurl10s.clear();
                                fcurl10 = new Doctos.Fcurl10(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl10s.add(fcurl10);
                                documentacion.setFcurl10(fcurl10s);
                                sumSize(sizeBandera, String.valueOf(CAMERA_ACTA_MATRIMONIO));


                            } else {
                                fcurl10 = new Doctos.Fcurl10(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl10s.add(fcurl10);
                                documentacion.setFcurl10(fcurl10s);

                                sumSize(sizeBandera, String.valueOf(CAMERA_ACTA_MATRIMONIO));


                            }


                            permisos[0] = getObligatorios();

                            if (permisos[0]) {
                                binding.btnFinalizar.setAlpha(1);
                                binding.btnFinalizar.setEnabled(true);
                            } else {
                                binding.btnFinalizar.setAlpha(.4f);
                                binding.btnFinalizar.setEnabled(false);
                            }
                            loadingProgress(progressDialog, 1);

                        } else if (codigo.getResultado().getSecureUrl().contains("CAMERA_CONSTITUTIVA")) {

                            if (banderaCam == 0) {
                                fcurl11s.clear();
                                fcurl11 = new Doctos.Fcurl11(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl11s.add(fcurl11);
                                documentacion.setFcurl11(fcurl11s);

                                sumSize(sizeBandera, String.valueOf(CAMERA_CONSTITUTIVA));


                            } else {
                                fcurl11 = new Doctos.Fcurl11(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl11s.add(fcurl11);
                                documentacion.setFcurl11(fcurl11s);

                                sumSize(sizeBandera, String.valueOf(CAMERA_CONSTITUTIVA));


                            }
                            permisos[0] = getObligatorios();

                            if (permisos[0]) {
                                binding.btnFinalizar.setAlpha(1);
                                binding.btnFinalizar.setEnabled(true);
                            } else {
                                binding.btnFinalizar.setAlpha(.4f);
                                binding.btnFinalizar.setEnabled(false);
                            }
                            loadingProgress(progressDialog, 1);

                        } else if (codigo.getResultado().getSecureUrl().contains("CAMERA_CARTA_PODER_REPRESENTANTE")) {

                            if (banderaCam == 0) {
                                fcurl12s.clear();
                                fcurl12 = new Doctos.Fcurl12(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl12s.add(fcurl12);
                                documentacion.setFcurl12(fcurl12s);
                                sumSize(sizeBandera, String.valueOf(CAMERA_CARTA_PODER_REPRESENTANTE));

                            } else {
                                fcurl12 = new Doctos.Fcurl12(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl12s.add(fcurl12);
                                documentacion.setFcurl12(fcurl12s);
                                sumSize(sizeBandera, String.valueOf(CAMERA_CARTA_PODER_REPRESENTANTE));

                            }

                            permisos[0] = getObligatorios();

                            if (permisos[0]) {
                                binding.btnFinalizar.setAlpha(1);
                                binding.btnFinalizar.setEnabled(true);
                            } else {
                                binding.btnFinalizar.setAlpha(.4f);
                                binding.btnFinalizar.setEnabled(false);
                            }

                            loadingProgress(progressDialog, 1);

                        } else if (codigo.getResultado().getSecureUrl().contains("ID_DEL_REPRE")) {

                            if (banderaCam == 0) {
                                fcurl13s.clear();
                                fcurl13 = new Doctos.Fcurl13(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl13s.add(fcurl13);
                                documentacion.setFcurl13(fcurl13s);
                                sumSize(sizeBandera, String.valueOf(ID_DEL_REPRE));


                            } else {
                                fcurl13 = new Doctos.Fcurl13(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                fcurl13s.add(fcurl13);
                                documentacion.setFcurl13(fcurl13s);
                                sumSize(sizeBandera, String.valueOf(ID_DEL_REPRE));

                            }
                            permisos[0] = getObligatorios();

                            if (permisos[0]) {
                                binding.btnFinalizar.setAlpha(1);
                                binding.btnFinalizar.setEnabled(true);
                            } else {
                                binding.btnFinalizar.setAlpha(.4f);
                                binding.btnFinalizar.setEnabled(false);
                            }
                            loadingProgress(progressDialog, 1);

                        } else {
                            loadingProgress(progressDialog, 1);
                        }
                    }

                }
            }

            @Override
            public void reject(Exception e) {

            }
        });
    }


    public static void loadingProgress(final ProgressDialog progressDialog, int i) {
        if (i == 0) {
            progressDialog.setTitle("Enviando...");
            progressDialog.setMessage("Espera mientras se carga tu informacion...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }
    }

    public static void loadingProgressDocumentos(final ProgressDialog progressDialog, int i, String mensaje) {
        if (i == 0) {
            progressDialog.setTitle("Enviando...");
            progressDialog.setMessage("Espera mientras se carga tu informacion...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else {

            progressDialog.setTitle("Enviado...");
            progressDialog.setMessage(mensaje + "");
            progressDialog.setCancelable(false);
            progressDialog.show();

            Runnable progressRunnable = new Runnable() {

                @Override
                public void run() {
                    progressDialog.cancel();
                }
            };

            Handler pdCanceller = new Handler();
            pdCanceller.postDelayed(progressRunnable, 100);

        }
    }

    public static String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


}


