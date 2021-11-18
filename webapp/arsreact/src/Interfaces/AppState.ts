import { IFlight } from "./IFlight";
import { ISeat } from "./ISeat";
import { ITicket } from "./ITicket";
import { IUser } from "./IUser";

export type AppState = {
    user: IUser,
    ticket: ITicket[],
    flight: IFlight[],
    seat: ISeat[]
}