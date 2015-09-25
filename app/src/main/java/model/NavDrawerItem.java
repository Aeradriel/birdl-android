package model;

import android.content.res.TypedArray;

/**
 * Created by Christophe on 15/08/2015.
 */
public class NavDrawerItem {
    private boolean showNotify;
    private String title;
    private TypedArray icon;

    public NavDrawerItem() {

    }

    public NavDrawerItem(boolean showNotify, String title, TypedArray icon) {
        this.showNotify = showNotify;
        this.title = title;
        this.icon = icon;
    }

    public boolean isShowNotify() {
        return showNotify;
    }

    public void setShowNotify(boolean showNotify) {
        this.showNotify = showNotify;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TypedArray getIcon() {
        return icon;
    }

    public void setIcon(TypedArray icon) {
        this.icon = icon;
    }
}
