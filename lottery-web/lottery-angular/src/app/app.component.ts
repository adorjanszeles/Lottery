import {Component} from '@angular/core';
import {TITLES} from './rule-titles';
import {GetTokenService} from './get-token.service';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent {
    titles = TITLES;
    token: string;

    constructor(private getTokenService: GetTokenService) {
    }

    ngOnInit() {
        this.getTokenService.getToken();
    }
}
