import { ComponentFixture, TestBed } from '@angular/core/testing';

<<<<<<<< HEAD:src/app/pages/contacto/contacto.component.spec.ts
import { ContactoComponent } from './contacto.component';

describe('ContactoComponent', () => {
  let component: ContactoComponent;
  let fixture: ComponentFixture<ContactoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ContactoComponent]
    });
    fixture = TestBed.createComponent(ContactoComponent);
========
import { ProductoComponent } from './producto.component';

describe('ProductoComponent', () => {
  let component: ProductoComponent;
  let fixture: ComponentFixture<ProductoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProductoComponent]
    });
    fixture = TestBed.createComponent(ProductoComponent);
>>>>>>>> main:src/app/pages/producto/producto.component.spec.ts
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
