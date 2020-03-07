package expansion.neto.com.mx.gerenteapp.ui.documentos;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import expansion.neto.com.mx.gerenteapp.R;
import expansion.neto.com.mx.gerenteapp.databinding.ActivityDocumentosBinding;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentDashboard.FragmentDialogCancelarMdRechazadas;
import expansion.neto.com.mx.gerenteapp.fragment.fragmentDashboard.FragmentDialogTerminaDocumentacion;
import expansion.neto.com.mx.gerenteapp.modelView.autorizaModel.AutorizaResponse;
import expansion.neto.com.mx.gerenteapp.modelView.crearModel.Codigos;
import expansion.neto.com.mx.gerenteapp.modelView.documentosModel.Docto;
import expansion.neto.com.mx.gerenteapp.modelView.documentosModel.Documentos;
import expansion.neto.com.mx.gerenteapp.provider.autorizaProvider.ProviderAutorizaFinal;
import expansion.neto.com.mx.gerenteapp.provider.documentosProvider.ProviderDocumentos;
import expansion.neto.com.mx.gerenteapp.provider.documentosProvider.ProviderGuardarDocumentos;
import expansion.neto.com.mx.gerenteapp.provider.documentosProvider.ProviderObtenerUrl;
import expansion.neto.com.mx.gerenteapp.utils.Util;

public class ActivityDocumentos extends AppCompatActivity {

    private ActivityDocumentosBinding binding;
    String usuario;
    String md;

    int doctoSeleccionadoId = 0;

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

                String json = new Gson().toJson(listaDocumentos);
                loadingProgress(progressDialog, 0);

                ProviderGuardarDocumentos.getInstance(ActivityDocumentos.this).guardarDocumentos(
                        usuario, md, json, getFechaHora(), new ProviderGuardarDocumentos.InterfaceGuardarDocumentos() {
                            @Override
                            public void resolve(Codigos codigo) {
                                if (codigo != null) {
                                    loadingProgress(progressDialog, 1);
                                    Toast.makeText(getApplicationContext(), codigo.getMensaje() + " archivos guardados correctamente",
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
            }
        });

        binding.nombreMD.setText(preferences.getString("nombreSitio", ""));
        binding.nombreMD.setTypeface(null, Typeface.BOLD);

        binding.btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Boolean[] permiso = {false};
                permiso[0] = getObligatorios();
                if (permiso[0]) {

                    String json = new Gson().toJson(listaDocumentos);
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

        for(int i = 0; i < listaDocumentos.size(); i++) {
            if(listaDocumentos.get(i).getOpcional() == 0  && listaDocumentos.get(i).getDocumentos().size() == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * Método que setea la vista con el binding
     */
    private void initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_documentos);
    }

    List<Docto> listaDocumentos = new ArrayList<Docto>();
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

                            List<Docto.DocumentoHoja> listaHojas = new ArrayList<Docto.DocumentoHoja>();
                            Docto doctoWS = new Docto();

                            if(documentos.getDatos().get(i).getDocumentos() != null && documentos.getDatos().get(i).getDocumentos().size() > 0) {
                                for (int j = 0; j < documentos.getDatos().get(i).getDocumentos().size(); j++) {
                                    Docto.DocumentoHoja hoja = new Docto.DocumentoHoja(documentos.getDatos().get(i).getDocumentos().get(j).getUrl(),
                                            documentos.getDatos().get(i).getDocumentos().get(j).getFecha(),
                                            documentos.getDatos().get(i).getDocumentos().get(j).getNombreArchivo());
                                    listaHojas.add(hoja);
                                }
                            }
                            doctoWS.setDocumentos(listaHojas);
                            doctoWS.setDescripcion(documentos.getDatos().get(i).getDescripcion());
                            doctoWS.setDocumentoId(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));
                            doctoWS.setOpcional(Integer.valueOf(documentos.getDatos().get(i).getOpcional()));
                            listaDocumentos.add(doctoWS);


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
                            imageView.setTag(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));


                            TextView upload = new TextView(ActivityDocumentos.this);
                            final TextView masFotos = new TextView(ActivityDocumentos.this);
                            masFotos.setPadding(0, paddingPixel, 0, 0);
                            masFotos.setGravity(Gravity.END);

//                            masFotos.setLayoutParams( new TableRow.LayoutParams( android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
//                                    android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 0 ) );

                            masFotos.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ot, 0, 0, 0);

                            masFotos.setLayoutParams(layoutParams);

                            masFotos.setVisibility(View.GONE);
                            //masFotos.setTag(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));
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
                            size.setTag(Integer.valueOf(documentos.getDatos().get(i).getDocumentoId()));


                            tbrow.addView(t1v1);
                            tbrow.addView(imageView);
                            tbrow.addView(masFotos);


                            final int finalI = i;

                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    doctoSeleccionadoId = Integer.parseInt(imageView.getTag().toString());

                                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityDocumentos.this, R.style.AlertDialogCustom);
                                    builder.setMessage(resource.getString(R.string.camara))
                                            .setCancelable(true)
                                            .setPositiveButton(resource.getString(R.string.galeria), new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    Intent intent = new Intent();
                                                    intent.setType("image/*");
                                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                                    startActivityForResult(Intent.createChooser(intent, "Select Imagen"), Integer.parseInt(documentos.getDatos().get(finalI).getDocumentoId()));
                                                    estableceCantidadTextView(doctoSeleccionadoId, documentos.getDatos().get(finalI).getDocumentos().size());
                                                    setImage(masFotos);
                                                    banderaCam = 0;
                                                }
                                            })
                                            .setNeutralButton("PDF", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                    Intent intent = new Intent();
                                                    intent.setType("application/pdf");
                                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                                    if (intent.resolveActivity(getPackageManager()) != null) {
                                                        File photoFile = null;
                                                        try {
                                                            photoFile = createPdfFile(ActivityDocumentos.this);
                                                        } catch (IOException ex) {
                                                        }
                                                        if (photoFile != null) {
                                                            Uri photoURI = FileProvider.getUriForFile(ActivityDocumentos.this, getString(R.string.file_provider_authority), photoFile);
                                                            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                                            startActivityForResult(intent, Integer.parseInt(documentos.getDatos().get(finalI).getDocumentoId()));
                                                            estableceCantidadTextView(doctoSeleccionadoId, documentos.getDatos().get(finalI).getDocumentos().size());
                                                            banderaCam = 0;
                                                            setImage(masFotos);
                                                        }
                                                    }
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
                                                            estableceCantidadTextView(doctoSeleccionadoId, documentos.getDatos().get(finalI).getDocumentos().size());
                                                            banderaCam = 0;
                                                            setImage(masFotos);
                                                        }
                                                    }
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
                                    doctoSeleccionadoId = Integer.parseInt(imageView.getTag().toString());

                                    AlertDialog.Builder builder = new AlertDialog.Builder(ActivityDocumentos.this, R.style.AlertDialogCustom);
                                    builder.setMessage(resource.getString(R.string.camara))
                                            .setCancelable(false)
                                            .setPositiveButton(resource.getString(R.string.galeria), new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    Intent intent = new Intent();
                                                    intent.setType("image/*");
                                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                                    startActivityForResult(Intent.createChooser(intent, "ChooseFile"), Integer.parseInt(documentos.getDatos().get(finalI).getDocumentoId()));
                                                    estableceCantidadTextView(doctoSeleccionadoId, documentos.getDatos().get(finalI).getDocumentos().size());
                                                    banderaCam = 1;
                                                }
                                            })
                                            .setNeutralButton("PDF", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {

                                                    Intent intent = new Intent();
                                                    intent.setType("application/pdf");
                                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                                    if (intent.resolveActivity(getPackageManager()) != null) {
                                                        File photoFile = null;
                                                        try {
                                                            photoFile = createPdfFile(ActivityDocumentos.this);
                                                        } catch (IOException ex) {
                                                        }
                                                        if (photoFile != null) {
                                                            Uri photoURI = FileProvider.getUriForFile(ActivityDocumentos.this, getString(R.string.file_provider_authority), photoFile);
                                                            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                                                            startActivityForResult(intent, Integer.parseInt(documentos.getDatos().get(finalI).getDocumentoId()));
                                                            estableceCantidadTextView(doctoSeleccionadoId, documentos.getDatos().get(finalI).getDocumentos().size());
                                                            banderaCam = 1;
                                                        }
                                                        masFotos.setVisibility(View.VISIBLE);
                                                    }
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
                                                            estableceCantidadTextView(doctoSeleccionadoId, documentos.getDatos().get(finalI).getDocumentos().size());
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
                        permisos[0] = getObligatorios();
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

    TextView masFotosP;
    public void setImage(TextView image){
        masFotosP = new TextView(this);
        this.masFotosP = image;
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
    private int CAMERA_ACTA_CONSTITUTIVA = 11;
    private int CAMERA_CARTA_PODER_REPRESENTANTE = 12;
    private int CAMERA_ID_REPRESENTANTE = 13;

    String imageFilePath;
    String nombreImagen;

    String imageFilePathPdf;
    String nombreImagenPdf;

    private File createImageFile(Context c) throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = c.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );
        nombreImagen = imageFileName;
        imageFilePath = image.getAbsolutePath();
        return image;
    }


    private File createPdfFile(Context c) throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "PDF_" + timeStamp + "_";
        File storageDir = c.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".pdf",         /* suffix */
                storageDir      /* directory */
        );
        nombreImagenPdf = imageFileName;
        imageFilePathPdf = image.getAbsolutePath();
        return image;
    }

    int banderaCam = 0;

    public String getPDFPath(Uri uri) {

        final String id = DocumentsContract.getDocumentId(uri);
        final Uri contentUri = ContentUris.withAppendedId(
                Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private String saveFile(Uri pdfUri, String nombreImagenPdf) {
        try {
            InputStream is = getContentResolver().openInputStream(pdfUri);
            byte[] bytesArray = new byte[is.available()];
            int read = is.read(bytesArray);
            //write to sdcard

            File dir = new File(Environment.getExternalStorageDirectory(), "/pdfneto");
            boolean mkdirs = dir.mkdirs();
            File myPdf = new File(Environment.getExternalStorageDirectory(), "/pdfneto/" + nombreImagenPdf + ".pdf");
            if (read == -1 && mkdirs) {

            }
            FileOutputStream fos = new FileOutputStream(myPdf.getPath());
            fos.write(bytesArray);
            fos.close();
            //            System.out.println(fileString);
            return myPdf.getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

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

        if (resultCode == -1) {
            nombreImagen = Util.random() + "DOCTO";
            if (getBitmap(imageFilePath) != null) {
                obtenerUrl(String.valueOf(mdid), nombreImagen, "png", "1", Uri.parse(imageFilePath));
            } else {
                Uri filePath = data.getData();
                String filePathUri = saveFile(filePath, nombreImagenPdf);
                try {
                    Bitmap bitfromPath = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), filePath);
                    if (bitfromPath != null) {
                        obtenerUrl(String.valueOf(mdid), nombreImagen, "png", "1", Uri.parse(filePathUri));
                    } else {
                        obtenerUrl(String.valueOf(mdid), nombreImagen, "pdf", "2", Uri.parse(filePathUri));
                    }
                } catch (IOException e) {
                    Toast.makeText(ActivityDocumentos.this, "Error al generar el archivo del documento, vuelve a intentarlo", Toast.LENGTH_LONG).show();
                }
            }
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

    public void obtenerUrl(final String mdId, final String nombreImg, final String formato, final String tipoArchivo, final Uri uri) {
        loadingProgress(progressDialog, 0);
        ProviderObtenerUrl.getInstance(ActivityDocumentos.this).obtenerUrl(mdId, nombreImg, formato, tipoArchivo, uri, new ProviderObtenerUrl.ConsultaUrl() {
            @Override
            public void resolve(Codigos codigo) {
                if (codigo != null && codigo.getCodigo() == 200) {
                    final Boolean[] permisos = {false};
                    if (codigo.getResultado() != null) {
                        imageFilePath = "";

                        if(listaDocumentos.size() > 0) {
                            for(int i = 0; i < listaDocumentos.size(); i++) {
                                if(doctoSeleccionadoId == listaDocumentos.get(i).getDocumentoId()) {
                                    if (banderaCam == 0) { //Cuando se abra la cámara para sustituir el docto
                                        listaDocumentos.get(i).getDocumentos().clear();
                                        masFotosP.setVisibility(View.VISIBLE);
                                    }
                                    Docto.DocumentoHoja doctoHoja = new Docto.DocumentoHoja(codigo.getResultado().getSecureUrl() + "", getFechaHora(), nombreImagen);
                                    listaDocumentos.get(i).getDocumentos().add(doctoHoja);
                                    estableceCantidadTextView(doctoSeleccionadoId, listaDocumentos.get(i).getDocumentos().size());
                                    botonFinaliza();
                                    loadingProgress(progressDialog, 1);
                                }
                            }
                        } else {
                            loadingProgress(progressDialog, 1);
                        }
                    } else {
                        loadingProgress(progressDialog, 1);
                    }
                } else {
                    loadingProgress(progressDialog, 1);





                    Toast.makeText(ActivityDocumentos.this, R.string.intenta, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void reject(Exception e) {

            }
        });
    }

    public void estableceCantidadTextView(int doctoId, int size) {
        TableLayout plomoTable = (TableLayout) findViewById(R.id.plomosTable);

        for (int i = 0; i < plomoTable.getChildCount(); i++) {
            View parentRow = plomoTable.getChildAt(i);

            View cajaTexto = parentRow.findViewWithTag(doctoId);
            if (cajaTexto instanceof TextView) {
                if(((TextView) cajaTexto).getTag() != null && Integer.parseInt(((TextView) cajaTexto).getTag() + "") == doctoId) {
                    ((TextView) cajaTexto).setText(String.valueOf(size));
                }
            }
        }
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

    public void botonFinaliza() {
        permisos[0] = getObligatorios();
        if (permisos[0]) {
            binding.btnFinalizar.setAlpha(1);
            binding.btnFinalizar.setEnabled(true);
        } else {
            binding.btnFinalizar.setAlpha(.4f);
            binding.btnFinalizar.setEnabled(false);
        }
    }
}


