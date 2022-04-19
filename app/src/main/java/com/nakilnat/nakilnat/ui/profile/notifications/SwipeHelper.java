package com.nakilnat.nakilnat.ui.profile.notifications;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.nakilnat.nakilnat.ui.profile.NotificationsAdapter;

public class SwipeHelper extends ItemTouchHelper.SimpleCallback {
    NotificationsAdapter notificationsAdapter;

    public SwipeHelper(int dragDirs, int swipeDirs) {
        super(dragDirs, swipeDirs);
    }

    public SwipeHelper(NotificationsAdapter notificationsAdapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT);
        this.notificationsAdapter = notificationsAdapter;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        notificationsAdapter.removeNotificationItem(viewHolder.getAdapterPosition());
    }
}
