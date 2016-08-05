package com.mac10_1.monsuivivehicule.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mac10_1.monsuivivehicule.R;

import java.util.List;

/**
 * Created by mac10-1 on 04/08/2016.
 */
public class MemoCarAdapter extends ArrayAdapter<MemoCar>{

    public MemoCarAdapter(Context context, List<MemoCar> memoCars){
        super(context, 0, memoCars);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.memo_car_item,parent, false);
        }

        MemoCarViewHolder viewHolder = (MemoCarViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MemoCarViewHolder();
            viewHolder.memo = (TextView) convertView.findViewById(R.id.memo);
            viewHolder.value = (TextView) convertView.findViewById(R.id.value);
            viewHolder.unit = (TextView) convertView.findViewById(R.id.unit);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<MemoCar>
        MemoCar memoCar = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.memo.setText(memoCar.getMemo());
        viewHolder.value.setText(String.valueOf(memoCar.getVal()));
        viewHolder.unit.setText(memoCar.getUnit());
        //viewHolder.logo.setImageDrawable(new ColorDrawable(car.getLogo()));

        return convertView;
    }

    private class MemoCarViewHolder{
        public TextView memo;
        public TextView value;
        public TextView unit;

    }
}
