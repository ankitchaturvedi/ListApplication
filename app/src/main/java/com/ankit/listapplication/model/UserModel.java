package com.ankit.listapplication.model;

import android.view.View;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ankit.listapplication.R;
import com.bumptech.glide.Glide;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user")
public class UserModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int mid;

    @ColumnInfo(name = "email")
    @SerializedName("email")
    @Expose
    String email;

    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    @Expose
    String first_name;

    @ColumnInfo(name = "last_name")
    @SerializedName("last_name")
    @Expose
    String last_name;

    @ColumnInfo(name = "avatar")
    @SerializedName("avatar")
    @Expose
    String avatar;

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @BindingAdapter({"avatar"})
    public static void loadImage(ImageView imageView, String imageURL) {
        if (imageURL != null) {
            imageView.setVisibility(View.VISIBLE);
            Glide.with(imageView.getContext())
                    .load(imageURL)
                    .placeholder(R.drawable.no_image)
                    .dontAnimate()
                    .fitCenter()
                    .into(imageView);
        } else {
            imageView.setVisibility(View.GONE);
        }
    }
}
