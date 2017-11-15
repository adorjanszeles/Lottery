import {inject, TestBed} from '@angular/core/testing';

import {MostFreqFiveService} from './most-freq-five.service';

describe('MostFreqFiveService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [MostFreqFiveService]
        });
    });

    it('should be created', inject([MostFreqFiveService], (service: MostFreqFiveService) => {
        expect(service).toBeTruthy();
    }));
});
