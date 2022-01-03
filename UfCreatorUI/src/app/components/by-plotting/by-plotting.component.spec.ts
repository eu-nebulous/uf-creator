import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ByPlottingComponent } from './by-plotting.component';

describe('ByPlottingComponent', () => {
  let component: ByPlottingComponent;
  let fixture: ComponentFixture<ByPlottingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ByPlottingComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ByPlottingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
