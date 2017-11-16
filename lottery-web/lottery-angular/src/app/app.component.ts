import {Component} from '@angular/core';
import {TITLES} from "./rule-titles";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
    titles = TITLES;
}
