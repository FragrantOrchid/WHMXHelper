package cn.org.orchid.whmx.Adapter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cn.org.orchid.whmx.R;

public class PrimaryMenuAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;
    public PrimaryMenuAdapter(Context context,  String[] values) {
        super(context, R.layout.primary_menu_item_layout,values);
        this.context = context;
        this.values = values;
    }
    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("ViewHolder") View rowView = inflater.inflate(R.layout.primary_menu_item_layout,parent,false);
        TextView textView = rowView.findViewById(R.id.primary_menu_item);
        textView.setText(values[position]);
        return rowView;
    }
}
