//////////THE OLD WORKING CODE, CAN ADD BUT NOT EDIT OR DELETE
package com.example.schedulerv2;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ClassInfoAdapter extends RecyclerView.Adapter<ClassInfoAdapter.ClassViewHolder> {

    private ArrayList<ClassInfo> classList;
    private OnItemClickListener listener;

    // Interface for click events
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // Constructor
    public ClassInfoAdapter(ArrayList<ClassInfo> classList) {
        this.classList = classList;
    }

    // ViewHolder class
    public static class ClassViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewDateTime;
        public TextView textViewDaysOfWeek;
        public TextView textViewProfessor;
        public TextView textViewSection;
        public TextView textViewLocation;

        public ClassViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            textViewDateTime = itemView.findViewById(R.id.textViewDateTime);
            textViewDaysOfWeek = itemView.findViewById(R.id.textViewDaysOfWeek);
            textViewProfessor = itemView.findViewById(R.id.textViewProfessor);
            textViewSection = itemView.findViewById(R.id.textViewSection);
            textViewLocation = itemView.findViewById(R.id.textViewLocation);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_class_card, parent, false);
        return new ClassViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        ClassInfo currentItem = classList.get(position);
        holder.textViewDateTime.setText(currentItem.getDateTime());
        holder.textViewDaysOfWeek.setText(currentItem.getDaysOfWeek());
        holder.textViewProfessor.setText(currentItem.getProfessor());
        holder.textViewSection.setText(currentItem.getSection());
        holder.textViewLocation.setText(currentItem.getLocation());
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }
}

//package com.example.schedulerv2;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.ArrayList;
//
//public class ClassInfoAdapter extends RecyclerView.Adapter<ClassInfoAdapter.ClassViewHolder> {
//
//    private ArrayList<ClassInfo> classList;
//
//    public static class ClassViewHolder extends RecyclerView.ViewHolder {
//        public TextView textViewDateTime;
//        public TextView textViewDaysOfWeek;
//        public TextView textViewProfessor;
//        public TextView textViewSection;
//        public TextView textViewLocation;
//
//        public ClassViewHolder(View itemView) {
//            super(itemView);
//            textViewDateTime = itemView.findViewById(R.id.textViewDateTime);
//            textViewDaysOfWeek = itemView.findViewById(R.id.textViewDaysOfWeek);
//            textViewProfessor = itemView.findViewById(R.id.textViewProfessor);
//            textViewSection = itemView.findViewById(R.id.textViewSection);
//            textViewLocation = itemView.findViewById(R.id.textViewLocation);
//        }
//    }
//
//    public ClassInfoAdapter(ArrayList<ClassInfo> classList) {
//        this.classList = classList;
//    }
//
//    @NonNull
//    @Override
//    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_class_card, parent, false);
//        return new ClassViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
//        ClassInfo currentItem = classList.get(position);
//        holder.textViewDateTime.setText(currentItem.getDateTime());
//        holder.textViewDaysOfWeek.setText(currentItem.getDaysOfWeek());
//        holder.textViewProfessor.setText(currentItem.getProfessor());
//        holder.textViewSection.setText(currentItem.getSection());
//        holder.textViewLocation.setText(currentItem.getLocation());
//    }
//
//    @Override
//    public int getItemCount() {
//        return classList.size();
//    }
//}
