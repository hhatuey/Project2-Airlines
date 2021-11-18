import axios from "axios";
import React, { useEffect, useState } from "react";
import { IFlight } from "../../Interfaces/IFlight";
import { ICity } from "../../Interfaces/ICity";
import { useDispatch, useSelector } from 'react-redux';
import { useHistory, useLocation } from "react-router-dom";
import "../Flight/Book.css";
import { TicketDisplay } from "../TicketDisplay";
import { IUser } from "../../Interfaces/IUser";

export const Book: React.FC<any> = () => {

    const appState = useSelector<any, any>((state) => state);
    const dispatch = useDispatch();
    const history = useHistory();
    const location = useLocation<IUser>();

    const [theUser, setTheUser] = useState<IUser>();
    const [origin, setOrigin] = useState('');
    const [destination, setDestination] = useState('');
    const [oneFlight, setOneFlight] = useState<IFlight>();
    const [oneCity, setOneCity] = useState<ICity>();

    const [flights, setFlights] = useState<IFlight[]>([]);
    const [cities, setCities] = useState<ICity[]>([]);



    useEffect(() => {
        axios.get('http://localhost:8080/city/get').then(response => {
            setCities(response.data);
        });
        // setTheUser(location.state);
        // console.log(theUser);
        console.log(appState);
    }, []);

    // useEffect(() => {
    //     console.log(appState);
    //     // if(appState.user.id > 0){
    //     //     history.push('/home');
    //     // }
    // }, [appState]);

    function search(event: any) {
        event.preventDefault();
        axios('http://localhost:8080/flight/get/trip?orgn=' + origin + '&dest=' + destination)
            .then(response => {
                console.log(response.data);
                setFlights(response.data);
            });
    }

    function originChange(event: any) {
        console.log(event.target.value);
        setOrigin(event.target.value)
    }

    function destChange(event: any) {
        console.log(event.target.value);
        setDestination(event.target.value)
    }

    function clickFlight(event: any) {
        event.preventDefault();
        let index = event.currentTarget.dataset.index
        history.push({ pathname: '/seat', state: flights[index] });
    }

    function logout() {
        window.localStorage.clear();
        window.location.reload();
        window.location.replace('/');
    }

    return (
        <>
            <nav className="navbar navbar-light bg-light">
                <div className="container-fluid">
                    <a className="navbar-brand">Aether Airlines</a>
                    <div className="d-flex">
                        <button className="btn btn-outline-primary" type="submit" onClick={logout}>logout</button>
                    </div>
                </div>
            </nav>
            <div className="container pt-5">
                <div className="container bg-white shadow p-3 mb-5 bg-body rounded">
                <h1 className="fw-light">Search for a flight</h1>
                <hr />
                    <form className="row g-3 needs-validation" noValidate>
                        <div className="col-md-6">
                            <label htmlFor="originDataList" className="form-label">Origin</label>
                            <input className="form-control" list="datalistOrigin" id="originDataList" onChange={originChange} placeholder="Click to select..." />
                            <datalist id="datalistOrigin">
                                {
                                    cities.map((item, idx) => {
                                        return <option key={idx} value={item.name} />
                                    })
                                }
                            </datalist>
                            <div className="valid-feedback">
                                Looks good!
                            </div>
                        </div>
                        <div className="col-md-6">
                            <label htmlFor="destinationDataList" className="form-label">Destination</label>
                            <input className="form-control" list="datalistDestination" id="destinationDataList" onChange={destChange} placeholder="Click to select..." />
                            <datalist id="datalistDestination">
                                {
                                    cities.map((item, idx) => {
                                        return <option key={idx} value={item.name} />
                                    })
                                }
                            </datalist>

                            <div className="valid-feedback">
                                Looks good!
                            </div>
                        </div>

                        <div className="col-12">
                            <button className="btn btn-primary" type="submit" onClick={search}>Search</button>
                        </div>
                    </form>
                </div>
                <hr />
                <div className="container">
                    {
                        flights.map((itm, idx) => {
                            return (
                                <div className="list-group my-1" onClick={clickFlight} key={itm.id} data-name={itm.name} data-index={idx}>
                                    <a href="/ticket" className="list-group-item list-group-item-action active" aria-current="true">
                                        <div className="d-flex w-100 justify-content-between">
                                            <h5 className="mb-1">{itm.origin.name} - {itm.destination.name}</h5>
                                            <small>Fligh name: {itm.name}</small>
                                        </div>
                                        <p className="mb-1">Take off at: {new Date(itm.takeoff).toUTCString()}</p>
                                        <small>ETA: {new Date(itm.eta).getHours()} hours</small>
                                    </a>
                                </div>
                            );
                        })
                    }
                </div>
            </div>
        </>
    );
}