package com.anpln.vkv11;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class SortingUtils {

    public static ArrayList<String> getNamesList(ArrayList<File> files){
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            names.add(files.get(i).getName());
        }
        return names;
    }

    public static ArrayList<Long> getSizeList(ArrayList<File> files){
        ArrayList<Long> size = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            size.add(files.get(i).length());
        }
        return size;
    }

    //дата последнего изменения
    public static ArrayList<Long> getDateList(ArrayList<File> files){
        ArrayList<Long> date = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            date.add(files.get(i).lastModified());
        }
        return date;
    }


}
