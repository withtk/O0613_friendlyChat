package com.project.tk.o0613_friendlychat.activity.user_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.tk.o0613_friendlychat.R;
import com.project.tk.o0613_friendlychat.activity.ChatListActivity;
import com.project.tk.o0613_friendlychat.model.User;
import com.project.tk.o0613_friendlychat.util.SharedPreferenceUtil;

public class UserListActivity extends AppCompatActivity {

    private static final String TAG = "UserListActivity";
    public static final String CHILD_USERS = "users";

    private TextView tv1;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference mReference;
    private FirebaseRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        setTitle(R.string.user_list);


        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setText(SharedPreferenceUtil.getInstance().getString(SharedPreferenceUtil.DISPLAY_NAME,null));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserListActivity.this, ChatListActivity.class));
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setStackFromEnd(true);

        mReference = FirebaseDatabase.getInstance().getReference();
        mAdapter = new UserListAdapter(UserListActivity.this,User.class, R.layout.item_user_list, UserListViewHolder.class, mReference.child(CHILD_USERS));
        /*mAdapter.setMyViewHolderClickListener(new UserListViewHolder.MyViewHolderClickListener() {
            @Override
            public void onFaceClick(View view, int position) {
                Toast.makeText(UserListActivity.this,"onFaceClick",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNameClick(View view, int position) {
                Toast.makeText(UserListActivity.this,"NameCNameCNameC",Toast.LENGTH_LONG).show();
            }
        });*/

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}


/*
package com.project.tk.o0613_friendlychat.activity.user_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.tk.o0613_friendlychat.R;
import com.project.tk.o0613_friendlychat.activity.ChatListActivity;
import com.project.tk.o0613_friendlychat.model.User;
import com.project.tk.o0613_friendlychat.util.SharedPreferenceUtil;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserListActivity extends AppCompatActivity {

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        CircleImageView civUserFace;
        TextView tvUserName, tvInsertedDate;

        public UserViewHolder(View v) {
            super(v);
            civUserFace = (CircleImageView)v.findViewById(R.id.civ_user_face);
            tvUserName = (TextView)v.findViewById(R.id.tv_user_name);
            tvInsertedDate = (TextView)v.findViewById(R.id.tv_inserted_date);
        }
    }

    private static final String TAG = "UserListActivity";
    public static final String CHILD_USERS = "users";

    private TextView tv1;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private DatabaseReference mReference;
    private FirebaseRecyclerAdapter<User, UserViewHolder> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        setTitle(R.string.user_list);


        tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setText(SharedPreferenceUtil.getInstance().getString(SharedPreferenceUtil.DISPLAY_NAME,null));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserListActivity.this, ChatListActivity.class));
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setStackFromEnd(true);

        mReference = FirebaseDatabase.getInstance().getReference();
        mAdapter = new FirebaseRecyclerAdapter<User, UserViewHolder>(
                User.class, R.layout.item_user_list, UserViewHolder.class, mReference.child(CHILD_USERS)) {

            @Override
            protected User parseSnapshot(DataSnapshot snapshot) {
                User user = super.parseSnapshot(snapshot);
                if(user!=null){
                    user.setKey(snapshot.getKey());
                }else{
                    Toast.makeText(UserListActivity.this,"no Users",Toast.LENGTH_LONG).show();
                }
                return user;
            }

            @Override
            protected void populateViewHolder(UserViewHolder viewHolder, User user, int position) {
                if(user.getUserName()!=null){
                    viewHolder.tvUserName.setText(user.getUserName());
                }
                if(user.getInsertedDate()!=null){
                    viewHolder.tvInsertedDate.setText(user.getInsertedDate());
                }
                if(user.getFaceUrl()==null){
                    viewHolder.civUserFace.setImageDrawable(ContextCompat.getDrawable(UserListActivity.this,R.drawable.ic_account_circle_black_36dp));
                }else{
                    Glide.with(UserListActivity.this)
                            .load(user.getFaceUrl())
                            .into(viewHolder.civUserFace);
                }

            }
        };

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}
*/