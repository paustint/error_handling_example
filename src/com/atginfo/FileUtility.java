package com.atginfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by paustint on 11/4/2015.
 */
public class FileUtility {

    public static BufferedReader getFileReader(String filePath) throws FileNotFoundException {
        return new BufferedReader(new FileReader(filePath));
    }
}
