import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoriasProductosComponent } from './categorias-productos.component';

describe('CategoriasProductosComponent', () => {
  let component: CategoriasProductosComponent;
  let fixture: ComponentFixture<CategoriasProductosComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CategoriasProductosComponent]
    });
    fixture = TestBed.createComponent(CategoriasProductosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
