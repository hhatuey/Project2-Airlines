import { IFlight } from "./IFlight";
import { IUser } from "./IUser";
import { ISeat } from "./ISeat";

export interface ITicket {
    id: number,
    flight: IFlight,
    user: IUser,
    seat: ISeat
}
