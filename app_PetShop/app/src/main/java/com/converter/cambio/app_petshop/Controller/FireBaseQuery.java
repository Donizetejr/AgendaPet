package com.converter.cambio.app_petshop.Controller;

import com.google.firebase.database.DatabaseReference;

public class FireBaseQuery {
    private DatabaseReference databaseReference;

    public void InsertObjectDb(Object obj, String tableName, String idObj){

        databaseReference.child(tableName).child(idObj).setValue(obj);
    }

    public void UpdateObjetcDb(){

    }

    public void DeleteObjectDb(){

    }

    public void SelectObjectDb(){

    }

    public void SelectObjectById(){

    }
}
