import {inject, TestBed} from '@angular/core/testing';

import {SwapiTestService} from './swapi-test.service';

describe('SwapiTestService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [SwapiTestService]
        });
    });

    it('should be created', inject([SwapiTestService], (service: SwapiTestService) => {
        expect(service).toBeTruthy();
    }));
});
