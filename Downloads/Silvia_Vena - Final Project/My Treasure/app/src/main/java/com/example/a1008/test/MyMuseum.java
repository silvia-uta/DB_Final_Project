package com.example.a1008.test;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyMuseum extends AppCompatActivity {

    private String url = "jdbc:mysql://192.168.64.2:3306/FinalProject";
    private String user = "hi";
    private String passwd = "";

    private String[] MuseumNames;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_museum);

        new MyMuseum.MyTask().execute();

        list = (ListView) findViewById(R.id.list_data);

        // click on the item
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showMuseumDetail = new Intent(getApplicationContext(),MuseumDetail.class);
                showMuseumDetail.putExtra("com.example.a1008.test.MUSEUM_ID", i);
                startActivity(showMuseumDetail);
            }
        });
    }

    private class MyTask extends AsyncTask<Void,Void,Void> {
        private int i = 0;
        @Override
        protected Void doInBackground(Void... voids) {

            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url,user,passwd);

                Statement st = conn.createStatement();
                String sql = "select museum_name from Museum order by museum_id";

                final ResultSet rs = st.executeQuery(sql);

                //Get data size
                while(rs.next()){
                    i++;
                }
                //Set Array size
                MuseumNames = new String[i];
                i=0;

                //Move to first data
                rs.first();
                MuseumNames[i] = rs.getString(1);
                i++;

                //Store data to array
                while(rs.next()){
                    MuseumNames[i] = rs.getString(1);
                    i++;
                }

                conn.close();

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void Result){
            MyMuseum.MyAdapter adapter = new MyMuseum.MyAdapter(MyMuseum.this);
            list.setAdapter(adapter);
            super.onPostExecute(Result);
        }
    }



    public class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        public MyAdapter(Context c){
            inflater = LayoutInflater.from(c);
        }
        @Override
        public int getCount() {
            return MuseumNames.length;
        }

        @Override
        public Object getItem(int i) {
            return MuseumNames[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflater.inflate(R.layout.layout_adapter,null);
            TextView aName;

            aName = (TextView) view.findViewById(R.id.text_name);

            aName.setText(MuseumNames[i]);
            return view;
        }
    }




}
