export interface User {
    id: number,
    username: string,
    password: string,
    firstname: string,
    lastname: string,
    roleid: number,
    email: string
}

export interface Flight {
    id: number,
    name: string,
    takeoff: Date,
    eta: Date,
    destination: string,
    origin: string,
    seats: number;
}

export interface Seat {
    id: number,
    flight: Flight,
    available: boolean
}

export interface Ticket {
    id: number,
    flight: Flight,
    user: User,
    seat: Seat
}

