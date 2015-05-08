package com.example.victor.cra.adapter;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victor.cra.R;
import com.example.victor.cra.model.Nota;

public class NotaAdapter extends ArrayAdapter {

    int resource;
    List data;
    Context context;

    public NotaAdapter(Context context, int resource, List data) {
        super(context, resource, data);

        this.data = data;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NotaHolder holder = null;

        View row = convertView;

        if(row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);

            holder = new NotaHolder();

            holder.itemName = (TextView)row.findViewById(R.id.example_itemname);
            holder.button1=(Button)row.findViewById(R.id.swipe_button1);
            holder.button2=(Button)row.findViewById(R.id.swipe_button2);
            row.setTag(holder);
        } else {
            holder = (NotaHolder) row.getTag();
        }

        Nota itemdata = (Nota) data.get(position);
        holder.itemName.setText(itemdata.toString());

        holder.button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "Button 1 Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        holder.button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "Button 2 Clicked",Toast.LENGTH_SHORT).show();
            }
        });

        return row;

    }

    static class NotaHolder{
        TextView itemName;
        Button button1;
        Button button2;
    }

}
