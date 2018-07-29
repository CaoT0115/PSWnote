package com.example.caotiancheng.pwdnote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class addActivity extends Activity {

    private EditText UserID, Password, Note, Platform;
    private String pfvalue, idvalue, pwdvalue, notevalue;
    private TextView Add;
    private ArrayList<HashMap<String, Object>> listItem;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        Platform = (EditText)findViewById(R.id.add_Platform);
        UserID = (EditText)findViewById(R.id.add_UserID);
        Password = (EditText)findViewById(R.id.add_Password);
        Note = (EditText)findViewById(R.id.add_Note);
        Add = (TextView)findViewById(R.id.add_bt_add);

        Add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                pfvalue = Platform.getText().toString();
                idvalue = UserID.getText().toString();
                pwdvalue = Password.getText().toString();
                notevalue = Note.getText().toString();

                listItem = listActivity.getListItem();

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("Platform", pfvalue);
                map.put("UserID", idvalue);
                map.put("Password",pwdvalue);
                map.put("Note",notevalue);
                listItem.add(map);


                save();

                Toast.makeText(getApplicationContext(), pfvalue+" added", Toast.LENGTH_SHORT).show();


                addActivity.this.finish();

            }


        });

    }




    public void save(){
        data.saveInfo(this, "list", listItem);
    }
}
