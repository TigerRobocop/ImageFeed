package com.unibratec.livia.picturefeed;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ImageListFragment.OnImageClick {


    private FragmentManager mFragmentManager;
    private ImageListFragment mImageListFragment;
    TabLayout mTb;
    ViewPager mVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVp = (ViewPager)findViewById(R.id.viewpager_main);
        mVp.setAdapter(new PageAdapter(getSupportFragmentManager()));

        mTb = (TabLayout) findViewById(R.id.tablayout_tabs);
        mTb.setupWithViewPager(mVp);
    }

    @Override
    public void imageClick(Image img) {

    }

    private class PageAdapter extends FragmentPagerAdapter {

        public PageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new ImageListFragment();
            } else {
                return new Fragment();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        public CharSequence getPageTitle(int pos){
            if (pos == 0){
                return "List";
            }else   {
                return "Faves";
            }
        }
    }

}
