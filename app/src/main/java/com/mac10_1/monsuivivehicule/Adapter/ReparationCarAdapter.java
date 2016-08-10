package com.mac10_1.monsuivivehicule.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mac10_1.monsuivivehicule.R;
import com.mac10_1.monsuivivehicule.utils.MemoCar;
import com.mac10_1.monsuivivehicule.utils.Reparation;

import java.util.List;

/**
 * Created by mac10-1 on 09/08/2016.
 */
public class ReparationCarAdapter extends ArrayAdapter<Reparation> {

    public ReparationCarAdapter(Context context, List<Reparation> reparationList){
        super(context, 0, reparationList);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.reparation_car_item,parent, false);
        }

        ReparationCarViewHolder viewHolder = (ReparationCarViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ReparationCarViewHolder();
            viewHolder.reparation_name = (TextView) convertView.findViewById(R.id.reparation_txt);
            viewHolder.reparation_cout = (TextView) convertView.findViewById(R.id.reparation_cout);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<MemoCar>
        Reparation reparation = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.reparation_name.setText(reparation.getNom());
        viewHolder.reparation_cout.setText(String.valueOf(reparation.getCout()));

        return convertView;
    }

    private class ReparationCarViewHolder{
        public TextView reparation_name;
        public TextView reparation_cout;
    }
}
