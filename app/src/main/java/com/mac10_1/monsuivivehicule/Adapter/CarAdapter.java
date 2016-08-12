package com.mac10_1.monsuivivehicule.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mac10_1.monsuivivehicule.Activity.InfoCarActivity;
import com.mac10_1.monsuivivehicule.R;
import com.mac10_1.monsuivivehicule.utils.Car;

import java.util.List;

/**
 * Created by mac10-1 on 03/08/2016.
 */
public class CarAdapter extends ArrayAdapter<Car> implements AdapterView.OnItemClickListener {

    Context context;
    List<Car> cars;
    public CarAdapter(Context context, List<Car> cars){
        super(context, 0, cars);
        this.context = context;
        this.cars = cars;
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

        String marque = car.getMarque().toLowerCase();
        marque = marque.replaceAll(" ", "_");
        String urilogo = "@drawable/"+ marque;
        Log.d("URI", urilogo);
        int imageResource = getContext().getResources().getIdentifier(urilogo, null, getContext().getPackageName());
        if(imageResource != 0x0) {
            Drawable res = getContext().getResources().getDrawable(imageResource);
            viewHolder.logo.setImageDrawable(res);
        }else {
            viewHolder.modele.setText(car.getMarque() +" "+ car.getModele());
            viewHolder.logo.setImageResource(R.drawable.nologo);
        }

        return convertView;
    }

    private class CarViewHolder{
        public TextView immatriculation;
        public TextView modele;
        public TextView chassis;
        public ImageView logo;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Car car = cars.get(position);
        Intent intent = new Intent(context, InfoCarActivity.class);
        intent.putExtra("Car", car);
        context.startActivity(intent);
    }
}
