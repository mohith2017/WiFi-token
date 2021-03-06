package com.example.vybhavjain.nsrcel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedpreferences;
    EditText name , phonenumber;
    String[] namesarray , phonenumberarray;
    Button b;
    String password , type1;
    private static final String URL = "https://script.googleusercontent.com/macros/echo?user_content_key=suSzdJxXBfM90snBivmZZzp7yyuJTu_Ya4qMQlPFcML-pBzHhBRDE2mBs94zfMF0YZTgAp6V9gN2KL3orU8MoFS3W9lvaBJRm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnCSxg59lhMImqfINVDyVfMjQ7I1hBwkBHvqa3IcR2vWqYG6WaOXNcFwu1QPxwNwfZ0WY2nZ7HPw5&lib=Mpmp6VZVIcgylJlJbX0MEHL866zndRzds";
    private static final String URL_guest = "https://script.google.com/macros/s/AKfycbxv-7ZjjQ9PYNDvXRn0Z-RZ8doJNYzOS0D26YS0caxmtdtM2fUR/exec";
    private RequestQueue requestQueue;
    private StringRequest request;
    JSONObject jsonArray;
    int inmate , guest;
    TextView guest12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        name = (EditText) findViewById(R.id.myname);
        phonenumber = (EditText) findViewById(R.id.myphone);
        b = (Button) findViewById(R.id.submit);
        guest12 = (TextView) findViewById(R.id.guest);
      //  sharedpreferences = getSharedPreferences("Count", Context.MODE_PRIVATE);


        request = new StringRequest(Request.Method.GET, URL , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    String flag = response;
                    Log.e(flag, "onResponse:dailydarshan ");
                    try {
                        jsonArray = new JSONObject(response);
                        Log.e(String.valueOf(jsonArray), "onResponse:");


                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                        JSONArray obj = null;
                        try {
                            obj = (JSONArray) (jsonArray.get("user"));
                            namesarray = new String[obj.length()];
                            phonenumberarray = new String[obj.length()];
                            for (int j = 0; j < obj.length(); j++) {
                                JSONObject jsonObject = null;
                                try {
                                    jsonObject = (JSONObject) (obj.get(j));
                                    String name = jsonObject.optString("name");
                                    String phonenumberget = jsonObject.optString("phone_number");
                                    namesarray[j] = name;
                                    phonenumberarray[j] = phonenumberget;
                                    Log.e( namesarray[j],"onResponse: name" );


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();


                return hashMap;

            }
        };

        requestQueue.add(request);



        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final String myname = name.getText().toString();
                Log.e( myname,"onClick: name");
                final String myphone = phonenumber.getText().toString();
                int flag = 0;
                final String id = "1L-8iuRCWLaHkwsAbTOLuni3sfJFpXe51DYeOSUSA5Cw";
                for( int i = 0; i < namesarray.length; i++ )
                {
                    Log.e(String.valueOf(myname.equals(namesarray[i])),"onClick: comparison" );
                    if(namesarray[i].equals(myname))
                    {
                        if(phonenumberarray[i].equals(myphone))
                        {
                            flag = 1;
                            type1 = "i";
                            Random random = new Random();
                            password = String.valueOf(random.nextInt(100000) + 0);
                            Log.e( password,"onClick: password" );
                            Intent intent = new Intent(MainActivity.this , Ticket.class);
                            intent.putExtra("Password" , password);
                            intent.putExtra("Username" , myname);
                            intent.putExtra("type" , type1 );
                            startActivity(intent);


                        }
                        else
                        {
                            Toast.makeText(getApplicationContext() , "Phone number is incorrect",Toast.LENGTH_LONG).show();
                        }
                    }

                }
                if( flag != 1 )
                {
                    Toast.makeText(getApplicationContext() ,"User not registered" , Toast.LENGTH_LONG).show();
                }


            }
        });


        guest12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this,login.class);
                startActivity(intent1);
            }
        });
    }

/*    public void callapi()
    {
        request = new StringRequest(Request.Method.GET, URL , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    String flag = response;
                    Log.e(flag, "onResponse:dailydarshan ");
                    try {
                        jsonArray = new JSONArray(response);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    namesarray = new String[jsonArray.length()];
                    phonenumberarray = new String[jsonArray.length()];
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = null;
                        try {
                            obj = (JSONObject) (jsonArray.get(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String name = obj.optString("name");
                        String phonenumberget = obj.optString("phonenumber");
                        namesarray[i] = name;
                        phonenumberarray[i] = phonenumberget;
                    }




                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();


                return hashMap;

            }
        };

        requestQueue.add(request);
    } */
}
