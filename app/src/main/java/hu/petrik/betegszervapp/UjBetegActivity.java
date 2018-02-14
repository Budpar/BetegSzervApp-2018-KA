package hu.petrik.betegszervapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class UjBetegActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uj_beteg);

        Button ujbeteg = (Button)findViewById(R.id.ujbeteg_gomb);
        ujbeteg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        DbHelper helper = DbHelper.getInstance(UjBetegActivity.this);
                        SQLiteDatabase db = helper.getWritableDatabase();


                        TextView nev = (TextView)findViewById(R.id.ujbeteg_nev);
                        TextView taj = (TextView)findViewById(R.id.ujbeteg_taj);
                        Spinner szerv = (Spinner)findViewById(R.id.ujbeteg_szerv);
                        TextView tipus = (TextView)findViewById(R.id.ujbeteg_tipus);

                        String nev_s = nev.getText().toString();
                        String taj_s = taj.getText().toString();
                        String szerv_s = szerv.getSelectedItem().toString();
                        String tipus_s = tipus.getText().toString();



                        ContentValues uj_beteg = new ContentValues();
                        uj_beteg.put("nev",nev_s);
                        uj_beteg.put("taj", taj_s);
                        uj_beteg.put("szerv", szerv_s);
                        uj_beteg.put("tipus", tipus_s);
                        uj_beteg.putNull("szerv_id");
                        long uj_betegId = db.insert("beteg", null, uj_beteg);

                        db.close();
                        UjBetegActivity.this.finish();
                    }
                });
            }
        });
    }
}
