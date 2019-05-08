package com.example.a1008.test;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MuseumDetail extends AppCompatActivity {

    private String url = "jdbc:mysql://192.168.64.2:3306/FinalProject";
    private String user = "hi";
    private String passwd = "";
    private int index;
    private String museum_name;
    private String street_adress;
    private String district;
    private String city;
    private String zipcode;
    private String province;
    private String website;
    private String description;
    private String year_founded;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_detail);

        Intent in = getIntent();
        index = in.getIntExtra("com.example.a1008.test.MUSEUM_ID", -1);

        if (index > -1){
            index = index+1;
            // Task1 get museum info
            new MuseumDetail.MyTask1().execute();
            // Task2 get centerpiece name
            new MuseumDetail.MyTask2().execute();

        }
    }

    private class MyTask1 extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url,user,passwd);

                String sql = "select museum_name, street_adress, district, city, zipcode, province, website, description, year_founded from Museum where museum_id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, index);

                final ResultSet rs = ps.executeQuery();

                //Store museum info to corresponding string
                while(rs.next()){
                    museum_name = rs.getString(1);
                    street_adress = rs.getString(2);
                    district = rs.getString(3);
                    city = rs.getString(4);
                    zipcode = rs.getString(5);
                    province = rs.getString(6);
                    website = rs.getString(7);
                    description = rs.getString(8);
                    year_founded = rs.getString(9);
                }

                address = street_adress + ", " + district + ", " + city + ", " + province + ", " + "China, " + zipcode;

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
            TextView name, addr, year, descript;
            name = (TextView) findViewById(R.id.treasure_name_textview);
            addr = (TextView) findViewById(R.id.era_textview);
            year = (TextView) findViewById(R.id.category_textview);
            descript = (TextView) findViewById(R.id.descript_textview);

            name.setText(museum_name);
            addr.setText(address);
            year.setText(year_founded);
            descript.setText(description);
            descript.setMovementMethod(new ScrollingMovementMethod());

            // Set up go to official website button
            Button site_btn = (Button) findViewById(R.id.treasure_site_btn);
            site_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri webaddress = Uri.parse(website);

                    Intent gotoWebsite = new Intent(Intent.ACTION_VIEW, webaddress);
                    if (gotoWebsite.resolveActivity(getPackageManager()) != null){
                        startActivity(gotoWebsite);
                    }
                }
            });

            // Set up Search All Treasure button
            Button search_all_btn = (Button) findViewById(R.id.seeAll_btn);
            search_all_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent showResultList = new Intent(MuseumDetail.this, SearchResult.class);
                    Bundle extras = new Bundle();
//                testQueryMuseum.setText(museumQ);
//                testQueryCategory.setText(categoryQ);
                    extras.putString("Extra_museumQuery", "select museum_id from Museum where museum_id = " + index);
                    extras.putString("Extra_categoryQuery", "select * from Treasure");
                    showResultList.putExtras(extras);
                    startActivity(showResultList);
                }
            });

            super.onPostExecute(Result);

        }
    }

    private class MyTask2 extends AsyncTask<Void,Void,Void> {

        String[] center = new String[3];
        int[] id = new int[3];

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url,user,passwd);

                String sql = "select Treasure.treasure_name, Treasure.treasure_id  from Treasure join Contain on Contain.treasure_id = Treasure.treasure_id where is_centerpiece is true and Contain.museum_id = ?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, index);

                final ResultSet rs = ps.executeQuery();

                int i = 0;

                //Store museum info to corresponding string
                while(rs.next()){
                    center[i] = rs.getString(1);
                    id[i] = rs.getInt(2);
                    i++;
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
            TextView c1, c2, c3;
            c1 = (TextView) findViewById(R.id.c1_textview);
            c2 = (TextView) findViewById(R.id.c2_textview);
            c3 = (TextView) findViewById(R.id.c3_textview);

            c1.setText(center[0]);
            c2.setText(center[1]);
            c3.setText(center[2]);

            c1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent showTreasureDetail = new Intent(MuseumDetail.this, TreasureDetail.class);
                    showTreasureDetail.putExtra("com.example.a1008.test.tid", id[0]);
                    startActivity(showTreasureDetail);
                }
            });

            c2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent showTreasureDetail = new Intent(MuseumDetail.this, TreasureDetail.class);
                    showTreasureDetail.putExtra("com.example.a1008.test.tid", id[1]);
                    startActivity(showTreasureDetail);
                }
            });

            c3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent showTreasureDetail = new Intent(MuseumDetail.this, TreasureDetail.class);
                    showTreasureDetail.putExtra("com.example.a1008.test.tid", id[2]);
                    startActivity(showTreasureDetail);
                }
            });

            super.onPostExecute(Result);
        }
    }

}
