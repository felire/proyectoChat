package com.example.felipe.chatrevancha;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.*;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

import com.astuetz.PagerSlidingTabStrip;
import com.facebook.login.LoginManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Menu extends AppCompatActivity{
    LoginManager loginManager;
    ViewPager viewPager;
    MenuAdapter adapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        loginManager = LoginManager.getInstance();
        toolbar = (Toolbar) findViewById(R.id.toolbarPrincipal);

        toolbar.setTitle("Menu");
        //toolbar.inflateMenu(R.menu.toolbarmenu);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.paginas);
        viewPager.setAdapter(new MenuAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(1);
        PagerSlidingTabStrip tabus = (PagerSlidingTabStrip) findViewById(R.id.tabus);
        //tabus.setIndicatorHeight(3);
        tabus.setViewPager(viewPager);
        //PagerTabStrip tabs = (PagerTabStrip) findViewById(R.id.tabs);
        /*FragmentTabHost tabHost = (FragmentTabHost) findViewById(R.id.menu);
        tabHost.setup(this, getSupportFragmentManager(), R.id.berga);
        tabHost.addTab(tabHost.newTabSpec("AJUSTES").setIndicator("AJUSTES"), Ajustes.class, null);
        tabHost.addTab(tabHost.newTabSpec("CHATS").setIndicator("CHATS"), Chats.class, null);
        tabHost.addTab(tabHost.newTabSpec("CONTACTOS").setIndicator("CONTACTOS"), Contactos.class, null);*/

    }
}
