import {inject, TestBed} from '@angular/core/testing';

import {GetTokenService} from './get-token.service';

describe('GetTokenService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [GetTokenService]
        });
    });

    it('should be created', inject([GetTokenService], (service: GetTokenService) => {
        expect(service).toBeTruthy();
    }));
});
