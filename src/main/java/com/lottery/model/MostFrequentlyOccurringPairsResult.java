package com.lottery.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A Most Frequently Occurring Pairs rule eredményeit gyüjtő osztály.
 */
public class MostFrequentlyOccurringPairsResult {

    private Integer[][] result;

    public List<Integer[]> lottoArray;

    public MostFrequentlyOccurringPairsResult(){
        this.lottoArray = makeLottoFiveArray();
    }

    public Integer[][] getResult() {
        return result;
    }

    public void setResult(Integer[][] result) {
        this.result = result;
    }


    /**
     * @return Egy List-et ad vissza 89db Integer[]-el feltöltve.
     * Az Integer[]-ek összes mezelyének 0 kezdőértéket ad.
     */
    private List<Integer[]> makeLottoFiveArray(){
        List<Integer[]> lottoArray = new ArrayList<Integer[]>();
        int columnCounter = 89;

        while(columnCounter >= 0){
            lottoArray.add(new Integer[columnCounter]);
            columnCounter--;
        }

        for(Integer[] arr : lottoArray){
            Arrays.fill(arr,0);
        }

        return lottoArray;
    }

    /**
     * Csinál egy átlátható printet a lottoArray-emből
     */
    public void lottoArraySTDOUT(){
        for(int n = 0; n < this.lottoArray.size(); n++) {
            if( n < 9) {
                System.out.print("0" + (n + 1) + " : num of possible pairs:" + this.lottoArray.get(n).length + " - ");
            }else {
                if(this.lottoArray.get(n).length < 10) {
                    System.out.print(n + 1 + " : num of possible pairs:0" + this.lottoArray.get(n).length + " - ");
                }else{
                    System.out.print(n + 1 + " : num of possible pairs:" + this.lottoArray.get(n).length + " - ");

                }
            }
            for (int i = 0; i < this.lottoArray.get(n).length; i++) {
                System.out.print(" " + this.lottoArray.get(n)[i] + " ");
            }

            System.out.println();
        }
    }

    public List<Integer[]> getLottoArray() {
        return lottoArray;
    }



}
