package com.nursultan.firebasehomework;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private List<User> userList;
    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=FirebaseFirestore.getInstance();
        userList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersAdapter=new UsersAdapter();
        recyclerView.setAdapter(usersAdapter);
        CollectionReference users = db.collection("users");
//        users.get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        for (DocumentSnapshot documentSnapshot:queryDocumentSnapshots.getDocuments())
//                        {
//                            User user =documentSnapshot.toObject(User.class);
//                            userList.add(user);
//                        }
//                        usersAdapter.setUserList(userList);
//                    }
//                });
        users.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error!=null)
                {
                    return;
                }
               // userList.clear();
                for (DocumentChange documentSnapshot:value.getDocumentChanges())
                {
                    User user =documentSnapshot.getDocument().toObject(User.class);
                    userList.add(user);
                }
                usersAdapter.setUserList(userList);
            }
        });

    }
    public void goToAddActivity(View view)
    {
        Intent intent = new Intent(this,AddUserActivity.class);
        startActivity(intent);
    }
}
