package com.example.exercise3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class XML extends AppCompatActivity implements View.OnClickListener {

    private TextView xml_city_name,xml_weaher_info;
    private Button xml_beijing,xml_shanghai,xml_guangzhou;
    private ImageView xml_weather_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);
        init();

    }

    private void init() {
        xml_city_name= findViewById(R.id.xml_city_name);
        xml_weaher_info=findViewById(R.id.xml_weather_info);
        xml_weather_img=findViewById(R.id.xml_weather_img);
        xml_beijing=(Button)findViewById(R.id.xml_beijing);
        xml_guangzhou=(Button)findViewById(R.id.xml_guangzhou);
        xml_shanghai=(Button)findViewById(R.id.xml_shanghai);

        xml_beijing.setOnClickListener(this);
        xml_guangzhou.setOnClickListener(this);
        xml_shanghai.setOnClickListener(this);
        xml_city_name.setText("北京");
        xml_weaher_info.setText("晴 26℃/32℃ 98 3级");
        xml_weather_img.setImageResource(R.drawable.sun);
    }

    @Override
    public void onClick(View view) {
        InputStream xml=this.getClass().getClassLoader().getResourceAsStream("assets/weather.xml");
        List<City> cities=null;
        try{
            cities=CityService.getCitys(xml);
        }catch (IOException e)
        {
            e.printStackTrace();
        }catch (XmlPullParserException e)
        {
            e.printStackTrace();
        }
        switch (view.getId())
        {
            case R.id.xml_beijing:
                for(City city:cities)
                {
                    if(city.getId()==2)
                    {
                        xml_city_name.setText(city.getName());
                        xml_weaher_info.setText(city.toString());
                        xml_weather_img.setImageResource(R.drawable.sun);
                    }
                }
                break;
            case R.id.xml_shanghai:
                for(City city:cities)
                {
                    if(city.getId()==1)
                    {
                        xml_city_name.setText(city.getName());
                        xml_weaher_info.setText(city.toString());
                        xml_weather_img.setImageResource(R.drawable.cloud_sun);
                    }
                }
                Log.e(null,"test");
                break;
            case R.id.xml_guangzhou:
                for(City city:cities)
                {
                    if(city.getId()==3)
                    {
                        xml_city_name.setText(city.getName());
                        xml_weaher_info.setText(city.toString());
                        xml_weather_img.setImageResource(R.drawable.cloud_sun);
                    }
                }
                Log.e(null,"test");
                break;
            default:break;

        }
    }
}
