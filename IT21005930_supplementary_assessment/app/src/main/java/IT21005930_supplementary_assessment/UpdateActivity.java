package IT21005930_supplementary_assessment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.IT21005930_supplementary_assessment.R;

public class UpdateActivity extends AppCompatActivity {

    EditText note_input, date_input, dis_input;
    Button update_button, delete_button;

    String id, note, date, dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        note_input = findViewById(R.id.note_input2);
        date_input = findViewById(R.id.date_input2);
        dis_input = findViewById(R.id.dis_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);


        getAndSetIntentData();


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(note);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                note = note_input.getText().toString().trim();
                date = date_input.getText().toString().trim();
                dis = dis_input.getText().toString().trim();
                myDB.updateData(id, note, date, dis);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("note") &&
                getIntent().hasExtra("date") && getIntent().hasExtra("dis")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            note = getIntent().getStringExtra("note");
            date = getIntent().getStringExtra("date");
            dis = getIntent().getStringExtra("dis");

            //Setting Intent Data
            note_input.setText(note);
            date_input.setText(date);
            dis_input.setText(dis);
            Log.d("stev", note+" "+date+" "+dis);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + note + " ?");
        builder.setMessage("Are you sure you want to delete " + note + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
