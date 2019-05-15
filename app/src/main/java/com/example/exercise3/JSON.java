package com.example.exercise3;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.LinkedList;

public class JSON extends AppCompatActivity implements View.OnClickListener {
    private TextView json_city_name,json_weaher_info;
    private Button json_beijing,json_shanghai,json_guangzhou;
    private ImageView json_weather_img;
    FileReader reader=null;
    LinkedList<City> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        init();
        ReadJson();
    }

    //这是一个方法，其中filename是放在assets中的本地JSON文件名
    public static String getJson(String fileName, Context context){
        //这个用来保存JSON格式字符串
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = context.getAssets();
        try{
            BufferedReader bf=new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line=bf.readLine())!=null){
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();

    }

    private LinkedList<City> ReadJson() {

        String result = getJson("weather.json",this);
        Type listType=new TypeToken<LinkedList<City>>(){}.getType();
        Gson gson=new Gson();
         cities=gson.fromJson(result,listType);
        for(City city:cities)
        {
            Log.e(null,city.toString());
        }
        return  cities;
    }


    private void init() {
        json_city_name= findViewById(R.id.json_city_name);
        json_weaher_info=findViewById(R.id.json_weather_info);
        json_weather_img=findViewById(R.id.json_weather_img);
        json_beijing=findViewById(R.id.json_beijing);
        json_guangzhou=findViewById(R.id.json_guangzhou);
        json_shanghai=findViewById(R.id.json_shanghai);

        json_beijing.setOnClickListener(this);
        json_guangzhou.setOnClickListener(this);
        json_shanghai.setOnClickListener(this);
        json_city_name.setText("北京");
        json_weaher_info.setText("晴 26℃/32℃ 98 3级");
        json_weather_img.setImageResource(R.drawable.sun);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.json_beijing:
                for(City city:cities)
                {
                    if(city.getId()==1)
                    {
                        json_city_name.setText(city.getName());
                        json_weaher_info.setText(city.toString());
                        json_weather_img.setImageResource(R.drawable.sun);
                    }
                }
                break;
            case R.id.json_shanghai:
                for(City city:cities)
                {
                    if(city.getId()==2)
                    {
                        json_city_name.setText(city.getName());
                        json_weaher_info.setText(city.toString());
                        json_weather_img.setImageResource(R.drawable.sun);
                    }
                }
                break;
            case R.id.json_guangzhou:
                for(City city:cities)
                {
                    if(city.getId()==3)
                    {
                        json_city_name.setText(city.getName());
                        json_weaher_info.setText(city.toString());
                        json_weather_img.setImageResource(R.drawable.sun);
                    }
                }
                break;
            default:break;

        }
    }
}
