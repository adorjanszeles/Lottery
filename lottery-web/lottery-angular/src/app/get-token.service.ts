import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from '@angular/common/http';

const httpOptions = {
    headers: new HttpHeaders().set('Authorization', 'Basic bG90dGVyeS1jbGllbnQ6c2VjcmV0')
};

@Injectable()
export class GetTokenService {

    private lotteryUrl = 'http://localhost:8000/auth/oauth/token?grant_type=password&username=admin&password=admin';

    constructor(private http: HttpClient) {
    }

    getToken(): void {
        this.http
            .post(this.lotteryUrl, {}, httpOptions).subscribe(data => {
            console.log(data['access_token']);
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
