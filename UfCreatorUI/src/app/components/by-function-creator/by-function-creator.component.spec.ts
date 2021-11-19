import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ByFunctionCreatorComponent } from './by-function-creator.component';

describe('ByFunctionCreatorComponent', () => {
  let component: ByFunctionCreatorComponent;
  let fixture: ComponentFixture<ByFunctionCreatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ByFunctionCreatorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ByFunctionCreatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
