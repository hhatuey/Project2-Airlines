import { ICity } from "./ICity";

export interface IFlight {
    id: number,
    name: string,
    takeoff: Date,
    eta: Date,
    destination: ICity,
    origin: ICity,
    seats: number;
}