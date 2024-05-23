import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IcommerceComponent } from './icommerce.component';

describe('IcommerceComponent', () => {
  let component: IcommerceComponent;
  let fixture: ComponentFixture<IcommerceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ IcommerceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IcommerceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
