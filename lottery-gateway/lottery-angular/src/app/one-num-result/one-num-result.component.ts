import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {ResultNum} from '../resultNum';

@Component({
    selector: 'app-one-num-result',
    templateUrl: './one-num-result.component.html',
    styleUrls: ['./one-num-result.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class OneNumResultComponent implements OnInit {

    @Input() result: ResultNum;

    constructor() {
    }

    ngOnInit() {
    }
}
