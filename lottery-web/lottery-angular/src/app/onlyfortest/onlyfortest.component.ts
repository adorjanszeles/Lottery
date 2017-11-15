import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ServiceHubService} from './../service-hub.service';

@Component({
    selector: 'app-onlyfortest',
    templateUrl: './onlyfortest.component.html',
    styleUrls: ['./onlyfortest.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class OnlyfortestComponent implements OnInit {

    private result;
    private resultByFilter;

    constructor(private serviceHubService: ServiceHubService) {
    }

    ngOnInit() {
        this.getResult();
        this.getResultByFilter('2012-01-01', '2017-01-01');
    }

    getResult(): void {
        this.result = this.serviceHubService.getService('Average');
    }

    getResultByFilter(from: string, to: string): void {
        this.resultByFilter = this.serviceHubService
            .getService('Average', from, to,);
    }
}
