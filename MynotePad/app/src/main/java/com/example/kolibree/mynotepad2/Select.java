package com.example.kolibree.mynotepad2;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by kolibree on 2016/11/20.
 */
public class Select extends Activity implements View.OnClickListener{

    private Button delete,back;
    private TextView s_textview;
    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select);
        delete = (Button)findViewById(R.id.s_delete);
        back = (Button)findViewById(R.id.s_back);
        s_textview = (TextView) findViewById(R.id.s_textview);
        notesDB =  new NotesDB(this);
        dbWriter = notesDB.getWritableDatabase();
        delete.setOnClickListener(this);
        back.setOnClickListener(this);
        s_textview.setText(getIntent().getStringExtra(NotesDB.CONTENT));
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.s_delete:
                deleteDataBase();
                finish();
                break;
            case R.id.s_back:
                finish();
                break;
        }

    }
    public void deleteDataBase(){
        dbWriter.delete(NotesDB.TABLE_NAME,"_id="+ getIntent().getIntExtra(NotesDB.ID,0),null);
    }


}
