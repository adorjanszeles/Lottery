package com.lottery.model;

import com.lottery.model.mfop_utils.DrawsInTwoDimension;
import com.lottery.model.mfop_utils.Row;

import java.util.Arrays;

/**
 * A Most Frequently Occurring Pairs rule eredményeit gyüjtő osztály.
 */
public class MostFrequentlyOccurringPairsResult {

    private DrawsInTwoDimension lottoArray;
    private DrawsInTwoDimension resultArray;

    public MostFrequentlyOccurringPairsResult(){

        this.lottoArray = new DrawsInTwoDimension();
        this.resultArray = new DrawsInTwoDimension();
        int columnCounter = 89;

        while(columnCounter >= 0){

            Row row = new Row(new Integer[columnCounter]);

            this.lottoArray.addRow(row);
            columnCounter--;
        }

        for(Row arr : this.lottoArray.getRows()){
            Arrays.fill(arr.getColumns(),0);
        }
    }

    /**
     * A lottoArray field teszt print-jére alkalmazható method.
     */
    public void lottoArraySTDOUT(){
        for(int n = 0; n < this.lottoArray.getRows().size(); n++) {
            if( n < 9) {
                System.out.print("0" + (n + 1) + " : num of possible pairs:" + this.lottoArray.getRows().get(n).getColumns().length + " - ");
            }else {
                if(this.lottoArray.getRows().size() < 10) {
                    System.out.print(n + 1 + " : num of possible pairs:0" + this.lottoArray.getRows().get(n).getColumns().length + " - ");
                }else{
                    System.out.print(n + 1 + " : num of possible pairs:" + this.lottoArray.getRows().get(n).getColumns().length + " - ");

                }
            }
            for (int i = 0; i < this.lottoArray.getRows().get(n).getColumns().length; i++) {
                System.out.print(" " + this.lottoArray.getRows().get(n).getColumns()[i] + " ");
            }

            System.out.println();
        }
    }

    /**
     * A resultArray field teszt nyomtatására alkalmazható method.
     */
    public void resultArraySTDOUT(){

        for(Row resultList : this.resultArray.getRows()){
            System.out.println("Pair of " + resultList.getColumns()[0] + " and " + resultList.getColumns()[1] );
        }
    }

    public DrawsInTwoDimension getResultArray() {
        return resultArray;
    }

    public void setResultArray(DrawsInTwoDimension resultArray) {
        this.resultArray = resultArray;
    }

    public DrawsInTwoDimension getLottoArray() {
        return lottoArray;
    }

    public void setLottoArray(DrawsInTwoDimension lottoArray) {
        this.lottoArray = lottoArray;
    }

}
