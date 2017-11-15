import {Injectable} from '@angular/core';
import {AverageService} from './average.service';
import {MostFreqFiveService} from './most-freq-five.service';
import {RearestFiveService} from './rearest-five.service';
import {MostFreqPairsService} from './most-freq-pairs.service';
import {FourMatchFiveRatioService} from './four-match-five-ratio.service';
import {DrawDateIntervalsService} from './draw-date-intervals.service';
import {TimeBetweenTwoFiveService} from './time-between-two-five.service';
import {SwapiTestService} from './swapi-test.service';


@Injectable()
export class ServiceHubService {

    constructor(private averageService: AverageService,
                private mostFreqFiveService: MostFreqFiveService,
                private rearestFiveService: RearestFiveService,
                private mostFreqPairsService: MostFreqPairsService,
                private fourMatchFiveRatioService: FourMatchFiveRatioService,
                private timeBetweenTwoFiveService: TimeBetweenTwoFiveService,
                private drawDateIntervalsService: DrawDateIntervalsService,
                private swapiTestService: SwapiTestService) {
    }

    getService(title: string, from = '', to = ''): any {
        if (from.length === 0 && to.length === 0) {
            return this.getResult(title);
        } else {
            return this.getResultByDate(from, to, title);
        }

    }

    getResultByDate(from: string, to: string, title: string): any {
        switch (title) {
            case 'Average':
                return this.averageService.getResultByDate(from, to);
            case 'Most Frequent Five Number':
                return this.mostFreqFiveService.getResultByDate(from, to);
            case 'Most Frequent Pairs':
                return this.mostFreqPairsService.getResultByDate(from, to);
            case 'Rarest Five Number':
                return this.rearestFiveService.getResultByDate(from, to);
            case 'Average Time Between Five Match':
                return this.timeBetweenTwoFiveService.getResultByDate(from, to);
            case 'Four Match Ratio to Five':
                return this.fourMatchFiveRatioService.getResultByDate(from, to);
            case 'Swapi Test':
                return this.swapiTestService.getDeathStar(from, to);
        }
    }

    getResult(title: string): any {
        switch (title) {
            case 'Average':
                return this.averageService.getResult();
            case 'Most Frequent Five Number':
                return this.mostFreqFiveService.getResult();
            case 'Most Frequent Pairs':
                return this.mostFreqPairsService.getResult();
            case 'Rarest Five Number':
                return this.rearestFiveService.getResult();
            case 'Average Time Between Five Match':
                return this.timeBetweenTwoFiveService.getResult();
            case 'Four Match Ratio to Five':
                return this.fourMatchFiveRatioService.getResult();
            case 'Draw Date Intervals':
                return this.drawDateIntervalsService.getResult();
            case 'Swapi Test':
                return this.swapiTestService.getLuke();
        }
    }

}
