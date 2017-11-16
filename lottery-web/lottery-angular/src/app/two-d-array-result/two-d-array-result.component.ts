import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {ResultCompact} from '../resultCompact';

@Component({
  selector: 'app-two-d-array-result',
  templateUrl: './two-d-array-result.component.html',
  styleUrls: ['./two-d-array-result.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class TwoDArrayResultComponent implements OnInit {

    @Input() result: ResultCompact;

    constructor() {
    }

    ngOnInit() {
    }

}
