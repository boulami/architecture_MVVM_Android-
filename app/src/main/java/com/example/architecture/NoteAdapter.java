package com.example.architecture;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ListAdapter<Note,NoteAdapter.NoteHolder> {
    private OnItemClickListenner listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }
    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK=new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(Note oldItem, Note newItem) {
            return oldItem.getId()== newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Note oldItem,Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority()==newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.Title.setText(currentNote.getTitle());
        holder.priority.setText(String.valueOf(currentNote.getPriority()));
        holder.desc.setText(currentNote.getDescription());

    }



    public Note getNoteAt(int position) {
        return getItem(position);

    }


    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView Title;
        private TextView priority;
        private TextView desc;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.text_view_title);
            priority = itemView.findViewById(R.id.text_view_priority);
            desc = itemView.findViewById(R.id.text_view_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.OnItemClick(getItem(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListenner {
        void OnItemClick(Note note);

    }

    public void setOnItemClickListener(OnItemClickListenner listener) {
        this.listener = listener;
    }


}
