import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class SwapiTestService {

    private results;

    private swapiUrl = 'https://swapi.co/api/people/1';
    private planetUrl = 'https://swapi.co/api/';

    constructor(private http: HttpClient) {
    }

    getLuke(): Observable<string> {
        return this.http.get<string>(this.swapiUrl);
    }

    getDeathStar(from: string, to: string): Observable<string> {
        const url = this.planetUrl + from + '/' + to;
        return this.http.get<string>(url);
    }
}
