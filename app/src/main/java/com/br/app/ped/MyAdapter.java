package com.br.app.ped;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Employe> {

    Context context;
    List<Employe> arrayListEmployes;


    public MyAdapter(@NonNull Context context, List<Employe> arrayListEmployes) {
        super(context, R.layout.custom_list_item,arrayListEmployes);

        this.context = context;
        this.arrayListEmployes = arrayListEmployes;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item,null,true);

        /*Acho que aqui tu usa pra puxar os dados para o textView*/
        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvName = view.findViewById(R.id.textName);

        tvID.setText(arrayListEmployes.get(position).getId());
        tvName.setText(arrayListEmployes.get(position).getName());



        return view;
    }
}
