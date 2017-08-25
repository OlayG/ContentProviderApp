package com.example.admin.contentproviderapp.injection;

import com.example.admin.contentproviderapp.view.MainActivity;

import dagger.Component;

/**
 * Created by Admin on 8/23/2017.
 */
@Component(modules = MainActivityModule.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
