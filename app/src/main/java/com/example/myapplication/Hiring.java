package com.example.myapplication;

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

    public int compareTo(Hiring h2)
    {
        if(this.getListId() > h2.getListId())
        {
            return 1;
        }
        else if(this.getListId() < h2.getListId())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

}
