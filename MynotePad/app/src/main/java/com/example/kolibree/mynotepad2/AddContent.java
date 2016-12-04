package com.example.kolibree.mynotepad2;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by kolibree on 2016/11/14.
 */
public class AddContent extends Activity  implements View.OnClickListener{

    private String val;
    private Button save, delete;
    private EditText ettext;
    private NotesDB notesDB;
    private SQLiteDatabase dbWriter;


    protected  void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.addcontent);
        val =  getIntent().getStringExtra("flag");
        save = (Button)findViewById(R.id.save);
        delete = (Button)findViewById(R.id.delete);
        ettext = (EditText)findViewById(R.id.ettext);
        save.setOnClickListener(this);
        delete.setOnClickListener(this);
        notesDB = new NotesDB(this);
        dbWriter = notesDB.getWritableDatabase();

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.save:
                addDB();
                finish();
                break;

            case R.id.delete:
                finish();
                break;
        }

    }
    private String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年mm月dd日，HH：mm：ss");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        return str;
    }
    public void addDB(){
        ContentValues cv = new ContentValues();
        cv.put(NotesDB.CONTENT,ettext.getText().toString());
        cv.put(NotesDB.TIME,getTime());
        dbWriter.insert(NotesDB.TABLE_NAME,null,cv);
    }


}
