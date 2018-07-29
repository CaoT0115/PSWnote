package com.example.caotiancheng.pwdnote;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class MyAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, Object>> listItem;
    private MyItemClickListener myItemClickListener;


    public MyAdapter(Context context, ArrayList<HashMap<String, Object>> listItem) {
        inflater = LayoutInflater.from(context);
        this.listItem = listItem;
    }



    class Viewholder extends RecyclerView.ViewHolder  {
        private TextView Title, Text, Password, Note;


        public Viewholder(View root) {
            super(root);
            Title = (TextView) root.findViewById(R.id.Platform);
            Text = (TextView) root.findViewById(R.id.UserID);
            Password=(TextView) root.findViewById(R.id.Password);
            Note = (TextView) root.findViewById(R.id.Note);

            root.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (myItemClickListener != null)
                        myItemClickListener .onItemClick(v,getPosition());
                }
            }
            );

        }

        public TextView getTitle() {
            return Title;
        }

        public TextView getText() {
            return Text;
        }


        public TextView getPassword() {
            return Password;
        }

        public TextView getNote() {
            return Note;
        }
    }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Viewholder(inflater.inflate(R.layout.list_cell, null));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            Viewholder vh = (Viewholder) holder;
            vh.Title.setText((String) listItem.get(position).get("Platform"));
            vh.Text.setText((String) listItem.get(position).get("UserID"));
            vh.Password.setText((String) listItem.get(position).get("Password"));
            vh.Note.setText((String) listItem.get(position).get("Note"));

        }

        @Override
        public int getItemCount() {
            return listItem.size();
        }

        public void setOnItemClickListener(MyItemClickListener listener){
        myItemClickListener = listener;
        }


}

