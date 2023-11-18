package com.example.myapplication.activity.dashboard;

import com.example.myapplication.enumeration.CardViewItemEnum;

public class GridViewModel {
    // string course_name for storing course_name
    // and imgid for storing image id.

    private String name;
    private int imageId;
    private CardViewItemEnum cardViewItemEnum;


    public GridViewModel(String name, int imageId, CardViewItemEnum cardViewItemEnum) {
        this.name = name;
        this.imageId = imageId;
        this.cardViewItemEnum = cardViewItemEnum;
    }

    public String getCourseName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public CardViewItemEnum getCardViewItemEnum() {
        return cardViewItemEnum;
    }

}
