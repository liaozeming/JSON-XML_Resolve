package com.example.exercise3;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class SharePreference extends AppCompatActivity implements View.OnClickListener {

    private EditText IDName,Password;
    private Button register,show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        init();
    }

    private void init() {
        IDName = findViewById(R.id.IDName);
        Password=findViewById(R.id.Password);
        register=findViewById(R.id.register);
        show=findViewById(R.id.Show);

        register.setOnClickListener(this);
        show.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.register:
                String ID_str=IDName.getText().toString();
                String Password_str=Password.getText().toString();
                boolean flag=save_user_msg(SharePreference.this,ID_str,Password_str);
                if(flag && ID_str!=null && Password_str!=null)
                {
                    Toast.makeText(SharePreference.this,"保存成功",Toast.LENGTH_SHORT).show();
                }
                else  Toast.makeText(SharePreference.this,"保存失败",Toast.LENGTH_SHORT).show();
                break;
            case  R.id.Show:
                Map<String,String> user=getuserMsg(SharePreference.this);
                if(user!=null)
                {
                    String username=user.get("username");
                    String password=user.get("password");
                    Toast.makeText(SharePreference.this,"用户名为"+username+"\n"+"密码为:"+password,Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SharePreference.this,"用户名为\n"+"密码为:",Toast.LENGTH_SHORT).show();
                    Log.e(null,"错误");
                }
                break;
        }

    }

    private Map<String,String> getuserMsg(SharePreference sharePreference) {
        SharedPreferences sharedPreferences=sharePreference.getSharedPreferences("user_msg",MODE_PRIVATE);
       String username=sharedPreferences.getString("username",null);
       String password=sharedPreferences.getString("password",null);
       Map<String,String> user=new HashMap<String, String>();
       user.put("username",username);
       user.put("password",password);
       return  user;

    }


    private boolean save_user_msg(SharePreference sharePreference, String id_str, String password_str) {
        SharedPreferences sharedPreferences=sharePreference.getSharedPreferences("user_msg",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString("username",id_str);
        editor.putString("password",password_str);
        editor.commit();
        return  true;
    }
}
