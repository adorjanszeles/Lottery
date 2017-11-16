import {Injectable} from '@angular/core';
import {ResultCompact} from './resultCompact';
import {Token} from './token';

import {HttpClient, HttpHeaders} from '@angular/common/http';
import {forEach} from "@angular/router/src/utils/collection";

const token = new Token();
const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': token.token
    })
};

@Injectable()
export class MostFreqPairsService {
    private result;
    private resultByDate;
    private lotteryUrl = 'http://localhost:8080/lottery/lottery/most-frequently-occuring-pairs';

    constructor(private http: HttpClient) {
    }

    parseResult(resultList: object[][]): number[][] {
        let resultArray = [];
        for(let i=0; i<resultList.length; i++) {
            resultArray.push(resultList[i]['columns'])
        }
        return resultArray;
    }

    getResult(): ResultCompact {
        this.result = new ResultCompact();
        this.http
            .get<number>(this.lotteryUrl, httpOptions).subscribe(data => {
                this.result.arr = this.parseResult(data['rows']);
            });
        return this.result;
    }

    getResultByDate(from: string, to: string): ResultCompact {
        const url = this.lotteryUrl + '/' + from + '/' + to;
        this.resultByDate = new ResultCompact();
        let temp;
        this.http
            .get<number>(url, httpOptions).subscribe(data => temp = data);
        this.resultByDate.arr = this.parseResult(temp);
        return this.resultByDate;
    }
}
