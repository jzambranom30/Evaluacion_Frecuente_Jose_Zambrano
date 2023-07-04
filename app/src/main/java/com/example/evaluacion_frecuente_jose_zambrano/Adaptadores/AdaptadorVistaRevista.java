package com.example.evaluacion_frecuente_jose_zambrano.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.evaluacion_frecuente_jose_zambrano.Modelos.VistaRevista;
import com.example.evaluacion_frecuente_jose_zambrano.R;

import java.util.ArrayList;

public class AdaptadorVistaRevista extends ArrayAdapter<VistaRevista> {

    public AdaptadorVistaRevista(Context context, ArrayList<VistaRevista> data) {
        super(context, R.layout.lyitemvistarevista, data);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.lyitemvistarevista, null);

        TextView lbldate = (TextView)item.findViewById(R.id.lbldatepublished);
        lbldate.setText("Fecha de Publicación: " + getItem(position).getDate_published());

        TextView lblnumber = (TextView)item.findViewById(R.id.lblnumber);
        lblnumber.setText("Número: " + getItem(position).getNumber());

        TextView lblvolume = (TextView)item.findViewById(R.id.lblvolume);
        lblvolume.setText("Volumen: " + getItem(position).getVolume());

        TextView lblyear = (TextView)item.findViewById(R.id.lblyear);
        lblyear.setText("Año: " + getItem(position).getYear());

        ImageView imageView = (ImageView)item.findViewById(R.id.imgUsr);
        Glide.with(this.getContext()).load(getItem(position).getCover()).into(imageView);
        return(item);
    }
}
