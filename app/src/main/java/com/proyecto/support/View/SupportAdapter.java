package com.proyecto.support.View;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.proyecto.support.R;

// 5 insertar heladoAdapter.HeladoPh

public class SupportAdapter extends RecyclerView.Adapter<SupportAdapter.HeladoPH> {

    // 3 crear list
    private List<HeladoModel> datos;

    //constructor
    SupportAdapter(List<HeladoModel>d){
        this.datos =d ;

    }
 // 9 control o insertra oncreateholder


    @NonNull
    @Override
    public HeladoPH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itemmolde,viewGroup, false);
      HeladoPH ph =new HeladoPH(v);

        return ph;
    }

    //6
    // eliminmar RecyclerView.ViewHolder   y elimimnar
        @Override
    public void onBindViewHolder( HeladoPH holder, int position) {
       // 8
            holder.im.setImageResource(datos.get(position).foto);
            //holder.su.setText(datos.get(position).nombre);
            holder.ti.setText(datos.get(position).nombre);

    }

    @Override
    public int getItemCount() {
        //7 retornar datos
        return datos.size();
    }

    //4 creat clase
    public static class HeladoPH  extends  RecyclerView.ViewHolder{

         CardView cv;
         ImageView im;
         TextView ti;
         TextView su;
         HeladoPH(View itemView){

            super(itemView);
            cv=itemView.findViewById(R.id.cv);
            im=itemView.findViewById(R.id.imgfoto);
            ti=itemView.findViewById(R.id.textTitulo);




        }

    }

}
