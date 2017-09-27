package com.lottery.model;

/**
 * A heti lottószám húzásokat olvassa be fileból
 */

public interface LotteryFileReader {

    /**
     * beolvassa a csv fileból az adatokat
     *
     * @param filePath a csv file helye
     */
    void readFromFile(String filePath);

}
