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
import {SwapiTestService} from './swapi-test.service';

import {HttpClientModule} from '@angular/common/http';

@NgModule({
  declarations: [
      AppComponent,
      OnlyfortestComponent
  ],
  imports: [
      BrowserModule,
      HttpClientModule
  ],
    providers: [AverageService,
        ServiceHubService,
        MostFreqFiveService,
        RearestFiveService,
        MostFreqPairsService,
        FourMatchFiveRatioService,
        TimeBetweenTwoFiveService,
        DrawDateIntervalsService,
        SwapiTestService,
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }
