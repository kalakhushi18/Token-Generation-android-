package com.example.asus.tokengeneration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class Counter_details_Adapter extends RecyclerView.Adapter<Counter_details_Adapter.CounterViewHolder>{

    List<String> counters ;
    List<String> tokens ;

    public Counter_details_Adapter(List<String> counters , List<String> tokens){
        this.counters = counters ;
        this.tokens = tokens ;
    }

    @NonNull
    @Override
    public CounterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.counter_item, parent, false);

        return new CounterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CounterViewHolder holder, int position) {
         holder.counter_number.setText(counters.get(position));
         holder.token_number.setText(tokens.get(position));
    }

    @Override
    public int getItemCount() {
        return counters.size();
    }

    class CounterViewHolder extends RecyclerView.ViewHolder {
        public TextView counter_number ;
        public TextView token_number ;
        public CounterViewHolder(@NonNull View itemView) {
            super(itemView);
            counter_number = (TextView)itemView.findViewById(R.id.counterNumber);
            token_number = (TextView) itemView.findViewById(R.id.tokennumber);
        }
    }
}
