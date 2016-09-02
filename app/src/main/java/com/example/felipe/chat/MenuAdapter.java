package com.example.felipe.chat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by felipe on 28/08/16.
 */
public class MenuAdapter extends FragmentPagerAdapter {

    public MenuAdapter(FragmentManager fm){
        super(fm);
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch(position){
            case 0: return "AJUSTES";
            case 1: return "CHAT";
            case 2: return "CONTACTOS";
            default: return "";
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragmento;
        switch (position){
            case 0:
            {
                fragmento = new Ajustes();
                break;
            }
            case 1:
            {
                fragmento = new Chats();
                break;
            }
            case 2:
            {
                fragmento = new Contactos();
                break;
            }
            default:
            {
                fragmento = new Chats();
                break;
            }
        }
        return fragmento;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
