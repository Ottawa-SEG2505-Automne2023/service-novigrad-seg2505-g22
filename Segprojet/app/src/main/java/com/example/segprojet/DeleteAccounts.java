package com.example.segprojet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DeleteAccounts extends AppCompatActivity implements ExampleDialog3.ExampleDialogListener {


    private ListView listView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("users");
    ArrayList<String> listOfUsers ;
    ArrayAdapter<String> adapter;
    User user;
    private boolean confirm =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_accounts);

        user = new User();
        listView =(ListView)findViewById(R.id.listView);

        listOfUsers =new ArrayList<>();
        adapter = new ArrayAdapter<String>(DeleteAccounts.this, R.layout.user_info,R.id.printUsername,listOfUsers);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds:snapshot.getChildren())
                {
                    user =ds.getValue(User.class);
                    listOfUsers.add(user.getUsername()+" "+user.getEmail()+" "+user.getTypeOfAccount());
                }

                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                openDialog3();
                if(confirm)
                {

                    String user =listOfUsers.get(position);
                    listOfUsers.remove(position);
                    int firstSpacePosition = user.indexOf(" ");
                    String userId = user.substring(0,firstSpacePosition);
                    myRef.child(userId).removeValue();
                }


                confirm=false;
                return false;
            }
        });
    }

    private void openDialog3() {
        ExampleDialog3 exampleDialog = new ExampleDialog3();
        exampleDialog.show(getSupportFragmentManager(),"example Dialog");
    }

    @Override
    public void DeleteItem(){
       confirm =true;
    }
}