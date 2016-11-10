package notes.chaunhatquang.com.writenotes.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import notes.chaunhatquang.com.writenotes.Adapter.ItemNoteAdapter;
import notes.chaunhatquang.com.writenotes.Database.DatabaseHelper;
import notes.chaunhatquang.com.writenotes.Model.Note;
import notes.chaunhatquang.com.writenotes.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ItemNoteAdapter adapter;
    private List<Note> listNote = new ArrayList<>();
    private Context context;
    private DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        db = new DatabaseHelper(context);
        connectView();
    }

    //connect java with xml view

    private void connectView() {
        //find Float Action Button
        findViewById(R.id.fab).setOnClickListener(this);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_note);
        // If the size of views will not change as the data changes
        recyclerView.setHasFixedSize(true);
        //setting the LayoutManager.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        //setting the adapter
        adapter = new ItemNoteAdapter(listNote,context);
        recyclerView.setAdapter(adapter);
    }
    //update list note when resume (open app or finish NoteActivity)
    public void onResume(){
        super.onResume();
        updateListNote();
    }

    private void updateListNote() {
        //clear old list
        listNote.clear();
        //add all notes from database, reverse list
        ArrayList<Note> ls = db.getListNote("SELECT * FROM " + DatabaseHelper.TABLE_NOTE);
        //reverse list
        for(int i = ls.size() - 1;i >= 0;i--){
            listNote.add(ls.get(i));
        }
        //update again data
        adapter.notifyDataSetChanged();
    }

    //display note have id
    public static void showNote(Context context,long id){
        Intent intent = new Intent(context,NoteActivity.class);
        //send id to NoteActivity
        intent.putExtra(NoteActivity.ID,id);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                showNote(context,NoteActivity.NEW_NOTE);
                break;
            default:
                break;
        }
    }
}
