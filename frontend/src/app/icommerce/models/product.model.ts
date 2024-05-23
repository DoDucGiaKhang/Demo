export class Product {
  id: number;
  name: string;
  price: number;
  pictureId: string;
  pictureData: Uint8Array;
  brand: string;
  color: string;
  description: string;

  constructor(
    id: number,
    name: string,
    price: number,
    pictureId: string,
    pictureData: Uint8Array,
    brand: string,
    color: string,
    description: string
  ) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.pictureId = pictureId;
    this.pictureData = pictureData;
    this.brand = brand;
    this.color = color;
    this.description = description;
  }
}
