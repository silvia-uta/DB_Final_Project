package com.example.a1008.test;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class ListTreasures extends AppCompatActivity {

    private String url = "jdbc:mysql://192.168.64.2:3306/FinalProject";
    private String user = "hi";
    private String passwd = "";
    private int list_id;
    private int[] TreasureIDs= new int[1];
    private String[] TreasureNames=new String[1];


    private ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_treasures);

        Intent in = getIntent();
        list_id = in.getIntExtra("com.example.a1008.test.list_id", 0);

        //Log.d("test", museumQ);
        //Log.d("test", categoryQ);

        new ListTreasures.MyTask().execute();

        list = (ListView) findViewById(R.id.list_treasure);

        // click on the item
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent showTreasureDetail = new Intent(getApplicationContext(), TreasureDetail.class);
                showTreasureDetail.putExtra("com.example.a1008.test.tid", TreasureIDs[i]);
                startActivity(showTreasureDetail);
            }
        });
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {

        private int i = 0;

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, user, passwd);

                Statement st = conn.createStatement();
                //String sql = "select T.treasure_id, T.treasure_name from Treasure as T where category = \"Seal\" ";
                String sql = "select Treasure.treasure_id, Treasure.treasure_name from AddTo, Treasure where AddTo.treasure_id = Treasure.treasure_id AND " +
                        "list_id = " + list_id ;
                Log.d("test",sql);


                final ResultSet rs = st.executeQuery(sql);

                //Get data size
                while (rs.next()) {
                    i++;
                }
                Log.i("test", "message "+i);
                TreasureNames[0] = "No treasure has been added yet.";

                if (i > 0) {
                    Log.d("test", "HIIIIIII!!!!!");
                    //Set Array size
                    TreasureIDs = new int[i];
                    TreasureNames = new String[i];
                    i = 0;

                    //Move to first data
                    rs.first();
                    TreasureIDs[i] = rs.getInt(1);
                    TreasureNames[i] = rs.getString(2);
                    i++;

                    //Store data to array
                    while (rs.next()) {
                        TreasureIDs[i] = rs.getInt(1);
                        TreasureNames[i] = rs.getString(2);
                        i++;
                    }

                    conn.close();
                }

                Log.i("test", "HIIIIIII!!!!!" + TreasureNames[0]);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void Result) {
            ListTreasures.MyAdapter adapter = new ListTreasures.MyAdapter(ListTreasures.this);
            list.setAdapter(adapter);
            super.onPostExecute(Result);
        }

    }

    public class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public MyAdapter(Context c) {
            inflater = LayoutInflater.from(c);
        }

        @Override
        public int getCount() {
            return TreasureNames.length;
        }

        @Override
        public Object getItem(int i) {
            return TreasureNames[i];
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = inflater.inflate(R.layout.layout_adapter, null);
            TextView aName;

            aName = (TextView) view.findViewById(R.id.text_name);

            aName.setText(TreasureNames[i]);
            return view;
        }
    }
}
