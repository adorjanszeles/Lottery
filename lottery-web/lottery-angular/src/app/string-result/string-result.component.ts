import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {ResultString} from '../resultString';

@Component({
  selector: 'app-string-result',
  templateUrl: './string-result.component.html',
  styleUrls: ['./string-result.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class StringResultComponent implements OnInit {

    @Input() result: ResultString;

    constructor() {
    }

    ngOnInit() {
    }

}
