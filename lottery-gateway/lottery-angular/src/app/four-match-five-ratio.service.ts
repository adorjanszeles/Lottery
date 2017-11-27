import {Injectable} from '@angular/core';
import {ResultNum} from './resultNum';
import {GetTokenService} from "./get-token.service";

import {HttpClient} from '@angular/common/http';

@Injectable()
export class FourMatchFiveRatioService {
    private result;
    private resultByDate;
    private lotteryUrl = 'http://localhost:1234/gateway/gateway/four-match-ratio-to-five-match';

    constructor(private http: HttpClient, private getTokenService: GetTokenService) {
    }

    getResult(): ResultNum {
        this.result = new ResultNum();
        this.http
            .get<number>(this.lotteryUrl, this.getTokenService.getHttpOption()).subscribe(data => {
                this.result.num = data['result'].toFixed(1)
            },
            err => {
                this.result.error = err.error;
            });
        return this.result;
    }

    getResultByDate(from: string, to: string): ResultNum {
        const url = this.lotteryUrl + '/' + from + '/' + to;
        this.resultByDate = new ResultNum();
        this.http
            .get<number>(url, this.getTokenService.getHttpOption()).subscribe(data => {
                this.resultByDate.num = data['result'].toFixed(1)
            },
            err => {
                this.resultByDate.error = err.error;
            });
        return this.resultByDate;
    }
}
