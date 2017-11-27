import {Component, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
    selector: 'app-exception-message',
    templateUrl: './exception-message.component.html',
    styleUrls: ['./exception-message.component.css'],
    encapsulation: ViewEncapsulation.None
})
export class ExceptionMessageComponent implements OnInit {

    private exceptionMessage;

    constructor() {
    }

    ngOnInit() {
    }

}
