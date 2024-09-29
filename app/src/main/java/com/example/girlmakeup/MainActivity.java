/*

This app search for makeup info on the internet
https://makeup-api.herokuapp.com/api/v1/products.json
we hook up the appropriate json object to get a response.
Author: Andy Duverneau

 */
package com.example.girlmakeup;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import android.view.autofill.AutofillId;
import android.view.autofill.AutofillManager;
import android.view.autofill.AutofillValue;
import android.widget.CheckBox;
import android.widget.HorizontalScrollView;

import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Consumer;

/*

  This class main activity handles every aspect of the program
   our goal was to make the program efficient. we make the best use of our variables;
   we use less loop and statements so that our log time can be less. And also there was no need to use
   them since this program was intended to be small.
 */
public class MainActivity extends AppCompatActivity {

    /*
    We create different field to handle our references
    such as check box, imageview, horizontal scroll view,
    list for json object, the brand name , price, image link, and a search view

     */
    String url;
    ImageView imageView, imageView2, imageView3, imageView4;

    CheckBox checkBox;
    private HorizontalScrollView hori, horizontalScrollView2, horizontalScrollView3, horizontalScrollView4;

    TextView text_descrption, text_product_name,text_descrption2, text_product_name2,text_product_name3
            ,text_descrption3,text_descrption4,text_product_name4;

    List<JSONObject> jsonObjectList;

    String productName;
    String product_link;
    String product_detail;
    String productprice;
    String imageLink;

    SearchView searchView;
    Context context = this;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /*
    here we get a set of references

    */
        setContentView(R.layout.activity_main);
        searchView = findViewById(R.id.search);

        imageView = findViewById(R.id.imageView3);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView8);
        imageView4 = findViewById(R.id.imageView9);

        checkBox = findViewById(R.id.checkBox2);

        hori = findViewById(R.id.hori2);
        horizontalScrollView2 = findViewById(R.id.hori3);
        horizontalScrollView3 =  findViewById(R.id.hori);
        horizontalScrollView4 =  findViewById(R.id.hori4);

        text_product_name = findViewById(R.id.textView4);
        text_product_name2 = findViewById(R.id.textView5);
        text_product_name3 = findViewById(R.id.textView2);
        text_product_name4 = findViewById(R.id.textView11);

        text_descrption = findViewById(R.id.textView);
        text_descrption2 = findViewById(R.id.textView3);
        text_descrption3 = findViewById(R.id.textView6);
        text_descrption4 = findViewById(R.id.textView10);

        searchView.setQueryHint("search GirlMakeUp");
        searchView.clearFocus();

        searchView.setEnabled(true);

        hori.setHorizontalScrollBarEnabled(false);
        horizontalScrollView2.setHorizontalScrollBarEnabled(false);
        horizontalScrollView3.setHorizontalScrollBarEnabled(false);
        horizontalScrollView4.setHorizontalScrollBarEnabled(false);

        searchView.setSubmitButtonEnabled(true);

        searchView.setMinimumHeight(0);

    /*
     we add a search listener
    */
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {

                Dictionary<String, String> dictionary = new Hashtable<>();
                Dictionary<String, String> dictionary_next = dixtionary(dictionary);
                /*
                 here we process the dictionary

                 */
                if (dictionary_next.keys().hasMoreElements()) {

                    dictionary_next.keys().asIterator().forEachRemaining(new Consumer() {
                        @Override
                        public void accept(Object key) {

                            if (key.equals(s.trim().toLowerCase())) {


                                url = dictionary_next.get(key).toString();
                                /*
                                we create a Json Array Request
                                We choose array instead of object because we're dealing with array
                                with array of makeup.

                                params: int the way of access
                                url the makeup api
                                jsonrequest null
                                listener
                                error listener

                                 */
                                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

                                    @Override
                                    public void onResponse(JSONArray response) {

                                        /*
                                        we process each response and add them to a list of json object
                                        for a natural search
                                         */

                                        try {
                                            jsonObjectList = new ArrayList<>();
                                            
                                            jsonObjectList.add(0, response.getJSONObject(0));
                                            jsonObjectList.add(1, response.getJSONObject(1));
                                            jsonObjectList.add(2, response.getJSONObject(2));
                                            jsonObjectList.add(3, response.getJSONObject(3));

                                            /*

                                            we get the responses and hook them up to our views
                                             */
                                            productprice = jsonObjectList.get(0).getString("price");
                                            productName = jsonObjectList.get(0).getString("name");
                                            text_product_name.setText(productName + "\t\t" + "$" + productprice);


                                            product_link = jsonObjectList.get(0).getString("product_link");
                                            product_detail = jsonObjectList.get(0).getString("description");
                                            text_descrption.setText(product_detail + "\n\n" + product_link);

                                            productprice = jsonObjectList.get(1).getString("price");
                                            productName = jsonObjectList.get(1).getString("name");
                                            text_product_name2.setText(productName + "\t\t" + "$" + productprice);

                                            product_link = jsonObjectList.get(1).getString("product_link");
                                            product_detail = jsonObjectList.get(1).getString("description");
                                            text_descrption2.setText(product_detail + "\n\n" + product_link);

                                            productprice = jsonObjectList.get(2).getString("price");
                                            productName = jsonObjectList.get(2).getString("name");
                                            text_product_name3.setText(productName + "\t\t" + "$" + productprice);

                                            product_link = jsonObjectList.get(2).getString("product_link");
                                            product_detail = jsonObjectList.get(2).getString("description");
                                            text_descrption3.setText(product_detail + "\n\n" + product_link);

                                            productprice = jsonObjectList.get(3).getString("price");
                                            productName = jsonObjectList.get(3).getString("name");
                                            text_product_name4.setText(productName + "\t\t" + "$" + productprice);

                                            product_link = jsonObjectList.get(3).getString("product_link");
                                            product_detail = jsonObjectList.get(3).getString("description");
                                            text_descrption4.setText(product_detail + "\n\n" + product_link);
                                            /*

                                            here we began processing the image link
                                            and hooking them up
                                            we use glide, a fast-image-processing library
                                             */
                                            imageLink = jsonObjectList.get(0).getString("image_link");
                                            Glide.with(context).load(imageLink).fitCenter().into(imageView);

                                            imageLink = jsonObjectList.get(1).getString("image_link");
                                            Glide.with(context).load(imageLink).fitCenter().into(imageView2);

                                            imageLink = jsonObjectList.get(2).getString("image_link");
                                            Glide.with(context).load(imageLink).fitCenter().into(imageView3);

                                            imageLink = jsonObjectList.get(3).getString("image_link");
                                            Glide.with(context).load(imageLink).fitCenter().into(imageView4);


                                        } catch (JSONException e) {
                                            throw new RuntimeException(e);
                                        }

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                        text_descrption.setText(error.getMessage());

                                    }
                                });

                                Volley.newRequestQueue(context).add(jsonArrayRequest);


                            }
                            else {

                                text_product_name.setText("No such result");
                                // text_product_name.setText("");

                            }


                        }
                    });
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                /*

                here we reset our views

                 */

                text_descrption.setText(null);
                text_descrption2.setText(null);
                text_descrption3.setText(null);
                text_descrption4.setText(null);

                text_product_name.setText(null );

                text_product_name2.setText( null);
                text_product_name3.setText(null );
                text_product_name4.setText(null );

                imageView.setImageBitmap(null);
                imageView2.setImageBitmap(null);
                imageView3.setImageBitmap(null);
                imageView4.setImageBitmap(null);

                return true;
            }
        });



    }

    /*

    A dictionary url
    return: dictionary
    param: dictionary
     */
    public Dictionary<String,String> dixtionary(Dictionary<String,String> dictionary){

        dictionary.put("dior","https://makeup-api.herokuapp.com/api/v1/products.json?brand=dior");
        dictionary.put("dio","https://makeup-api.herokuapp.com/api/v1/products.json?brand=dior");

        dictionary.put("cover","https://makeup-api.herokuapp.com/api/v1/products.json?brand=covergirl");
        dictionary.put("covergirl","https://makeup-api.herokuapp.com/api/v1/products.json?brand=covergirl");

        dictionary.put("maybelline","https://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline");
        dictionary.put("may","https://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline");

        dictionary.put("smashbox","https://makeup-api.herokuapp.com/api/v1/products.json?brand=smashbox");
        dictionary.put("smash","https://makeup-api.herokuapp.com/api/v1/products.json?brand=smashbox");

        dictionary.put("pacifica","https://makeup-api.herokuapp.com/api/v1/products.json?brand=pacifica");
        dictionary.put("paci","https://makeup-api.herokuapp.com/api/v1/products.json?brand=pacifica");

        dictionary.put("marien","https://makeup-api.herokuapp.com/api/v1/products.json?brand=marienatie");
        dictionary.put("marienatie","https://makeup-api.herokuapp.com/api/v1/products.json?brand=marienatie");

        dictionary.put("mar","https://makeup-api.herokuapp.com/api/v1/products.json?brand=marienatie");
        dictionary.put("iman","https://makeup-api.herokuapp.com/api/v1/products.json?brand=iman");

        dictionary.put("nyx","https://makeup-api.herokuapp.com/api/v1/products.json?brand=iman");
        dictionary.put("sante","https://makeup-api.herokuapp.com/api/v1/products.json?brand=sante");

        dictionary.put("colour","https://makeup-api.herokuapp.com/api/v1/products.json?brand=colourpop");
        dictionary.put("colourpop","https://makeup-api.herokuapp.com/api/v1/products.json?brand=colourpop");

        dictionary.put("revlon","https://makeup-api.herokuapp.com/api/v1/products.json?brand=revlon");
        dictionary.put("milani","https://makeup-api.herokuapp.com/api/v1/products.json?brand=milani");

        dictionary.put("almay","https://makeup-api.herokuapp.com/api/v1/products.json?brand=almay");

        dictionary.put("essie","https://makeup-api.herokuapp.com/api/v1/products.json?brand=essie");
        //dictionary.put("zorah","https://makeup-api.herokuapp.com/api/v1/products.json?brand=zorah");
        return dictionary;
    }
}