package com.example.horopter.sangallery;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Horopter on 2/11/2016.
 */
public class ImageFinder {

    public static List<File> dfsImageFind(File root)
    {
        List<File> images = new ArrayList<>();
        File file = new File(root.getAbsolutePath());
        if (file.canRead())
        {
        }
        File[] files = root.listFiles();

        if (files != null)
        {
            for (int i = 0; i < files.length; i++)
            {
                if (files[i].isDirectory())
                {
                    images.addAll(dfsImageFind(files[i]));
                }
                else
                {
                    if (files[i].getName().endsWith(".jpg")||files[i].getName().endsWith(".jpeg")||files[i].getName().endsWith(".png")||files[i].getName().endsWith(".gif")) {
                        images.add(files[i]);
                    }
                }
            }
        }
        else
        {
        }
        return images;
    }
}

