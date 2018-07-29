package com.example.caotiancheng.pwdnote;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.service.autofill.SaveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class listActivity extends AppCompatActivity implements MyItemClickListener{

    private RecyclerView Rv;
    public static ArrayList<HashMap<String,Object>> listItem;
    private MyAdapter myAdapter;
    private SharedPreferences pref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        pref = this.getSharedPreferences("Item", Context.MODE_PRIVATE);

        initData();
        initView();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_bar_menu, menu);
        return true;
        }





    public void initData(){
        listItem = data.getInfo(this, "list");
        if(pref.getBoolean("firstaccess",true))
        {
            SharedPreferences.Editor editor = pref.edit();
        for (int i = 0; i < 30; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("Platform", "exampleplatform " + i );
            map.put("UserID", "exampleuser " + i);
            map.put("Password","examplepassword "+i);
            map.put("Note","note "+i);
            listItem.add(map);
        }
            editor.putBoolean("firstaccess",false);
            editor.commit();
        }
        save();
    }

    public static ArrayList<HashMap<String, Object>> getListItem(){return listItem;}

    public void initView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        Rv = (RecyclerView) findViewById(R.id.my_recycler_view);

        myAdapter = new MyAdapter(this,listItem);
        myAdapter.setOnItemClickListener(this);
        Rv.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        Rv.setLayoutManager(layoutManager);
        Rv.setHasFixedSize(true);
        Rv.setAdapter(myAdapter);

    }

    @Override
    public void onItemClick(View view, int postion) {
        System.out.println("select "+postion);
        Toast.makeText(this, (String)listItem.get(postion).get("UserID"), Toast.LENGTH_SHORT).show();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_search:
                EditText search;
                int toItem;
                for (toItem=0; toItem< listItem.size(); toItem++){
                    search = (EditText)findViewById(R.id.list_et_search);
                    if(listItem.get(toItem).get("Platform").toString().equals(search.getText().toString())){
                        MoveToPosition(((LinearLayoutManager)Rv.getLayoutManager()), toItem);
                        myAdapter.notifyDataSetChanged();
                        break;
                    }

                }
                //Toast.makeText(this, "search"+toItem, Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_add:
                Intent intent_add = new Intent(listActivity.this,addActivity.class);
                listActivity.this.startActivity(intent_add);
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_edit:
                Intent intent_edit = new Intent(listActivity.this,editActivity.class);
                listActivity.this.startActivity(intent_edit);
                Toast.makeText(this, "edit", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_delete:
                Intent intent_delete = new Intent(listActivity.this,deleteActivity.class);
                listActivity.this.startActivity(intent_delete);
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public static void MoveToPosition(LinearLayoutManager manager, int n) {
        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);
    }




    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }




    public void save(){
        data.saveInfo(this, "list", listItem);
    }
}


