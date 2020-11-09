package com.example.instagramclone.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.instagramclone.R;
import com.example.instagramclone.models.User;
import com.example.instagramclone.models.UserAccountSettings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseMethods {
    private static final String TAG = "FirebaseMethods";

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private String userID;

    private Context mContext;

    public FirebaseMethods(Context context) {
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mContext = context;

        if(mAuth.getCurrentUser() != null) {
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    public boolean checkIfUsernameExist(String username, DataSnapshot dataSnapshot) {
        Log.d(TAG, "checkIfUsernameExists: checking if " + username + " already exists.");

        User user = new User();

        for (DataSnapshot ds: dataSnapshot.child(userID).getChildren()) {
            Log.d(TAG, "checkIfusernameExists: dataSnapshot " + ds);

            user.setUsername(ds.getValue(User.class).getUsername());
            Log.d(TAG, "checkIfUsernameExists: username " + user.getUsername());

            if(StringManipulation.expandUsername(user.getUsername()).equals(username)){
                Log.d(TAG, "checkIfUsername: FOUND A MATCH: " + user.getUsername());
                return true;
            }
        }
        return false;
    }

    /**
     * Register a new email and password to Firebase Authentication
     * @param email
     * @param password
     * @param username
     */
    public void registerNewEmail(final String email, String password, final String username) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail: onComplete:" + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Toast.makeText(mContext, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        } else if(task.isSuccessful()) {
                                userID = mAuth.getCurrentUser().getUid();
                                Log.d(TAG, "onComplete: Authstat changed: " + userID);
                        }
                        // ...
                    }
                });
    }

    public void addNewUser(String email, String username, String description, String website, String profile_photo) {

        User user = new User( userID, 1, email, StringManipulation.condenseUsername(username));

        //Adding new user under -users- node in firebase
        myRef.child(mContext.getString(R.string.dbname_users))
                .child(userID)
                .setValue(user);

        UserAccountSettings settings = new UserAccountSettings(
                description,
                username,
                0,
                0,
                0,
                profile_photo,
                username,
                website
        );

        //Adding new user account settings under -user_account_settings- node in firebase
        myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                .child(userID)
                .setValue(settings);
    }
}
