import {Injectable} from '@angular/core';
import {ResultString} from "./result-string";
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
export class DrawDateIntervalsService {
    private result;
    private lotteryUrl = 'http://localhost:8080/lottery/lottery/get-date-intervals';

    constructor(private http: HttpClient) {
    }

    getResult(): ResultString {
        this.result = new ResultString();
        this.http
            .get<number>(this.lotteryUrl, httpOptions).subscribe(data => this.result.interval = data);
        return this.result;
    }
}
