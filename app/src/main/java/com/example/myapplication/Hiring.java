package com.example.myapplication;

/*
    The JSON data is getting deserialized into Java Objects using Gson in the Main Activity
    id,listid,name in java == Id, listid, name in JSON

    We need getters to be able to access to JSON data/Objects in different classes
        - in this case we need it to retrieve the content to display on the main activity
        - do the sorting and removing of certain objects/variables
 */
public class Hiring
{

    private int id;
    private int listId;
    private String name;


    public int getId() {
        return id;
    }

    public int getListId() {
        return listId;
    }

    public String getName() {
        return name;
    }
}
