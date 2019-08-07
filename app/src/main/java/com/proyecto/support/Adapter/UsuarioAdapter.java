package com.proyecto.support.Adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.proyecto.support.Bean.UsuarioBean;

import java.util.ArrayList;

public class UsuarioAdapter extends ArrayAdapter<UsuarioBean> {
    int idmolde;
    ArrayList<UsuarioBean> datos;
    public UsuarioAdapter(Context ctx, int res, ArrayList<UsuarioBean> data) {
        super(ctx, res, data);
        this.idmolde = res;
        this.datos = data;
    }


    @Override
    public View getView(int pos, @NonNull View cView, @NonNull ViewGroup parent) {
        viewHolder holder;
        if (cView == null){
            cView = LayoutInflater.from(getContext()).inflate(this.idmolde,parent,false);

        }
        return cView;
    }
    static class viewHolder{
        ImageView avatar;
        TextView nombre;
        TextView descri;
    }
}
