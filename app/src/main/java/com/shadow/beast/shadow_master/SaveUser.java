package com.shadow.beast.shadow_master;

import android.util.Pair;

import com.parse.ParseUser;

import java.util.Collection;

/**
 * Created by Beast on 11/3/2015.
 */
public class SaveUser implements Callable {

    public void call(Collection<Pair> pairs) {
        ParseUser parseUser = ParseUser.getCurrentUser();
        for(Pair pair: pairs){
            parseUser.put((String)pair.first, pair.second);
        }
        parseUser.saveInBackground();
    };
}
