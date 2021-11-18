import axios, { Axios } from "axios";
import React, { useEffect, useState } from "react";
import { Footer } from "./Footer";
import './Main.css';

export const Main: React.FC<any> = () => {

    return (
        <div className="text-center">
            <div className="pt-3">
                <img src="travel.png" className="img-fluid " alt="travel image" width="128" height="156" loading="lazy" />
            </div>
            <div className="px-4 pt-3  bg-black bg-opacity-10">
                <h1 className="display-4 fw-bold text-light">Aether Airlines</h1>
                <h3 className="display-4 fw-bold text-light">Reservation System</h3>
                <div className="col-lg-6 mx-auto">
                    <p className="lead mb-4 text-light">Quickly design and customize responsive mobile-first sites with Bootstrap, the world’s most popular front-end open source toolkit, featuring Sass variables and mixins, responsive grid system, extensive prebuilt components, and powerful JavaScript plugins.</p>
                    <div className="d-grid gap-2 d-sm-flex justify-content-sm-center mb-5">
                        <a href="/login" className="btn btn-outline-light btn-lg px-4 me-sm-3">Login</a>
                        <a href="/register" className="btn btn-outline-light btn-lg px-4">Register</a>
                    </div>
                </div>
            </div>
            <div className="">
                <img src="craft.png" className="img-fluid" alt="craft image" width="700" height="500" loading="lazy" />
            </div>

        </div>

    );
}