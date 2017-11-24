import {Injectable} from '@angular/core';
import {ResultArray} from './resultArray';
import {GetTokenService} from "./get-token.service";


import {HttpClient} from '@angular/common/http';

@Injectable()
export class RearestFiveService {
    private result;
    private resultByDate;
    private lotteryUrl = 'http://localhost:1234/gateway/gateway/rearest-five-number';

    constructor(private http: HttpClient, private getTokenService: GetTokenService) {
    }

    getResult(): ResultArray {
        this.result = new ResultArray();
        this.http
            .get<number>(this.lotteryUrl, this.getTokenService.getHttpOption()).subscribe(data => this.result.arr = data['result']);
        return this.result;
    }

    getResultByDate(from: string, to: string): ResultArray {
        const url = this.lotteryUrl + '/' + from + '/' + to;
        this.resultByDate = new ResultArray();
        this.http
            .get<number>(url, this.getTokenService.getHttpOption()).subscribe(data => this.resultByDate.arr = data['result']);
        return this.resultByDate;
    }
}
