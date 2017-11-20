import {Injectable} from '@angular/core';
import {ResultCompact} from './resultCompact';

import {HttpClient} from '@angular/common/http';
import {GetTokenService} from "./get-token.service";

@Injectable()
export class MostFreqPairsService {
    private result;
    private resultByDate;
    private lotteryUrl = 'http://localhost:8080/lottery/lottery/most-frequently-occuring-pairs';

    constructor(private http: HttpClient, private getTokenService: GetTokenService) {
    }

    parseResult(resultList: object[][]): number[][] {
        let resultArray = [];
        for (let i = 0; i < resultList.length; i++) {
            resultArray.push(resultList[i]['columns'])
        }
        return resultArray;
    }

    getResult(): ResultCompact {
        this.result = new ResultCompact();
        this.http
            .get<number>(this.lotteryUrl, this.getTokenService.getHttpOption()).subscribe(data => {
            this.result.arr = this.parseResult(data['rows']);
        });
        return this.result;
    }

    getResultByDate(from: string, to: string): ResultCompact {
        const url = this.lotteryUrl + '/' + from + '/' + to;
        this.resultByDate = new ResultCompact();
        let temp;
        this.http
            .get<number>(url, this.getTokenService.getHttpOption()).subscribe(data => temp = data);
        this.resultByDate.arr = this.parseResult(temp);
        return this.resultByDate;
    }
}
