// @ts-ignore
import axios from "axios";
import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from 'react-redux';
import { useHistory, useLocation } from "react-router-dom";
import { IFlight } from "../../Interfaces/IFlight";
import { ISeat } from "../../Interfaces/ISeat";
import { ITicket } from "../../Interfaces/ITicket";
import { IUser } from "../../Interfaces/IUser";

import "../Seat/Seat.css";

// async function getData(id:number){
// let res = await axios.get('http://localhost:8080/flight/getby?id=' + flightId);
// console.log(res.data); //selected flight
// let flight:IFlight = res.data;
// res = await axios('http://localhost:8080/seat/get/flight/seats?id=' + flightId);
// console.log(res.data); //number of seats
// setSeats(res.data);
// }
export const SeatDisplay: React.FC<IFlight> = (flight: IFlight) => {

  const appState = useSelector<any, any>((state) => state); // getting current logged user
  const location = useLocation<IFlight>(); // getting flight information sent by book component
  let history = useHistory();

  let [theFlight, setTheFlight] = useState<IFlight>(location.state);
  let [seats, setSeats] = useState<ISeat[]>([]);
  let [theUser, setTheUser] = useState<IUser>(appState.user);


  useEffect(() => {
    axios.get('http://localhost:8080/flight/getby?id=' + theFlight.id)
      .then(res => {
        setTheFlight(res.data)
        axios.get('http://localhost:8080/seat/get/flight/seats?id=' + theFlight.id)
          .then(res => {
            setSeats(res.data);
            // setTheticket({ id: 0, flight: flight, seat: oneSeat, user: appState });
          });
      });
      
  }, []);

  function updateSeat(event: any) {
    event.preventDefault();
    if (event.currentTarget.dataset.available === "false") {
      alert("This seat is already taken");
    } else {
      update(event.currentTarget.dataset.id) //updating availability of the selected seat 
      .then(seat => {      
        console.log(seat, {appState, theFlight, seat})
        history.push({pathname: '/ticket', state: {user: theUser, flight: theFlight, seat: seat}})
      });
    }
  }

  async function update(id: number) { 
    let req = await axios.get('http://localhost:8080/seat/get/seat?id=' + id); //fetching a seat by id
    let res = await axios.post('http://localhost:8080/seat/update', req.data); //updating that seat
    return res.data;
  }

  const halfWayIndex = Math.ceil(seats.length / 2);

  const firstSeat = seats.slice(0, halfWayIndex);
  const secondSeat = seats.slice(halfWayIndex);
  return (

    <div className="container pt-5 ">

      <div className="container bg-white shadow p-3 mb-5 bg-body rounded">

        <h1 className="fw-light">Wait to select a seat</h1>
        <hr />
        <div className="second">
          {firstSeat.map(({ id, seatAvailable }, idx) => (

            <button onClick={updateSeat} className="btn btn-secondary m-1" data-available={seatAvailable} key={idx} data-id={id}>

              {seatAvailable ? id : 'X'}

            </button>
          ))}
        </div>

        <div className="second">
          {secondSeat.map(({ id, seatAvailable }, idx) => (

            <button onClick={updateSeat} className="btn btn-secondary m-1" data-available={seatAvailable} key={idx} data-id={id}>
              {seatAvailable ? id : 'X'}
            </button>
          ))}
        </div>
      </div>

      <div className="text-center">
        <img src="seats.jpg" className="img-fluid " alt="craft image" width="700" height="100" loading="lazy" />
      </div>
    </div>

  );
}


