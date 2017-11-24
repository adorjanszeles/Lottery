import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {ResultArray} from '../resultArray';

@Component({
    selector: 'app-five-num-result',
    templateUrl: './five-num-result.component.html',
    styleUrls: ['./five-num-result.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class FiveNumResultComponent implements OnInit {

    @Input() result: ResultArray;

    constructor() {
    }

    ngOnInit() {
    }

}
