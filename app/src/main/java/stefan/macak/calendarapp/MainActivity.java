package stefan.macak.calendarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final LinearLayout lGodina = (LinearLayout) findViewById(R.id.layoutGodina);
        lGodina.setVisibility(View.INVISIBLE);
        final LinearLayout lMesec = (LinearLayout) findViewById(R.id.layoutMesec);
        lMesec.setVisibility(View.INVISIBLE);
        CalendarView calendarMesec = (CalendarView) findViewById(R.id.calendarMesec);

        Button bGodina = (Button) findViewById(R.id.buttonGodina);
        bGodina.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                lGodina.setVisibility(View.VISIBLE);
                lMesec.setVisibility(View.INVISIBLE);

            }

        });

        Button bMesec = (Button) findViewById(R.id.buttonMesec);
        bMesec.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v)
            {
                lMesec.setVisibility(View.VISIBLE);
                lGodina.setVisibility(View.INVISIBLE);
            }

        });

        calendarMesec.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {

                    Intent myIntent = new Intent(MainActivity.this, DayActivity.class);
                    myIntent.putExtra("day", Integer.toString(dayOfMonth)).putExtra("month", Integer.toString(month)).putExtra("year", Integer.toString(year));
                    startActivity(myIntent);
            }
        });


        Button bMesecGodina = findViewById(R.id.buttonMesecGod);
        bMesecGodina.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                lGodina.setVisibility(View.VISIBLE);
                lMesec.setVisibility(View.INVISIBLE);
            }


        });

    }

}
