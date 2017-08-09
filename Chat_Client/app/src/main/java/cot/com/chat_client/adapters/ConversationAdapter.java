package cot.com.chat_client.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cot.com.chat_client.R;
import cot.com.chat_client.activities.ChatActivity;
import cot.com.chat_client.models.Conversation;

/**
 * Created by eli on 09/08/17.
 */

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder> {
    private List<Conversation> mDataset;
    private Context context;


    public ConversationAdapter(Context c, List<Conversation> myDataset) {
        mDataset = myDataset;
        context = c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ChatActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void addConversation() {
        mDataset.add(0, new Conversation("Conversation " + getItemCount()));
        notifyItemInserted(0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv);
        }
    }


}
