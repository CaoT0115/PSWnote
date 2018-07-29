package com.example.caotiancheng.pwdnote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class resetActivity extends Activity {

    private EditText username, oldpwd, newpwd1, newpwd2;
    private String unvalue, opwdvalue, npwdvalue1, npwdvalue2;
    private TextView reset;
    private SharedPreferences sp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        username = (EditText)findViewById(R.id.reset_username);
        oldpwd = (EditText)findViewById(R.id.reset_old_password);
        newpwd1 = (EditText)findViewById(R.id.reset_new_password);
        newpwd2 = (EditText)findViewById(R.id.reset_confirm);
        reset = (TextView)findViewById(R.id.reset_bt_confirm);


        reset.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                unvalue = username.getText().toString();
                opwdvalue = oldpwd.getText().toString();
                npwdvalue1 = newpwd1.getText().toString();
                npwdvalue2 = newpwd2.getText().toString();

                if (opwdvalue.equals(sp.getString("PASSWORD", "")) && unvalue.equals(sp.getString("USER_NAME", ""))) {
                    if (npwdvalue1.equals(npwdvalue2) && npwdvalue1.length() != 0) {
                        Editor editor = sp.edit();
                        editor.putString("PASSWORD", npwdvalue1);
                        editor.commit();
                        Toast.makeText(resetActivity.this, "password change successfully", Toast.LENGTH_SHORT).show();

                        resetActivity.this.finish();
                    } else {
                        Toast.makeText(resetActivity.this, "different new password", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(resetActivity.this, "wrong old password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
