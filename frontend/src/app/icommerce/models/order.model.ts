import { ProductOrders } from './product-orders.model';
import { ProductOrder } from './product-order.model';
export class Order {
  id: number;
  dateCreated: Date;
  status: string;
  productOrders: ProductOrder[];

  constructor(
    id: number,
    dateCreated: Date,
    status: string,
    productOrders: ProductOrder[]
  ) {
    this.id = id;
    this.dateCreated = dateCreated;
    this.status = status;
    this.productOrders = productOrders;
  }
}
