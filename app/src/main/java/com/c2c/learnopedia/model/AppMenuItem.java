package com.c2c.learnopedia.model;

/**
 * Created by prakash on 6/30/2016.
 */
public class AppMenuItem {

    private int menuIcon;

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public int getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(int menuIcon) {
        this.menuIcon = menuIcon;
    }

    private String menuTitle;

    public AppMenuItem(int menuIcon, String menuTitle) {
        this.menuIcon = menuIcon;
        this.menuTitle = menuTitle;
    }
}
