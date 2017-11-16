import {Injectable} from '@angular/core';
import {ResultArray} from './resultArray';
import {Token} from './token';

import {HttpClient, HttpHeaders} from '@angular/common/http';

const token = new Token();
const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': token.token
    })
};

@Injectable()
export class MostFreqFiveService {
    private result;
    private resultByDate;
    private lotteryUrl = 'http://localhost:8080/lottery/lottery/most-frequent-five-number';

    constructor(private http: HttpClient) {
    }

    getResult(): ResultArray {
        this.result = new ResultArray();
        this.http
            .get<number>(this.lotteryUrl, httpOptions).subscribe(data => this.result.arr = data['result']);
        console.log('five: ' + this.result.arr);
        return this.result;
    }

    getResultByDate(from: string, to: string): ResultArray {
        const url = this.lotteryUrl + '/' + from + '/' + to;
        this.resultByDate = new ResultArray();
        this.http
            .get<number>(url, httpOptions).subscribe(data => this.resultByDate.arr = data['result']);
        return this.resultByDate;
    }
}
