import {Injectable} from '@angular/core';
import {ResultCompact} from "./result-compact";
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
export class MostFreqPairsService {
    private result;
    private resultByDate;
    private lotteryUrl = 'http://localhost:8080/lottery/lottery/most-frequently-occuring-pairs';

    constructor(private http: HttpClient) {
    }

    getResult(): ResultCompact {
        this.result = new ResultCompact();
        this.http
            .get<number>(this.lotteryUrl, httpOptions).subscribe(data => this.result.num = data['rows'][0]['columns']);
        return this.result;
    }

    getResultByDate(from: string, to: string): ResultCompact {
        const url = this.lotteryUrl + '/' + from + '/' + to;
        this.resultByDate = new ResultCompact();
        this.http
            .get<number>(url, httpOptions).subscribe(data => this.resultByDate.num = data['rows'][0]['columns']);
        return this.resultByDate;
    }
}
