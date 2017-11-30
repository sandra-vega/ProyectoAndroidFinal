package com.example.sandra.mi_nota;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.net.sip.SipAudioCall;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import Modelo.DAONota;
import Modelo.Notas;

public class Fragment_Editar_Nota extends Fragment {
    TextView lbl_id;
    EditText txt_titulo;
    EditText txt_descripcion;
    TextView lbl_fecha;
    Button btn_guardar;
    DAONota objetoDAO;

    public static String ARG_ID_LIBRO = "id_libro";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_editar_nota, container, false);

        return vista;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lbl_id = (TextView) getActivity().findViewById(R.id.lbl_id_nota_e);
        txt_titulo = (EditText) getActivity().findViewById(R.id.txt_titulo_e);
        txt_descripcion = (EditText) getActivity().findViewById(R.id.txt_descripcion_e);
        lbl_fecha= (TextView) getActivity().findViewById(R.id.lbl_fecha_nota_e);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());

        Bundle args = getArguments();
        if (args != null) {
            String [] position = args.getStringArray(ARG_ID_LIBRO);
            //Toast.makeText(getActivity(),""+position[0]+""+position[1]+""+position[2]+""+position[3],Toast.LENGTH_LONG).show();
            cambiarTexto(position[0],position[1],position[2],formattedDate);
        }
        btn_guardar = (Button) getActivity().findViewById(R.id.btn_guardar_nota_e);
        objetoDAO = new DAONota(getContext());

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notas ob=new Notas(Integer.parseInt(lbl_id.getText().toString()),txt_titulo.getText().toString(),
                        txt_descripcion.getText().toString(),lbl_fecha.getText().toString());
                objetoDAO.update(ob);
                Confirmacion();
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
                getActivity().getFragmentManager().popBackStack();


            }
        });
    }
    public void Confirmacion(){
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(getActivity());
        dlgAlert.setMessage("La notas se ha modificado exitosamente!");
        dlgAlert.setTitle("Modificar Nota");
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

    public void cambiarTexto(String id, String titulo, String descripcion, String fecha){
        lbl_id.setText(id);
        txt_titulo.setText(titulo);
        txt_descripcion.setText(descripcion);
        lbl_fecha.setText(fecha);
    }
}
