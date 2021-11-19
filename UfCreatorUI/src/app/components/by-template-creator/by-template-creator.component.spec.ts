import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ByTemplateCreatorComponent } from './by-template-creator.component';

describe('ByTemplateCreatorComponent', () => {
  let component: ByTemplateCreatorComponent;
  let fixture: ComponentFixture<ByTemplateCreatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ByTemplateCreatorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ByTemplateCreatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
