package com.example.a1008.test;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class SearchFilter extends AppCompatActivity {

    //    private String url = "jdbc:mysql://192.168.64.2:3306/FinalProject";
//    private String user = "hi";
//    private String passwd = "";
    private String[] museumList = new String[10];
    private String[] categoryList;
    private String museumQ;

    private String categoryQ;

    Button chooseMuseumBtn;
    Button chooseCategoryBtn;
    TextView museumChoicesTV;
    TextView categoryChoicesTV;
    TextView testQueryMuseum;
    TextView testQueryCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        chooseMuseumBtn = findViewById(R.id.addListBtn);
        chooseCategoryBtn = findViewById(R.id.categorySelectBtn);
        museumChoicesTV = findViewById(R.id.MuseumChoicesTextview);
        categoryChoicesTV = findViewById(R.id.CategoryChoicesTextview);

        testQueryMuseum = findViewById(R.id.testQueryMuseum);
        testQueryCategory = findViewById(R.id.testQueryCategory);

        chooseMuseumBtn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(SearchFilter.this);

                // Get the array of museum names from MyMuseum activity

                museumList = new String[]{"The Palace Museum", "Shanghai Museum", "Hunan Province Museum", "Henan Museum", "Shanxi History Museum", "Hubei Museum", "Zhejiang Province Museum", "Liaoning Province Museum", "Suzhou Museum", "Anhui Province Museum"};

                // Boolean array for initial selected items
                final boolean[] checkedMuseums = new boolean[10];
                for (int i = 0; i < 10; i++) {
                    checkedMuseums[i] = false;
                }

                // convert the museum array to list
                final List<String> museums = Arrays.asList(museumList);
                // set alert title
                builder.setTitle("Select Museums");
                // set icon
                builder.setIcon(R.drawable.ico);
                // set multichoice
                builder.setMultiChoiceItems(museumList, checkedMuseums, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean isClicked) {
                        // update current focused item's checked status
                        checkedMuseums[i] = isClicked;
                        // get the current focused item
                        String currentItem = museums.get(i);
                        // notify the current action
                        Toast.makeText(SearchFilter.this, currentItem + " " + isClicked, Toast.LENGTH_SHORT).show();
                    }
                });

                // set positive/yes button click listener
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    private String Mselection = "You selected: \n";

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int count = 0;
                        for (int k = 0; k < checkedMuseums.length; k++) {
                            boolean checked = checkedMuseums[k];
                            if (checked) {
                                count = count + 1;
                                int index = k + 1;
                                // set up query for the first selected item
                                if (count == 1) {
                                    Mselection = Mselection + museumList[k] + "\n";
                                    museumQ = "select museum_id from Museum where museum_id = " + index;
                                }
                                // set up following queries
                                else {
                                    Mselection = Mselection + museumList[k] + "\n";
                                    museumQ = museumQ + " AND museum_id = " + index;
                                }
                            }
                        }
                        if (count == 0) {
                            museumQ = "select museum_id from Museum";
                        }
                        museumChoicesTV.setText(Mselection);

                        //select museum_id from Museum where museum_id = 1 AND museum_id = 2...
                    }
                });

                // set neutral/cancel button click listener
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j = 0; j < 10; j++) {
                            checkedMuseums[j] = false;
                        }
                    }
                });

                AlertDialog dialog = builder.create();

                // show alert dialog
                dialog.show();

            }
        });

        chooseCategoryBtn.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SearchFilter.this);

                categoryList = new String[]{"Bronze", "Painting", "Calligraphy", "Ceramic", "Enamel", "Various Material", "Jade", "Lacquer", "Coins", "Sculpture", "Seal", "Carving", "Buddhist Statues", "Buddha Statues", "Others"};

                // Boolean array for initial selected items
                final boolean[] checkedCategory = new boolean[categoryList.length];
                for (int i = 0; i < categoryList.length; i++) {
                    checkedCategory[i] = false;
                }

                // convert the museum array to list
                final List<String> categories = Arrays.asList(categoryList);
                // set alert title
                builder.setTitle("Select Categories");
                // set icon
                builder.setIcon(R.drawable.ico);
                // set multichoice
                builder.setMultiChoiceItems(categoryList, checkedCategory, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean isClicked) {
                        // update current focused item's checked status
                        checkedCategory[i] = isClicked;
                        // get the current focused item
                        String currentItem = categories.get(i);
                        // notify the current action
                        Toast.makeText(SearchFilter.this, currentItem + " " + isClicked, Toast.LENGTH_SHORT).show();
                    }
                });

                // set positive/yes button click listener
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    private String Cselection = "You selected: \n";

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int count = 0;
                        for (int k = 0; k < checkedCategory.length; k++) {
                            boolean checked = checkedCategory[k];
                            if (checked) {
                                count = count + 1;
                                // set up query for the first selected item
                                if (count == 1) {
                                    Cselection = Cselection + categoryList[k] + "\n";
                                    categoryQ = "select * from Treasure where category = \"" + categoryList[k] + "\"";
                                }
                                // set up following queries
                                else {
                                    Cselection = Cselection + categoryList[k] + "\n";
                                    categoryQ = categoryQ + " AND category =  \"" + categoryList[k] + "\"";
                                }
                            }
                        }
                        if (count == 0) {
                            categoryQ = "select * from Treasure";
                        }
                        categoryChoicesTV.setText(Cselection);

                        // select * from Treasure where category = "Enamel" AND
                    }
                });

                // set neutral/cancel button click listener
                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for (int j = 0; j < categoryList.length; j++) {
                            checkedCategory[j] = false;
                        }
                    }
                });

                AlertDialog dialog = builder.create();

                // show alert dialog
                dialog.show();

            }
        });

        Button goBtn = (Button) findViewById(R.id.goBtn);
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {
                Intent showResultList = new Intent(SearchFilter.this, SearchResult.class);
                Bundle extras = new Bundle();
//                testQueryMuseum.setText(museumQ);
//                testQueryCategory.setText(categoryQ);
                extras.putString("Extra_museumQuery", museumQ);
                extras.putString("Extra_categoryQuery", categoryQ);
                showResultList.putExtras(extras);
                startActivity(showResultList);
            }
        });
    }
}
