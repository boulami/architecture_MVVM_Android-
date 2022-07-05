package com.example.architecture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID=
            "com.example.architecture.EXTRA_ID";

    public static final String EXTRA_TITLE=
            "com.example.architecture.EXTRA_TITLE";

    public static final String EXTRA_PRIORITY=
            "com.example.architecture.EXTRA_PRIORITY";

    public static final String EXTRA_DESCCRIPTION=
            "com.example.architecture.EXTRA_DESCCRIPTION";
    private EditText editTexttitle;
    private EditText editTextDesc;
    private NumberPicker numberPickerpriorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTexttitle=findViewById(R.id.edit_text_title);
        editTextDesc=findViewById(R.id.edit_text_description);
        numberPickerpriorite=findViewById(R.id.number_picker_priorite);

        numberPickerpriorite.setMinValue(1);
        numberPickerpriorite.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent =getIntent();
        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit note");
            editTexttitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTexttitle.setText(intent.getStringExtra(EXTRA_DESCCRIPTION));
            numberPickerpriorite.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }
        else {
            setTitle("Add note");
        }


    }

    private void saveNote(){
        String title1=editTexttitle.getText().toString();
        String desc=editTextDesc.getText().toString();
        int priority1=numberPickerpriorite.getValue();
        if (title1.trim().isEmpty() || desc.trim().isEmpty()){
            Toast.makeText(this,"please insert a title and desc",Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data= new Intent();
        data.putExtra(EXTRA_TITLE,title1);
        data.putExtra(EXTRA_DESCCRIPTION,desc);
        data.putExtra(EXTRA_PRIORITY,priority1);
        int id=getIntent().getIntExtra(EXTRA_ID,-1);
        if(id!=-1){
            data.putExtra(EXTRA_ID,id);
        }
        setResult(RESULT_OK,data);
        finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}