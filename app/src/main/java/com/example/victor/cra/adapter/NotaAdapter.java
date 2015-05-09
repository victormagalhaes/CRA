package com.example.victor.cra.adapter;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.victor.cra.R;
import com.example.victor.cra.app.App;
import com.example.victor.cra.model.Nota;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NotaAdapter extends ArrayAdapter {

    int resource;
    List data;
    Context context;
    View row = null;

    public NotaAdapter(Context context, int resource, List data) {
        super(context, resource, data);

        this.data = data;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NotaHolder holder = null;

        row = convertView;

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

        final Nota itemdata = (Nota) data.get(position);
        holder.itemName.setText(itemdata.toString());

        holder.button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            }
        });

        holder.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                App.getRestClient().deleteNota(itemdata.getId(), new Callback() {
                    @Override
                    public void success(Object o, Response response) {
                        View parent = (View) v.getParent().getParent();
                        parent.setVisibility(View.GONE);
                        remove(parent);
                        Toast.makeText(context, "Feito", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("Erro Retrofit", error.toString());
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
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
