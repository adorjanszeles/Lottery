import {inject, TestBed} from '@angular/core/testing';

import {ServiceHubService} from './service-hub.service';

describe('ServiceHubService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [ServiceHubService]
        });
    });

    it('should be created', inject([ServiceHubService], (service: ServiceHubService) => {
        expect(service).toBeTruthy();
    }));
});
