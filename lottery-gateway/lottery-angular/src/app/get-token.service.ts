import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
    headers: new HttpHeaders()
        .set('Authorization', 'Basic bG90dGVyeS1jbGllbnQ6c2VjcmV0')
        .set("content-type", "application/x-www-form-urlencoded")
};

let body = 'grant_type=password&username=admin&password=admin';

@Injectable()
export class GetTokenService {

    private lotteryUrl = 'http://localhost:1234/gateway/auth/get-token';

    constructor(private http: HttpClient) {
    }

    getToken(): void {
        this.http
            .post(this.lotteryUrl, body, httpOptions).subscribe(data => {
            localStorage.setItem('access_token', 'Bearer ' + data['access_token']);
        });
    }

    getHttpOption(): any {
        return {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Authorization': localStorage.getItem('access_token')
            })
        }
    }
}
