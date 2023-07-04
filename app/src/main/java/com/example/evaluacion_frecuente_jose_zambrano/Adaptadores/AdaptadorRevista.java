package com.example.evaluacion_frecuente_jose_zambrano.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.evaluacion_frecuente_jose_zambrano.Modelos.Revista;
import com.example.evaluacion_frecuente_jose_zambrano.R;

import java.util.ArrayList;

public class AdaptadorRevista extends ArrayAdapter<Revista> {

    public AdaptadorRevista(Context context, ArrayList<Revista> data) {
        super(context,R.layout.lyitemrevista, data);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.lyitemrevista, null);

        TextView lblNombres = (TextView)item.findViewById(R.id.lblNombre);
        lblNombres.setText(getItem(position).getName());

        TextView lblNombreRev = (TextView)item.findViewById(R.id.lblNumRev);
        lblNombreRev.setText("N° " + getItem(position).getJournal_id());

        ImageView imageView = (ImageView)item.findViewById(R.id.imgUsr);
        Glide.with(this.getContext()).load(getItem(position).getPortada()).into(imageView);
        return(item);
    }
}

