import {inject, TestBed} from '@angular/core/testing';

import {AverageService} from './average.service';

describe('AverageService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [AverageService]
        });
    });

    it('should be created', inject([AverageService], (service: AverageService) => {
        expect(service).toBeTruthy();
    }));
});
