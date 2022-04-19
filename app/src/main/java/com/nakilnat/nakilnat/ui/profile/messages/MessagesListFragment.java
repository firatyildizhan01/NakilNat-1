package com.nakilnat.nakilnat.ui.profile.messages;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.models.response.MessagesList;
import com.nakilnat.nakilnat.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class MessagesListFragment extends BaseFragment {
    private ArrayList<MessagesList> messagesList;
    SearchView searchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_messages_list);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Mesajlar");
        pageInit();
    }

    private void filterMessage(String searchText, MessagesAdapter adapter) {
        List<MessagesList> filteredList = new ArrayList<>();
        for (MessagesList item : messagesList) {
            if (item.getName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.filterList(filteredList);
    }

    private void pageInit() {
        messagesList = new ArrayList<>();
        messagesList.add(new MessagesList("Mehmet Meydan", "naber abi", "21 saat önce"));
        messagesList.add(new MessagesList("Koray Aydın", "naber abi", "11 saat önce"));
        messagesList.add(new MessagesList("Fethi Günay", "naber abi", "3 gün önce"));

        //setting adapter and listview
        MessagesAdapter adapter = new MessagesAdapter(messagesList, this);
        RecyclerView listview = findViewById(R.id.messages_list);
        adapter.getItemCount();
        listview.setAdapter(adapter);
        listview.setLayoutManager(new LinearLayoutManager(this));

        searchView = findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterMessage(newText, adapter);
                return true;
            }
        });

    }
}
