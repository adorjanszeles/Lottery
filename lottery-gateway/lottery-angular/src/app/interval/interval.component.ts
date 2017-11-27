import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {ServiceHubService} from '../service-hub.service';

@Component({
    selector: 'app-interval',
    templateUrl: './interval.component.html',
    styleUrls: ['./interval.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class IntervalComponent implements OnInit {
    @Input() title: string = "Draw Date Intervals";
    @Input() from = '';
    @Input() to = '';
    @Input() resultType: string = "string result";
    result: any;
    filter = false;
    resultActive = false;

    constructor(private serviceHubService: ServiceHubService) {
    }

    ngOnInit() {
    }

    getResult(): void {
        this.result = this.serviceHubService.getService(this.title, this.from, this.to);
    }

    addClass() {
        this.resultActive = true;
    }

    checkCheckbox($event, title: string): void {
        if ($event.toElement.checked === true) {
            this.filter = true;
        } else {
            this.filter = false;
            this.from = '';
            this.to = '';

        }
    }


}