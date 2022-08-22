export class Product{
    public id : number;
    public name : string;
    public price : number;
    public rating : number;
    constructor(id : number, name : string, price : number, rating : number){
        this.id=id;
        this.name=name;
        this.price=price;
        this.rating=rating;
    }
}

export class GPU{
    public id : number;
    public name : string;
    public price : number;
    public rating : number;
    public chipset : string;
    public color : string;
    public memory : number;
    public clockspeed : number;
    public length : number;
    constructor(id : number, name : string, price : number, rating : number, chipset : string, color : string, memory : number, clockspeed : number, length : number){
        this.id=id;
        this.name=name;
        this.price=price;
        this.rating=rating;
        this.chipset = chipset;
        this.color = color;
        this.memory = memory;
        this.clockspeed = clockspeed;
        this.length = length;
    }
}

export class RAM{
    public id : number;
    public name : string;
    public price : number;
    public rating : number;
    public speed : number;
    public capacity : number;
    public overhead : number;
    public color : string;
    constructor(id : number, name : string, price : number, rating : number, speed: number, capacity: number, overhead:number, color:string){
        this.id=id;
        this.name=name;
        this.price=price;
        this.rating=rating;
        this.speed = speed;
        this.capacity = capacity;
        this.overhead = overhead;
        this.color = color;
    }
}

export class Mobo{
    public id : number;
    public name : string;
    public price : number;
    public rating : number;
    public socket : string;
    public formfactor : string;
    public maxram : number;
    public channels : number;
    public color : string;
    constructor(id : number, name : string, price : number, rating : number, socket : string, formfactor : string, maxram : number, channels : number, color : string){
        this.id=id;
        this.name=name;
        this.price=price;
        this.rating=rating;
        this.socket = socket;
        this.formfactor = formfactor;
        this.maxram = maxram;
        this.channels = channels;
        this.color = color;
    }
}

export class CPU{
    public id : number;
    public name : string;
    public price : number;
    public rating : number;
    public socket : string;
    public corecount : number;
    public coreclock : number;
    public tdp : number;
    public igpu : boolean;
    constructor(id : number, name : string, price : number, rating : number, socket : string, corecount : number, coreclock : number, tdp:number, igpu:boolean){
        this.id=id;
        this.name=name;
        this.price=price;
        this.rating=rating;
        this.socket = socket;
        this.corecount = corecount;
        this.coreclock = coreclock;
        this.tdp = tdp;
        this.igpu = igpu;
    }
}

