package com.example.asus.tokengeneration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class History_Adapter extends RecyclerView.Adapter<History_Adapter.HistoryViewHolder>{

    List<String> counters ;


    public History_Adapter(List<String> counters ){
        this.counters = counters ;

    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.counter_item, parent, false);

        return new HistoryViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final HistoryViewHolder holder, int position) {
//        holder.counter_number.setText(counters.get(position));
//        holder.token_number.setText(tokens.get(position));
    }

    @Override
    public int getItemCount() {
        return counters.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {
        public TextView name ;
        public TextView date ;
        public TextView age ;
        public TextView email ;
        public TextView disease ;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
           name = (TextView)itemView.findViewById(R.id.counterNumber);
            date = (TextView) itemView.findViewById(R.id.tokennumber);
        }
    }
}
