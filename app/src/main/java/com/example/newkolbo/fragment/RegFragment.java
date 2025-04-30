package com.example.newkolbo.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.newkolbo.Activity.LoginRegActivity;
import com.example.newkolbo.Activity.MainActivity;
import com.example.newkolbo.R;
import com.example.newkolbo.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegFragment extends Fragment {

    private DatabaseReference mUsersRef;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_reg, container, false);

        /// //////////
        //Initialize Firebase Authentication and Database
        mAuth = FirebaseAuth.getInstance();
        mUsersRef = FirebaseDatabase.getInstance("https://kolbonano-default-rtdb.europe-west1.firebasedatabase.app/").getReference("users");

        // Initialize the input fields and button
        TextInputEditText nameField = view.findViewById(R.id.etName);
        TextInputEditText emailField = view.findViewById(R.id.etEmail);
        TextInputEditText phoneField = view.findViewById(R.id.etPhone);
        TextInputEditText passwordField = view.findViewById(R.id.etPassword);
        Button registerButton = view.findViewById(R.id.registerButton);

        // Set an onClickListener for the register button
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the fields
                String name = nameField.getText().toString().trim();
                String email = emailField.getText().toString().trim();
                String phone = phoneField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                // Validate that all fields are filled
                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                    // Show a Toast message if any field is empty
                    Toast.makeText(getContext(), "Please fill all the fields!", Toast.LENGTH_SHORT).show();
                } else {
                    // Register the user with Firebase Authentication (Email and Password)
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(requireActivity(), task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getContext(), "authentication successful", Toast.LENGTH_SHORT).show();
                                    // If registration is successful, get the current user
                                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                    if (firebaseUser != null) {
                                        // Create a User object with the provided data
                                        LoginRegActivity.currentUser = new User(phone, name, "Arlozerov 80 Rehovot", email, password);

                                        // Add the User to Firebase Realtime Database
                                        String userId = mUsersRef.push().getKey();  // Create a unique key for the new user
                                        if (userId != null) {
                                            mUsersRef.child(userId).setValue(LoginRegActivity.currentUser)
                                                    .addOnSuccessListener(aVoid -> {
                                                        // Notify user that registration is successful
                                                        Toast.makeText(getContext(), "database Successful!", Toast.LENGTH_SHORT).show();
                                                        Toast.makeText(getContext(), "Registration Successful!", Toast.LENGTH_SHORT).show();
                                                        // Optionally, you can go to another activity after successful registration
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        // Handle errors if the registration fails
                                                        Toast.makeText(getContext(), "Registration Failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    });
                                        }
                                        Intent intent = new Intent(getContext(), MainActivity.class);
                                        startActivity(intent);
                                        getActivity().finish();
                                    }
                                } else {
                                    // If registration fails, show the error
                                    Toast.makeText(getContext(), "Authentication Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });
        /// /////////

        return view;
    }
}