import {inject, TestBed} from '@angular/core/testing';

import {MostFreqPairsService} from './most-freq-pairs.service';

describe('MostFreqPairsService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [MostFreqPairsService]
        });
    });

    it('should be created', inject([MostFreqPairsService], (service: MostFreqPairsService) => {
        expect(service).toBeTruthy();
    }));
});
