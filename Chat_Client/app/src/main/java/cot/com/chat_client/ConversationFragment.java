package cot.com.chat_client;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eli on 07/08/17.
 */

@SuppressLint("ValidFragment")
public class ConversationFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;

    public ConversationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_conversations, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        mFloatingActionButton = (FloatingActionButton) v.findViewById(R.id.floating_action_button);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && mFloatingActionButton.getVisibility() == View.VISIBLE) {
                    mFloatingActionButton.hide();
                } else if (dy < 0 && mFloatingActionButton.getVisibility() != View.VISIBLE) {
                    mFloatingActionButton.show();
                }
            }
        });

        setAdapterRv();

        setAddBtn();

        return v;
    }

    private void setAddBtn() {
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.addConversation();
            }
        });
    }

    private void setAdapterRv() {
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(getContext(), mLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(mDividerItemDecoration);

        mAdapter = new MyAdapter(new ArrayList<Conversation>());
        mRecyclerView.setAdapter(mAdapter);
    }


    public class MyAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<Conversation> mDataset;


        public MyAdapter(List<Conversation> myDataset) {
            mDataset = myDataset;
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
                    startActivity(new Intent(getActivity(), ChatActivity.class));
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
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv);
        }
    }


    private class Conversation {
        public String name;

        public Conversation(String s) {
            name = s;
        }
    }
}
