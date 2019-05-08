package com.example.a1008.test;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class NewList extends AppCompatActivity {

    private String url = "jdbc:mysql://192.168.64.2:3306/FinalProject";
    private String user = "hi";
    private String passwd = "";
    private String inputText;

    EditText newListName;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        newListName = (EditText) findViewById(R.id.listNameText);

        saveBtn = (Button) findViewById(R.id.saveListNameBtn);
        saveBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view) {

                inputText = newListName.getText().toString();
                new NewList.MyTask().execute();
                Toast.makeText(NewList.this, inputText + " is added!", Toast.LENGTH_SHORT).show();
//                Intent addList = new Intent(NewList.this, MyList.class);
//                startActivity(addList);

            }

        });

    }

    private class MyTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url,user,passwd);

                String sql = "insert into Lists(list_name, date_created, date_modified) values(?, now(), now())";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, inputText);
                Log.d("test", inputText);

                ps.executeUpdate();


                ps.close();
                conn.close();

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void Result){
            super.onPostExecute(Result);
        }
    }
}
