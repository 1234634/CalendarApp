package stefan.macak.calendarapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class DayActivity extends AppCompatActivity {

    CustomAdapter itemsAdapter;
    EditText eDodaj;
    TextView tDayInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        Intent ii= getIntent();
        Bundle b = ii.getExtras();
        String date =(String) b.getString("day")+"." + (String) b.getString("month")+"." + (String) b.getString("year");
        tDayInfo = (TextView) findViewById(R.id.textDayInfo);

        tDayInfo.setText(date);

        itemsAdapter =new CustomAdapter(this);

        ListView listView = (ListView) findViewById(R.id.listViewDay);
        listView.setAdapter(itemsAdapter);


        Button bDodaj = (Button) findViewById(R.id.buttonDodaj);
        eDodaj = (EditText) findViewById(R.id.editDodaj);
        bDodaj.setOnClickListener( new MyOnClickListener());

        listView.setOnItemClickListener(new MyOnItemClickListener(date));
        listView.setOnItemLongClickListener(new MyOnItemLongClickListener(date));

    }

    private class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String item = eDodaj.getText().toString();

            if (TextUtils.isEmpty(item)) {
                eDodaj.setError(getString(R.string.error_empty_input));
                return;
            }

            itemsAdapter.addUser(new User(item));
        }
    }

    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        public String mDate;
        public MyOnItemClickListener(String date) {
            mDate = date;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

           Intent intent = new Intent(DayActivity.this, EventActivity.class);

           String name = itemsAdapter.getUser(position).mName;
           intent.putExtra("name",name);
           intent.putExtra("date",mDate);
           startActivity(intent);
        }
    }

    private class MyOnItemLongClickListener implements AdapterView.OnItemLongClickListener{

        public String mDate;

        public MyOnItemLongClickListener(String date){
            mDate = date;
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                       final int pos, long id) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(DayActivity.this);
            builder1.setMessage("Chose from two options");
            builder1.setCancelable(true);

            builder1.setPositiveButton(
                    "Remove",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            itemsAdapter.remove(pos);
                            itemsAdapter.notifyDataSetChanged();
                            dialog.cancel();

                        }
                    });

            builder1.setNegativeButton(
                    "Change",

                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent myIntent = new Intent(DayActivity.this, EventActivity.class);

                            String name = itemsAdapter.getUser(pos).mName;

                            myIntent.putExtra("name",name);
                            myIntent.putExtra("date",mDate);
                            startActivity(myIntent);
                           // dialog.cancel();
                        }
                    });

            AlertDialog alert11 = builder1.create();
            alert11.show();

            return true;
        }
    }




}
