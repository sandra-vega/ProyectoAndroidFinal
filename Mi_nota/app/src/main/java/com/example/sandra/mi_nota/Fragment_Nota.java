package com.example.sandra.mi_nota;



import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Modelo.DAONota;
import Modelo.Notas;

/**
 * Created by sandra on 23/11/2017.
 */

public class Fragment_Nota extends Fragment {

    TextView lbl_fecha;
    EditText txt_titulo_nota;
    EditText txt_descripcion_nota;
    Button btn_guardar;
    DAONota objetoDAO;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_nota, container, false);
        lbl_fecha = view.findViewById(R.id.lbl_fecha_nota);
        txt_titulo_nota = view.findViewById(R.id.txt_titulo);
        txt_descripcion_nota = view.findViewById(R.id.txt_descripcion);
        btn_guardar = view.findViewById(R.id.btn_guardar);
        objetoDAO = new DAONota(getContext());

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c.getTime());
        lbl_fecha.setText(formattedDate);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notas ob=new Notas(txt_titulo_nota.getText().toString(), txt_descripcion_nota.getText().toString(),
                        lbl_fecha.getText().toString());
                objetoDAO.insert(ob);
                Confirmacion();
                FragmentManager fm = getFragmentManager();
                fm.popBackStack();
                getActivity().getFragmentManager().popBackStack();


            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void Confirmacion(){
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(getActivity());
        dlgAlert.setMessage("La notas se ha agregado exitosamente!");
        dlgAlert.setTitle("Agregar Nota");
        dlgAlert.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();
    }

}
