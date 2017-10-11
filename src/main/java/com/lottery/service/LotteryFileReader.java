package com.lottery.service;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * A heti lottószám húzásokat olvassa be fileból
 */

public interface LotteryFileReader {

    /**
     * beolvassa a csv fileból az adatokat
     *
     * @param file a csv file
     */
    void readFromFile(File file) throws FileNotFoundException;

}
