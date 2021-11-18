import { IFlight } from "./IFlight";

export interface ISeat {

    id: number,
    flight: IFlight,
    seatAvailable: boolean

}