/*
 * 
 */

import axios from "axios";
import { SyntheticEvent, useEffect, useState } from "react";
import { useLocation, useHistory } from "react-router";
import { ITicket } from "../Interfaces/ITicket";

export const TicketDisplay: React.FC<ITicket> = (ticket: ITicket) => {

    let location = useLocation<ITicket>();
    const { id, flight, seat, user } = location.state;
    let history = useHistory();

    let [ticketId, setTicketId] = useState(id);
    let [ticketFlight, setTicketFlight] = useState(flight);
    let [ticketSeat, setTicketSeat] = useState(seat);
    let [ticketUser, setTicketUser] = useState(user);

    let [theTicket, setTheTicket] = useState(location.state);

    useEffect(() => {
        setTicketId(ticketId);
        setTicketFlight(ticketFlight)
        setTicketSeat(ticketSeat);
        setTicketUser(ticketUser);
    }, [])

    function clickConfirm(event: SyntheticEvent) {
        event.preventDefault();
        update();
    }

    function clickOK(event: SyntheticEvent) {
        event.preventDefault();        
        history.push('/book');
    }

    async function update() { 
        console.log(theTicket);
        let res = await axios.post('http://localhost:8080/tickets/create', theTicket);
        console.log(res.data);        
        setTicketId(res.data.ticketId);
        document.getElementById('showticketnumber')?.classList.replace('d-none', 'd-block');
    }

    return (
        <div className="container pt-5 ">
            <div className="container bg-white shadow p-3 rounded">

                <h1 className="fw-light">Confirm Booking</h1>
                <hr />
                <form className="needs-validation" noValidate>
                    <fieldset>
                        <div>
                            <label htmlFor="ticketNum">Ticket Number: </label><br />
                            <input type="text" name="ticketNum" readOnly value={ticketId} />
                        </div>
                        <label htmlFor="flightName">Flight Name:</label><br />
                        <input type="text" name="flightName" readOnly value={ticketFlight.name} /><br />
                        <label htmlFor="origin">From:&nbsp;</label>

                        <input type="text" name="origin" readOnly value={ticketFlight.origin.name} /><br />
                        <label htmlFor="destination">To:&nbsp;</label>
                        <input type="text" name="destination" readOnly value={ticketFlight.destination.name} />

                        <div className="col-md-6">
                            <label htmlFor="seatNum">Seat Number: </label><br />
                            <input type="text" name="seatNum" readOnly value={ticketSeat.id} />
                        </div>
                        <div className="col-md-6">
                            <label htmlFor="userNum">Passenger: </label><br />
                            <input type="text" name="userNum" readOnly value={ticketUser.firstname + ' ' + ticketUser.lastname} />
                        </div>
                    </fieldset>
                    <hr />
                    <div className="d-grid gap-2 col-6 mx-auto">
                        <button className="btn btn-primary" type="button" onClick={clickConfirm}>Confirm</button>
                        <button className="btn btn-primary" type="button">Cancel</button>
                    </div>
                </form>
            </div>
            <div id="showticketnumber" className="d-none container w-100 bg-body position-absolute top-50 start-50 translate-middle rounded shadow">
                <div className="container text-center">
                    <h1 className="fw-light">Your ticket number</h1>
                    <hr />
                        <h2>{ticketId}</h2>
                    <hr />
                    <button className="btn btn-primary" type="button" onClick={clickOK}>OK</button>
                </div>
            </div>
        </div>
    );
}