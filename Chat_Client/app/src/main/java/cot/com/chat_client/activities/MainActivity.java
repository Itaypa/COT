package cot.com.chat_client.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import cot.com.chat_client.R;
import cot.com.chat_client.adapters.ViewPagerAdapter;
import cot.com.chat_client.fragments.ConversationFragment;
import cot.com.chat_client.fragments.IotDevicesFragment;
import cot.com.chat_client.fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    ViewPager viewPager;

    SettingsFragment settingsFragment;
    ConversationFragment conversationFragment;
    IotDevicesFragment iotDevicesFragment;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_conversations:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.action_iot_devices:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);


    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        settingsFragment = new SettingsFragment();
        conversationFragment = new ConversationFragment();
        iotDevicesFragment = new IotDevicesFragment();
        adapter.addFragment(settingsFragment);
        adapter.addFragment(conversationFragment);
        adapter.addFragment(iotDevicesFragment);
        viewPager.setAdapter(adapter);
    }

}
