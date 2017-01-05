package com.rakaadinugroho.sqliteexample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rakaadinugroho.sqliteexample.PeopleItem;
import com.rakaadinugroho.sqliteexample.PeopleOpenHelper;
import com.rakaadinugroho.sqliteexample.R;

/**
 * Created by Raka Adi Nugroho on 12/30/16.
 *
 * @Github github.com/rakaadinugroho
 * @Contact nugrohoraka@gmail.com
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleHolder> {
    public static final String TAG  = PeopleAdapter.class.getSimpleName();
    PeopleOpenHelper mDB;
    LayoutInflater mInflater;
    Context mContext;

    public PeopleAdapter(Context context, PeopleOpenHelper db) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.mDB = db;
    }

    @Override
    public PeopleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview   = mInflater.inflate(R.layout.item_people, parent, false);
        return new PeopleHolder(itemview);
    }

    @Override
    public void onBindViewHolder(PeopleHolder holder, int position) {
        PeopleItem current  = mDB.query(position);
        holder.item_name.setText(current.getmName());
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class PeopleHolder extends RecyclerView.ViewHolder{
        TextView item_name;
        public PeopleHolder(View itemView) {
            super(itemView);
            item_name   = (TextView)itemView.findViewById(R.id.item_name);
        }
    }
}
