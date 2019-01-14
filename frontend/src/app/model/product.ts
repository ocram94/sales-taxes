import { Tax } from "./tax";

export class Product{
    qta: number;
	description: string;
	price: number;
	tax= new Tax();
	
	public constructor() {}
}