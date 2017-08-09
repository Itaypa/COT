package cot.com.chat_client.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cot.com.chat_client.R;
import cot.com.chat_client.adapters.ConversationAdapter;
import cot.com.chat_client.models.Conversation;

/**
 * Created by eli on 07/08/17.
 */

@SuppressLint("ValidFragment")
public class ConversationFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private FloatingActionButton mFloatingActionButton;
    private LinearLayoutManager mLayoutManager;
    private ConversationAdapter mAdapter;

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

        mAdapter = new ConversationAdapter(getContext(), new ArrayList<Conversation>());
        mRecyclerView.setAdapter(mAdapter);
    }


}
