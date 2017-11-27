import {Injectable} from '@angular/core';
import {ResultString} from './resultString';

import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class DrawDateIntervalsService {
    private result;
    private lotteryUrl = 'http://localhost:1234/gateway/gateway/get-date-intervals';

    constructor(private http: HttpClient) {
    }

    getResult(): ResultString {
        this.result = new ResultString();
        this.http
            .get(this.lotteryUrl, {
                headers: new HttpHeaders({
                    'Content-Type': 'text/plain',
                    'Authorization': localStorage.getItem('access_token'),
                }), responseType: 'text'
            }).subscribe(response => {
            this.result.interval = response
            },
            err => {
                this.result.error = err.error;
            });
        return this.result;
    }
}
