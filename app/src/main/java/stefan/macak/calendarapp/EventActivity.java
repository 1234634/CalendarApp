    package stefan.macak.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

    public class EventActivity extends AppCompatActivity {

        private HttpHelper httpHelper;
        //URL1 + tOWNnAME + URL2
        public static String URL1 ="http://api.openweathermap.org/data/2.5/weather?q=";
        public static String URL2 =",uk&APPID=768bade5b2b7f54ee4ef7adcddb2a0cf";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Intent ii= getIntent();
        Bundle b = ii.getExtras();


        String name = (String) b.getString("name");
        String date = (String) b.getString("date");
        TextView txtName = (TextView) findViewById(R.id.textEventName);
        txtName.setText(name);
        TextView txtDate = (TextView) findViewById(R.id.textEventDate);
        txtDate.setText(date);


        //http part
        httpHelper = new HttpHelper();


        findViewById(R.id.buttonLokacija).setOnClickListener(new RequestLocationTemperature());


    }

    public  class RequestLocationTemperature implements View.OnClickListener {

            public String mLocationURL;


            @Override
            public void onClick(View v) {

                EditText editLokacija = (EditText)findViewById(R.id.editLokacija);
                String town = editLokacija.getText().toString();
                final TextView textTemp = (TextView) findViewById(R.id.textTemp);
                mLocationURL = URL1 + town + URL2;
                new Thread(new Runnable() {
                    public void run() {

                        try {
                            JSONObject jsonobject = httpHelper.getJSONObjectFromURL(mLocationURL);
                            JSONObject mainObj = jsonobject.getJSONObject("main");
                            final String temp =(String) mainObj.getString("temp");


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {

                                         textTemp.setText(temp);
                                    }
                                });

                                } catch (JSONException | IOException e) {

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {

                                            textTemp.setText("False town!");
                                        }
                                    });
                                        e.printStackTrace();
                                }
                            }

                }).start();

            }
        }



    }
