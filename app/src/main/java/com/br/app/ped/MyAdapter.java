package com.br.app.ped;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Employe> {

    Context context;
    List<Employe> arrayListEmployes;


    public MyAdapter(@NonNull Context context, List<Employe> arrayListEmployes) {
        super(context, R.layout.custom_list_item,arrayListEmployes);

        this.context = context;
        arrayListEmployes = arrayListEmployes;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item)

        return super.getView(position, convertView, parent);
    }
}
