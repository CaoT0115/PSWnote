package com.example.caotiancheng.pwdnote;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {

    private EditText username, password;
    private CheckBox remember;
    private TextView login, reset;
    private String unvalue, pwvalue;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        remember = (CheckBox)findViewById(R.id.remember);
        login = (TextView)findViewById(R.id.main_login);
        reset = (TextView)findViewById(R.id.reset);


        if(sp.getBoolean("firstaccess",true))
        {
            Editor editor = sp.edit();
            editor.putString("USER_NAME","Cao");
            editor.putString("PASSWORD","123");
            editor.putBoolean("firstaccess",false);
            editor.commit();
        }


        if(sp.getBoolean("ISCHECK", false))
        {

            remember.setChecked(true);
            username.setText(sp.getString("USER_NAME", ""));
            password.setText(sp.getString("PASSWORD", ""));
        }


        login.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                unvalue = username.getText().toString();
                pwvalue = password.getText().toString();

                if(unvalue.equals(sp.getString("USER_NAME", ""))&&pwvalue.equals(sp.getString("PASSWORD", "")))
                {
                    Toast.makeText(loginActivity.this,"登录成功", Toast.LENGTH_SHORT).show();

                    if(remember.isChecked())
                    {

                        Editor editor = sp.edit();
                        editor.putBoolean("ISCHECK",true);
                        editor.putString("USER_NAME", unvalue);
                        editor.putString("PASSWORD",pwvalue);
                        editor.commit();
                    }

                    Intent intent = new Intent(loginActivity.this,listActivity.class);
                    loginActivity.this.startActivity(intent);

                }else{

                    Toast.makeText(loginActivity.this,"Wrong username or password, try again", Toast.LENGTH_LONG).show();
                }

            }
        });

        reset.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {

                    Intent intent = new Intent(loginActivity.this,resetActivity.class);
                    loginActivity.this.startActivity(intent);

            }
        });


        remember.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
                if (remember.isChecked()) {

                    System.out.println("remember selected");
                    sp.edit().putBoolean("ISCHECK", true).commit();

                }else {

                    System.out.println("remember UNselected");
                    sp.edit().putBoolean("ISCHECK", false).commit();

                }

            }
        });


    }
}
