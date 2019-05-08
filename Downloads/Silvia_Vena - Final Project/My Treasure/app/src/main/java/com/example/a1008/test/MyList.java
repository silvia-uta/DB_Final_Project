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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MyList extends AppCompatActivity {

    private String url = "jdbc:mysql://192.168.64.2:3306/FinalProject";
    private String user = "hi";
    private String passwd = "";

    private String[] ListNames;
    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list);

        // Task 1 display my lists
        new MyList.MyTask1().execute();

        // Add A List Button set up
        Button add = (Button) findViewById(R.id.addListBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addList = new Intent(MyList.this, NewList.class);
                startActivity(addList);
            }
        });

        list = (ListView) findViewById(R.id.list_data);

        // click on the item
        // FIX ME
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showTreasures = new Intent(getApplicationContext(),ListTreasures.class);
                showTreasures.putExtra("com.example.a1008.test.list_id", i);
                startActivity(showTreasures);
            }
        });
    }



    private class MyTask1 extends AsyncTask<Void,Void,Void> {
        private int i = 0;
        @Override
        protected Void doInBackground(Void... voids) {

            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url,user,passwd);

                Statement st = conn.createStatement();
                String sql = "select list_name from Lists order by list_id";

                final ResultSet rs = st.executeQuery(sql);

                //Get data size
                while(rs.next()){
                    i++;
                }
                //Set Array size
                ListNames = new String[i];
                i=0;

                //Move to first data
                rs.first();
                ListNames[i] = rs.getString(1);
                i++;

                //Store data to array
                while(rs.next()){
                    ListNames[i] = rs.getString(1);
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
            MyList.MyAdapter adapter = new MyList.MyAdapter(MyList.this);
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
            return ListNames.length;
        }

        @Override
        public Object getItem(int i) {
            return ListNames[i];
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

            aName.setText(ListNames[i]);
            return view;
        }
    }
}
