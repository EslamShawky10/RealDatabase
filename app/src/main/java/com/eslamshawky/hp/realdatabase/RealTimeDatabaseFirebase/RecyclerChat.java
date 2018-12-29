package com.eslamshawky.hp.realdatabase.RealTimeDatabaseFirebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.PluralsRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eslamshawky.hp.realdatabase.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerChat extends RecyclerView.Adapter<RecyclerChat.MainView> {
   private Context context;
   ArrayList<ChatModel> chatModels;


    public RecyclerChat(Context context, ArrayList<Student> students) {
        this.context = context;
        this.chatModels = chatModels;
    }

    public RecyclerChat(Context context) {
        this.context = context;
    }

    public RecyclerChat(DatabaseActivity context, List<ChatModel> chatModelList) {
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setChatModels(ArrayList<ChatModel> chatModels) {
        this.chatModels = chatModels;
    }

    @NonNull
    @Override
    public MainView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.chat,viewGroup,false);
        return new RecyclerChat.MainView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainView mainView, int i) {
        ChatModel model = chatModels.get(i);
        System.out.println("this in");
        String chat = model.getTextChat();
        mainView.textChat.setText(chat);

    }

    @Override
    public int getItemCount() {
        return chatModels.size();
    }


    public class MainView extends RecyclerView.ViewHolder {
        TextView textChat;
        public MainView(@NonNull View itemView) {
            super(itemView);
            textChat = itemView.findViewById(R.id.text_chat);
        }
    }
}
