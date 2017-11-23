import {inject, TestBed} from '@angular/core/testing';

import {RearestFiveService} from './rearest-five.service';

describe('RearestFiveService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [RearestFiveService]
        });
    });

    it('should be created', inject([RearestFiveService], (service: RearestFiveService) => {
        expect(service).toBeTruthy();
    }));
});
