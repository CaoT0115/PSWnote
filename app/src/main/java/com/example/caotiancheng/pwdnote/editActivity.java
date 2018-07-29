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

public class editActivity extends Activity {

    private EditText UserID, Password, Note, Platform;
    private String pfvalue, idvalue, pwdvalue, notevalue;
    private TextView Edit, Back;
    private ArrayList<HashMap<String, Object>> listItem;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        Platform = (EditText)findViewById(R.id.edit_Platform);
        UserID = (EditText)findViewById(R.id.edit_UserID);
        Password = (EditText)findViewById(R.id.edit_Password);
        Note = (EditText)findViewById(R.id.edit_Note);
        Edit = (TextView)findViewById(R.id.edit_bt_edit);
        Back = (TextView)findViewById(R.id.edit_bt_back);

        Edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int position;

                pfvalue = Platform.getText().toString();
                idvalue = UserID.getText().toString();
                pwdvalue = Password.getText().toString();
                notevalue = Note.getText().toString();

                listItem = listActivity.getListItem();

                for (position=0; position< listItem.size(); position++){
                    if(listItem.get(position).get("Platform").toString().equals(pfvalue)){
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("Platform", pfvalue);
                        map.put("UserID", idvalue);
                        map.put("Password",pwdvalue);
                        map.put("Note",notevalue);

                        listItem.set(position,map);

                        save();

                        break;
                    }

                }

                Toast.makeText(getApplicationContext(), pfvalue+" edited", Toast.LENGTH_SHORT).show();

                editActivity.this.finish();

            }

        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editActivity.this.finish();
            }
        });

    }



    public void save(){
        data.saveInfo(this, "list", listItem);
    }
}
