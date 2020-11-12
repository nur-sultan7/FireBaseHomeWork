package com.nursultan.firebasehomework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import io.perfmark.Tag;

public class AddUserActivity extends AppCompatActivity {

    EditText editTextFN;
    EditText editTextLN;
    EditText editTextAge;
    EditText editTextSex;
    Button buttonSave;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        editTextFN=findViewById(R.id.editTextFN);
        editTextLN=findViewById(R.id.editTextLN);
        editTextAge=findViewById(R.id.editTextAge);
        editTextSex=findViewById(R.id.editTextSex);
        db= FirebaseFirestore.getInstance();
    }
    public void save(View view)
    {
        if (isValidate())
        {
            User user = new User(editTextFN.getText().toString().trim(),
                    editTextLN.getText().toString().trim(),
                    Integer.parseInt(editTextAge.getText().toString().trim()),
                    editTextSex.getText().toString());

            db.collection("users")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.i("FireBase","Saved user id-"+documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.i("FireBase","Error - "+e.toString());
                        }
                    });
            finish();

        }
    }
    public boolean isValidate()
    {
        return !editTextFN.getText().toString().isEmpty()
                && !editTextLN.getText().toString().isEmpty()
                && !editTextAge.getText().toString().isEmpty()
                && !editTextSex.getText().toString().isEmpty();
    }
}
