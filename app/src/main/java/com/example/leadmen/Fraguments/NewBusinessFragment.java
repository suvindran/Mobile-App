package com.example.leadmen.Fraguments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.leadmen.Activities.AuditingActivity;
import com.example.leadmen.R;

import java.util.ArrayList;
import java.util.List;

public class NewBusinessFragment extends Fragment {
    LinearLayout auditing;

    private List<chat_Model> strings = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_business, container, false);
        auditing = (LinearLayout)view.findViewById(R.id.auditing);
        auditing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AuditingActivity.class);
                startActivity(i);
            }
        });
        /*RecyclerView recyclerView = new RecyclerView(getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(new Chat_Adapter(strings,getActivity().getApplicationContext()));
        strings.add(new chat_Model(R.drawable.loans,"My Loans"));
        strings.add(new chat_Model(R.drawable.reports,"Reports"));
        strings.add(new chat_Model(R.drawable.collections,"Auditing"));*/
        // Inflate the layout for this fragment
        return view;
    }

    public class Chat_Adapter extends RecyclerView.Adapter<SimpleViewHolder> {

        List<chat_Model> list;
        Context context;

        public Chat_Adapter(List<chat_Model> strings, Context applicationContext) {
            this.list = strings;
            this.context = applicationContext;
        }

        @Override
        public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_desgin, parent, false);
            SimpleViewHolder viewHolder = new SimpleViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(SimpleViewHolder holder, int position) {
            holder.imageView.setImageResource(list.get(position).getName());
            holder.name.setText(list.get(position).getDate());
            holder.click.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{

        public TextView name;
        LinearLayout click;
        ImageView imageView;
        private final Context context;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            name = (TextView) itemView.findViewById(R.id.name);
            click = (LinearLayout) itemView.findViewById(R.id.click);
        }
    }

    public class chat_Model {

        private Integer image;

        private String name;

        public int getName() {
            return image;
        }

        public void setName(Integer image) {
            this.image = image;
        }

        public String getDate() {
            return name;
        }


        public void setDate(String name) {
            this.name = name;
        }


        public chat_Model(Integer image,String name) {
            this.image = image;
            this.name = name;
        }
    }
}