import {inject, TestBed} from '@angular/core/testing';

import {FourMatchFiveRatioService} from './four-match-five-ratio.service';

describe('FourMatchFiveRatioService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [FourMatchFiveRatioService]
        });
    });

    it('should be created', inject([FourMatchFiveRatioService], (service: FourMatchFiveRatioService) => {
        expect(service).toBeTruthy();
    }));
});
