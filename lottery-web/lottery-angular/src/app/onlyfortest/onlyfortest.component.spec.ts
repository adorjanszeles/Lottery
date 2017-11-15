import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {OnlyfortestComponent} from './onlyfortest.component';

describe('OnlyfortestComponent', () => {
    let component: OnlyfortestComponent;
    let fixture: ComponentFixture<OnlyfortestComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [OnlyfortestComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(OnlyfortestComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
