package IT21005930_supplementary_assessment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.IT21005930_supplementary_assessment.R;

public class AddActivity extends AppCompatActivity {

    EditText note_input, date_input, dis_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        note_input = findViewById(R.id.note_input);
        date_input = findViewById(R.id.date_input);
        dis_input = findViewById(R.id.dis_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(note_input.getText().toString().trim(),
                        date_input.getText().toString().trim(),
                        dis_input.getText().toString().trim());
            }
        });
    }
}
