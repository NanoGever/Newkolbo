package com.example.newkolbo.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newkolbo.Activity.LoginRegActivity;
import com.example.newkolbo.Activity.MainActivity;
import com.example.newkolbo.R;
import com.example.newkolbo.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginFragment extends Fragment {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private FirebaseAuth mAuth;
    String email;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        // אתחול FirebaseAuth
        mAuth = FirebaseAuth.getInstance();


        // בדיקה אם יש כבר משתמש מחובר
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            // אם יש משתמש מחובר, עדכן את המשתמש הנוכחי וקפוץ אוטומטית למסך הבית
            email = currentUser.getEmail();
            updateCurrentUser();
        }

        // אתחול רכיבי UI
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        btnLogin = view.findViewById(R.id.btnLogin);

        // מאזין לכפתור הכניסה
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        return view;
    }

    private void loginUser() {
        // קבלת נתונים מהשדות
        email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // אם השדות ריקים, נציג הודעת שגיאה
        if (TextUtils.isEmpty(email)) {
            etEmail.setError("הכנס אימייל");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError("הכנס סיסמה");
            return;
        }

        // כניסת משתמש עם אימייל וסיסמה
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(requireContext(), "sign in successes", Toast.LENGTH_SHORT).show();
                        updateCurrentUser();
                    } else {
                        // אם הכניסה לא הצליחה
                        Toast.makeText(requireContext(), "הכניסה נכשלה: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateCurrentUser() {
        DatabaseReference myRef = FirebaseDatabase.getInstance("https://kolbonano-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Toast.makeText(requireActivity(), "downloading users successes", Toast.LENGTH_SHORT).show();
                //FirebaseUser firebaseUser = mAuth.getCurrentUser();
                for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                    User user = userSnapshot.getValue(User.class);
                    //if (user.getEmail().equals(firebaseUser.getEmail()))
                    if (user.getEmail().equals(email))
                    {
                        LoginRegActivity.currentUser = user;
                        // כעת, ניתן לעבור למסך הבית או מסך אחר
                        Intent intent = new Intent(requireActivity(), MainActivity.class);
                        startActivity(intent);
                        getActivity().finish(); // על מנת למנוע חזרה למסך הכניסה
                    }
                }
                Toast.makeText(requireContext(), "finish downloading...", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(requireContext(), "error reading from firebase", Toast.LENGTH_LONG).show();
            }
        });
    }


}