package com.example.admin.appmarket.factory;

import com.example.admin.appmarket.base.BaseFragment;
import com.example.admin.appmarket.fragment.paidProjects.AllPaidProjectsFragment;
import com.example.admin.appmarket.fragment.paidProjects.NotOnTheDeviceFragment;
import com.example.admin.appmarket.fragment.paidProjects.PaidAppsFragment;

import java.util.HashMap;

/**
 * Fragment的工厂类,用于缓存以及生成Fragment对象
 * Created by admin on 2016/3/23.
 */
public class PaidProjectsFragmentFactory {

    private static HashMap<Integer, BaseFragment> mHashMap = new HashMap<Integer, BaseFragment>();

    public static BaseFragment createFragment(int index){

        BaseFragment fragment = mHashMap.get(index);
        if(fragment!=null){
            return fragment;
        }else{
            switch (index){
                case 0:
                    fragment=new AllPaidProjectsFragment();
                    break;
                case 1:
                    fragment=new NotOnTheDeviceFragment();
                    break;
                case 2:
                    fragment=new PaidAppsFragment();
                    break;
                case 3:
                    fragment=new PaidAppsFragment();
                    break;
            }

            mHashMap.put(index,fragment);
            return fragment;
        }
    }

}
