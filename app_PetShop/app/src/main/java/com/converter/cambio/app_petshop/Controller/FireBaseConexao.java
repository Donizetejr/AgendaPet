package com.converter.cambio.app_petshop.Controller;


import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FireBaseConexao {

    private static FirebaseAuth firebaseAuth;
    private static FirebaseAuth.AuthStateListener authStateListener;
    private static FirebaseUser firebaseUser;

    private  FireBaseConexao(){
    }

    public static FirebaseAuth getFirebaseAuth()
    {
        if(firebaseAuth == null){
            inicializaFirebaseAuth();
        }
        return firebaseAuth;
    }

    private static void inicializaFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null){
                    firebaseUser = user;
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public static FirebaseUser getFirebaseUser()
    {
        return  firebaseUser;
    }
    
    public static void logout(){
        firebaseAuth.signOut();
    }
}
