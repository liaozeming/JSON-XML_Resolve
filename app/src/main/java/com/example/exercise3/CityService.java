package com.example.exercise3;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CityService {
    public static List<City> getCitys(InputStream xml) throws IOException, XmlPullParserException
    {
        List<City> citys=null;
        City city=null;
        XmlPullParser pullParser= Xml.newPullParser();
        try {
            pullParser.setInput(xml,"UTF-8");
            int event=pullParser.getEventType();
            while (event!=XmlPullParser.END_DOCUMENT)
            {
                switch (event)
                {
                    case  XmlPullParser.START_DOCUMENT:
                        citys=new ArrayList<City>();
                        break;
                    case  XmlPullParser.START_TAG:
                        if("city".equals(pullParser.getName()))
                        {
                            int id=new Integer(pullParser.getAttributeValue(0));
                            city=new City();
                            city.setId(id);
                        }
                        if("temp".equals(pullParser.getName()))
                        {
                            String temp=pullParser.nextText();
                            city.setTemp(temp);
                        }
                        if("weather".equals(pullParser.getName()))
                        {
                            String weather=pullParser.nextText();
                            city.setWeather(weather);
                        }
                        if("name".equals(pullParser.getName()))
                        {
                            String name=pullParser.nextText();
                            city.setName(name);
                        }
                        if("pm".equals(pullParser.getName()))
                        {
                            String pm=pullParser.nextText();
                            city.setPm(pm);
                        }
                        if("wind".equals(pullParser.getName()))
                        {
                            String wind=pullParser.nextText();
                            city.setWind(wind);
                        }
                        break;
                        case XmlPullParser.END_TAG:
                            if("city".equals(pullParser.getName()))
                            {
                                citys.add(city);
                                city=null;
                            }
                            break;
                }
                event=pullParser.next();
            }
        }catch (XmlPullParserException e)
        {
            e.printStackTrace();
        }
        return  citys;
    }

}
