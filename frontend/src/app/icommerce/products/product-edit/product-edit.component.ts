import { ActivatedRoute, Params, Router } from '@angular/router';
import { IcommerceService } from './../../services/icommerce.service';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Product } from '../../models/product.model';
import { ProductOrder } from '../../models/product-order.model';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.scss'],
})
export class ProductEditComponent implements OnInit {
  product: Product;
  productForm: FormGroup;

  constructor(
    private ecommerceService: IcommerceService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.product = this.ecommerceService.SelectedProductOrder.product;
    this.initForm();
  }

  private initForm() {
    this.productForm = new FormGroup({
      id: new FormControl(this.product.id),
      name: new FormControl(this.product.name),
      price: new FormControl(this.product.price),
      brand: new FormControl(this.product.brand),
      color: new FormControl(this.product.color),
      description: new FormControl(this.product.description),
    });
  }

  getImage() {
    return 'data:image/jpeg;base64,' + this.product.pictureData;
  }

  onCancel() {
    this.router.navigate(['../'], { relativeTo: this.route });
  }

  onSubmit() {
    this.product.name = this.productForm.value['name'];
    this.product.brand = this.productForm.value['brand'];
    this.product.description = this.productForm.value['description'];
    this.product.color = this.productForm.value['color'];
    this.product.price = this.productForm.value['price'];
    this.ecommerceService.saveProduct(this.product).subscribe();
    this.router.navigate(['../'], { relativeTo: this.route });
  }
}
