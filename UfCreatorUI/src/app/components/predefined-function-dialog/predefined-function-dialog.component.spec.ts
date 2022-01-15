import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PredefinedFunctionDialogComponent} from './predefined-function-dialog.component';

describe('SidenavComponent', () => {
  let component: PredefinedFunctionDialogComponent;
  let fixture: ComponentFixture<PredefinedFunctionDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PredefinedFunctionDialogComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PredefinedFunctionDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
