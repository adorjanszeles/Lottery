import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {AverageService} from './average.service';
import {ServiceHubService} from './service-hub.service';
import {MostFreqFiveService} from './most-freq-five.service';
import {RearestFiveService} from './rearest-five.service';
import {MostFreqPairsService} from './most-freq-pairs.service';
import {FourMatchFiveRatioService} from './four-match-five-ratio.service';
import {TimeBetweenTwoFiveService} from './time-between-two-five.service';
import {DrawDateIntervalsService} from './draw-date-intervals.service';
import {OnlyfortestComponent} from './onlyfortest/onlyfortest.component';
import {GetTokenService} from "./get-token.service";

import {HttpClientModule} from '@angular/common/http';
import {StringResultComponent} from './string-result/string-result.component';
import {TwoDArrayResultComponent} from './two-d-array-result/two-d-array-result.component';
import {FiveNumResultComponent} from './five-num-result/five-num-result.component';
import {MessagesComponent} from './messages/messages.component';
import {RuleComponent} from './rule/rule.component';
import {OneNumResultComponent} from './one-num-result/one-num-result.component';
import {FormsModule} from '@angular/forms';
import {MessageService} from './message.service';
import {ExceptionMessageComponent} from './exception-message/exception-message.component';
import {IntervalComponent} from "./interval/interval.component";

@NgModule({
    declarations: [
        AppComponent,
        OnlyfortestComponent,
        AppComponent,
        OneNumResultComponent,
        RuleComponent,
        MessagesComponent,
        FiveNumResultComponent,
        TwoDArrayResultComponent,
        StringResultComponent,
        ExceptionMessageComponent,
        IntervalComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        FormsModule
    ],
    providers: [AverageService,
        ServiceHubService,
        MostFreqFiveService,
        RearestFiveService,
        MostFreqPairsService,
        FourMatchFiveRatioService,
        TimeBetweenTwoFiveService,
        DrawDateIntervalsService,
        MessageService,
        GetTokenService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
