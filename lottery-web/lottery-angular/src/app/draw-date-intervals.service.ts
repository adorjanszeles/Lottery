import {Injectable} from '@angular/core';
import {ResultString} from './resultString';
import {Token} from './token';

import {HttpClient, HttpHeaders} from '@angular/common/http';

const token = new Token();
const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'text/plain',
        'Authorization': token.token,
    } )
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
            .get(this.lotteryUrl, { headers: new HttpHeaders({
                'Content-Type': 'text/plain',
                'Authorization': token.token,
            } ), responseType: 'text'}).subscribe(response => {
            this.result.interval = response});
        console.log('string: ' + this.result.interval);
        return this.result;
    }
}
