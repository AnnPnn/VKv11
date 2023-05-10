package com.anpln.vkv11;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;


public class FilesActivity extends ListActivity {


    int iTypeFile [] = {R.drawable.pdf, R.drawable.jpg, R.drawable.txt, R.drawable.png, R.drawable.unknown};

    ListView listView;


    File dir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath());
    public ArrayAdapter<String> mAdapter;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, (SortingUtils.getNamesList(listFilesWithSubFolders(dir))));
        setListAdapter(mAdapter);
        //listView =(ListView) findViewById(R.id.customListView1);

//        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(),
//                SortingUtils.getNamesList(listFilesWithSubFolders(dir)),
//                SortingUtils.getSizeList(listFilesWithSubFolders(dir)),
//                SortingUtils.getDateList(listFilesWithSubFolders(dir)),
//                iTypeFile
//                );
//       listView.setAdapter(customBaseAdapter);
    }
        public ArrayList<File> listFilesWithSubFolders(File dir) {
        ArrayList<File> files = new ArrayList<>();
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory())
                files.addAll(listFilesWithSubFolders(file));
            else
                files.add(file);
        }


        //Сортировка
            if (Objects.equals(MainActivity.sort, "Name")){
                files.sort(Comparator.comparing(File::getName));
            }
            else if (Objects.equals(MainActivity.sort, "Size")){
                files.sort((t, t1) -> (int) (t.lastModified() - t1.lastModified()));
            }
            else if (Objects.equals(MainActivity.sort, "Date")){
                files.sort((t, t1) -> (int) (t.length() - t1.length()));
            }
            else {
                files.sort(Comparator.comparing(File::getName));
            }

        return files;

    }


}
