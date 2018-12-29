package com.eslamshawky.hp.realdatabase.RealTimeDatabaseFirebase;

import android.support.v7.widget.RecyclerView;

public interface Database_Interface {
    interface DatabaseView {
      void successful(String message);
      void failed(String message);
    }

    interface DatabasePresenter {
        void writeData(ChatModel chatModel);
        void readData( RecyclerView recyclerView);
        Void updateData(ChatModel chatModel);
        void deleteData(ChatModel chatModel);
    }
}
