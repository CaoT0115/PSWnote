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

public class deleteActivity extends Activity {

    private EditText  Platform;
    private String pfvalue;
    private TextView Edit, Back;
    private ArrayList<HashMap<String, Object>> listItem;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);


        Platform = (EditText)findViewById(R.id.delete_Platform);
        Edit = (TextView)findViewById(R.id.delete_bt_delete);
        Back = (TextView)findViewById(R.id.delete_bt_back);

        Edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int position;

                pfvalue = Platform.getText().toString();

                listItem = listActivity.getListItem();

                for (position=0; position< listItem.size(); position++){
                    if(listItem.get(position).get("Platform").toString().equals(pfvalue)){

                        listItem.remove(position);

                        save();

                        break;
                    }

                }

                Toast.makeText(getApplicationContext(), pfvalue+" deleted", Toast.LENGTH_SHORT).show();

                deleteActivity.this.finish();

            }

        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteActivity.this.finish();
            }
        });

    }




    public void save(){
        data.saveInfo(this, "list", listItem);
    }
}
