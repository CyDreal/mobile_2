package com.example.main_mobile;

public class MainCardItem {
    private String title;
    private String description;
    private Class<?> targetActivity;

    public MainCardItem(String title, String description, Class<?> targetActivity) {
        this.title = title;
        this.description = description;
        this.targetActivity = targetActivity;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Class<?> getTargetActivity() { return targetActivity; }
}
