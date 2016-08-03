package com.mac10_1.monsuivivehicule.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mac10_1.monsuivivehicule.R;

import java.util.List;

/**
 * Created by mac10-1 on 03/08/2016.
 */
public class CarAdapter extends ArrayAdapter<Car>{

    public CarAdapter(Context context, List<Car> cars){
        super(context, 0, cars);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_car_item,parent, false);
        }

        CarViewHolder viewHolder = (CarViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new CarViewHolder();
            viewHolder.modele = (TextView) convertView.findViewById(R.id.modele);
            viewHolder.immatriculation = (TextView) convertView.findViewById(R.id.immatriculation);
            viewHolder.logo = (ImageView) convertView.findViewById(R.id.logo);
            viewHolder.chassis = (TextView) convertView.findViewById(R.id.chassis);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Car>
        Car car = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.modele.setText(car.getModele());
        viewHolder.immatriculation.setText(car.getImmatriculation());
        viewHolder.chassis.setText(car.getNchassis());
        //viewHolder.logo.setImageDrawable(new ColorDrawable(car.getLogo()));

        return convertView;
    }

    private class CarViewHolder{
        public TextView immatriculation;
        public TextView modele;
        public TextView chassis;
        public ImageView logo;
    }
}
