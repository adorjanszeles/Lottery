import {inject, TestBed} from '@angular/core/testing';

import {TimeBetweenTwoFiveService} from './time-between-two-five.service';

describe('TimeBetweenTwoFiveService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [TimeBetweenTwoFiveService]
        });
    });

    it('should be created', inject([TimeBetweenTwoFiveService], (service: TimeBetweenTwoFiveService) => {
        expect(service).toBeTruthy();
    }));
});
