package com.example.androidtermwork.ui.dashboard;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.androidtermwork.R;
import com.example.androidtermwork.webView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<ListView> listView;

    public DashboardViewModel() {
        listView = new MutableLiveData<>();
    }

    public LiveData<ListView> getListView() {
        return listView;
    }
}