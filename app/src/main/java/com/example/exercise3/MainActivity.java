package com.example.exercise3;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    public EditText editText;
    public Button in,out,sp,json,xml;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        editText= (EditText) findViewById(R.id.show);
        in= (Button) findViewById(R.id.in);
        out = (Button) findViewById(R.id.out);
        json=findViewById(R.id.Json);
        xml=findViewById(R.id.XML);
        sp=findViewById(R.id.SP);
        in.setOnClickListener(this);
        out.setOnClickListener(this);
        sp.setOnClickListener(this);
        json.setOnClickListener(this);
        xml.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.in:
                String Text=editText.getText().toString();
                String environment= Environment.getExternalStorageState();
                if(Environment.MEDIA_MOUNTED.equals(environment))
                {
                    File sd_path=Environment.getExternalStorageDirectory();
                    File file=new File(sd_path,"lzm.txt");
                    String str=editText.getText().toString();
                    FileOutputStream file_out;
                    try{
                        //输出流输出到file文件
                        file_out=new FileOutputStream(file);
                        //输出流写入文字
                        file_out.write(str.getBytes());
                        //关闭输出流
                        file_out.close();
                        Toast.makeText(this,"写入成功",Toast.LENGTH_LONG).show();
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.out:
                environment = Environment.getExternalStorageState();
                if(Environment.MEDIA_MOUNTED.equals(environment))
                {
                    File sd_path=Environment.getExternalStorageDirectory();
                    File file=new File(sd_path,"lzm.txt");
                    FileInputStream file_in;
                    try{
                        file_in=new FileInputStream(file);
                        BufferedReader buff_read=new BufferedReader(new InputStreamReader(file_in));
                        String str=buff_read.readLine();
                        file_in.close();
                        editText.setText(str);
                        Toast.makeText(this,str,Toast.LENGTH_LONG).show();

                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.SP:
                Intent intent=new Intent(this,SharePreference.class);
                startActivity(intent);
                break;
            case  R.id.Json:
                Intent intent1=new Intent(this,JSON.class);
                startActivity(intent1);
                break;
            case  R.id.XML:
                Intent intent2=new Intent(this,XML.class);
                startActivity(intent2);
                break;
            default:break;
        }


    }
}
