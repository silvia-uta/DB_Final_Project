package com.example.a1008.test;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TreasureDetail extends AppCompatActivity {

    private String url = "jdbc:mysql://192.168.64.2:3306/FinalProject";
    private String user = "hi";
    private String passwd = "";
    private int tid;
    //private String tname;
    private String treasure_name;
    private String treasure_link;
    private String category;
    private String img;
    private String era;
    private String creator;
    private int museum_id;
    private String museum_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treasure_detail);

        Intent in = getIntent();
        tid = in.getIntExtra("com.example.a1008.test.tid", 0);
        Log.d("test", String.valueOf(tid));

        if (tid > 0) {
            // Task1 setup treasure info
            new TreasureDetail.MyTask1().execute();

            // Task2 setup museum name of the treasure
            new TreasureDetail.MyTask2().execute();

            // add to list btn
            Button addtolist = (Button) findViewById(R.id.addBtn);
            addtolist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent add = new Intent(getApplicationContext(),AddToList.class);
                    add.putExtra("com.example.a1008.test.ADD_TID", tid);
                    startActivity(add);
                }
            });


        }
    }

    private class MyTask1 extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url,user,passwd);

                String sql = "select treasure_name, treasure_link, category, img, era, creator from Treasure where treasure_id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, tid);

                final ResultSet rs = ps.executeQuery();

                //Store museum info to corresponding string
                while(rs.next()){
                    treasure_name = rs.getString(1);
                    treasure_link = rs.getString(2);
                    category = rs.getString(3);
                    img = rs.getString(4);
                    era = rs.getString(5);
                    creator = rs.getString(6);
                }

                ps.close();
                conn.close();

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void Result){
            // Set text views
            TextView name, kind, time, writer;
            name = (TextView) findViewById(R.id.treasure_name_textview);
            kind = (TextView) findViewById(R.id.category_textview);
            time = (TextView) findViewById(R.id.era_textview);
            writer = (TextView) findViewById(R.id.creator_textview);

            name.setText(treasure_name);
            kind.setText("Category: " + category);
            time.setText("Era: " + era);
            writer.setText("Creator: " + creator);

            // Go to treasure's official website button
            Button site_btn = (Button) findViewById(R.id.treasure_site_btn);
            site_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri webaddress = Uri.parse(treasure_link);

                    Intent gotoWebsite = new Intent(Intent.ACTION_VIEW, webaddress);
                    if (gotoWebsite.resolveActivity(getPackageManager()) != null){
                        startActivity(gotoWebsite);
                    }
                }
            });

            // setup img
            ImageView TreasureImg = (ImageView) findViewById(R.id.treasure_img);
            if (img != null) {
                Picasso.with(TreasureDetail.this).setLoggingEnabled(true);
                Uri img_addr = Uri.parse(img);
                Picasso.with(TreasureDetail.this).load(img_addr).into(TreasureImg);

                TreasureImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Uri webaddress = Uri.parse(img);

                        Intent gotoWebsite = new Intent(Intent.ACTION_VIEW, webaddress);
                        if (gotoWebsite.resolveActivity(getPackageManager()) != null) {
                            startActivity(gotoWebsite);
                        }
                    }
                });
            }
            else {
                int pic = R.drawable.img_error;
                TreasureImg.setImageResource(pic);
            }

            super.onPostExecute(Result);

        }

    }

    private class MyTask2 extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url,user,passwd);

                String sql = "select Museum.museum_id, museum_name from Museum join Contain on Contain.museum_id = Museum.museum_id where treasure_id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, tid);

                final ResultSet rs = ps.executeQuery();

                //Store museum info to corresponding string
                while(rs.next()){
                    museum_id = rs.getInt(1);
                    museum_name = rs.getString(2);
                }
                Log.d("test","Museum: "+museum_id);
                ps.close();
                conn.close();

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void Result){
            // Set text views
            final TextView museum;
            museum = (TextView) findViewById(R.id.MuseumNameTextView);
            museum.setText("From: " + museum_name);
            museum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent showMuseumDetail = new Intent(TreasureDetail.this, MuseumDetail.class);
                    showMuseumDetail.putExtra("com.example.a1008.test.MUSEUM_ID", museum_id-1);
                    startActivity(showMuseumDetail);
                }
            });

            super.onPostExecute(Result);

        }

    }



}
