package com.mac10_1.monsuivivehicule.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mac10_1.monsuivivehicule.R;
import com.mac10_1.monsuivivehicule.utils.Facture;
import com.mac10_1.monsuivivehicule.utils.MemoCar;

import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by mac10-1 on 08/08/2016.
 */
public class FactureCarAdapter extends ArrayAdapter<Facture> {

    public FactureCarAdapter(Context context, List<Facture> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.facture_item,parent, false);
        }

        MemoCarViewHolder viewHolder = (MemoCarViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MemoCarViewHolder();
            viewHolder.no = (TextView) convertView.findViewById(R.id.facture_no);
            viewHolder.date = (TextView) convertView.findViewById(R.id.facture_date);
            viewHolder.total = (TextView) convertView.findViewById(R.id.facture_total);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<MemoCar>
        Facture facture = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.no.setText(String.valueOf(facture.getNumFacture()));
        viewHolder.date.setText(String.valueOf(facture.getDate()));
        viewHolder.total.setText(String.valueOf(facture.getTotalFacture() + " DH"));
        //viewHolder.logo.setImageDrawable(new ColorDrawable(car.getLogo()));

        return convertView;
    }

    private class MemoCarViewHolder{
        public TextView no;
        public TextView date;
        public TextView total;

    }

}
