package com.example.androidtermwork.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidtermwork.ChatActivity;
import com.example.androidtermwork.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class chat {
    public int img;
    public String name;
    public String word;
    public String date;

    public chat(int img, String name, String word, String date) {
        this.img = img;
        this.name = name;
        this.word = word;
        this.date = date;
    }
}

public class HomeFragment extends Fragment  {

    public static List<chat> chatLists = Arrays.asList(
            new chat(R.mipmap.tx1, "学习强国助手1", "关注进博会，游世界展馆，看……","11月1日"),
            new chat(R.mipmap.tx2, "学习强国助手2", "关注进博会，游世界展馆，看……","11月3日"));

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final ListView listView = root.findViewById(R.id.listview);
        updateListView(listView);
        return root;
    }

    public void updateListView(ListView listView) {
        final List<Map<String, Object>> listItem = new ArrayList<>();
        for (int i = 0; i < chatLists.size(); i++) {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("img", chatLists.get(i).img);
            tempMap.put("name", chatLists.get(i).name);
            tempMap.put("word", chatLists.get(i).word);
            tempMap.put("date", chatLists.get(i).date);
            listItem.add(tempMap);
        }
        SimpleAdapter mAdapter = new SimpleAdapter(getActivity(), listItem, R.layout.chat_list_item, new String[]{"img", "name", "word", "date"}, new int[]{R.id.img, R.id.name, R.id.word, R.id.date});
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(),chatLists.get(position).name,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                startActivity(intent);
            }
        });
    }
}