package com.example.kolibree.mynotepad2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button image, text, video;
    private Intent i;
    private MyAdapter myAdapter;
    private NotesDB notesDB;
    private SQLiteDatabase dbReader;
    private ListView lv;
    private Cursor cursor;

    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void initView() {

        image = (Button) findViewById(R.id.image);
        text = (Button) findViewById(R.id.text);
        video = (Button) findViewById(R.id.video);
        lv = (ListView)findViewById(R.id.list);
        image.setOnClickListener(this);
        text.setOnClickListener(this);
        video.setOnClickListener(this);
        notesDB = new NotesDB(this);
        dbReader = notesDB.getReadableDatabase();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursor = dbReader.rawQuery("select * from " + NotesDB.TABLE_NAME,null);
                cursor.moveToPosition(position);
                Intent i = new Intent(MainActivity.this, Select.class);
                i.putExtra(NotesDB.ID,cursor.getInt(cursor.getColumnIndex(NotesDB.ID)));
                i.putExtra(NotesDB.CONTENT,cursor.getString(cursor.getColumnIndex(NotesDB.CONTENT)));
                i.putExtra(NotesDB.TIME,cursor.getString(cursor.getColumnIndex(NotesDB.TIME)));
                startActivity(i);
            }
        });
    }

    public void onClick(View v) {
        i = new Intent(this, AddContent.class);
        switch (v.getId()) {
            case R.id.image:
                i.putExtra("flag", "1");
                startActivity(i);
                break;
            case R.id.text:
                i.putExtra("flag", "2");
                startActivity(i);
                break;
            case R.id.video:
                i.putExtra("flag", "3");
                startActivity(i);
                break;
        }


    }
    public void selectDB(){
        Cursor cursor = dbReader.query(NotesDB.TABLE_NAME,null,null,null,null,null,null);
        myAdapter = new MyAdapter(this,cursor);
        lv.setAdapter(myAdapter);

    }
    protected void onResume(){
        super.onResume();
        selectDB();

    }
}


