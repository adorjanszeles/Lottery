import {inject, TestBed} from '@angular/core/testing';

import {DrawDateIntervalsService} from './draw-date-intervals.service';

describe('DrawDateIntervalsService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [DrawDateIntervalsService]
        });
    });

    it('should be created', inject([DrawDateIntervalsService], (service: DrawDateIntervalsService) => {
        expect(service).toBeTruthy();
    }));
});
