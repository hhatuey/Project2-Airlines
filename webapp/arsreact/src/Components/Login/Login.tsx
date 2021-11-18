import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { loginUser } from '../../Actions/UserActions';
import { useHistory, Link, Redirect } from 'react-router-dom';

import "./Login.css";


export const Login: React.FC<any> = () => {

    //First we will pull in the application level state with useSelector
    const appState = useSelector<any, any>((state) => state);
    //Create the dispatcher to dispatch actions
    const dispatch = useDispatch();

    //We will setup useHistory to naviate using JS
    const history = useHistory();

    //Set up our component level state, that other components don't need to know about
    let [username, setUsername] = useState('');
    let [password, setPassword] = useState('');

    useEffect(() => {
        if (appState.user.id > 0) {
            history.push({pathname: '/book', state: appState})
            console.log(appState);
        }
    }, [appState]);

    //Update the username or password state with whatever is typed in the fields
    const handleChange = (e: any) => {
        if (e.target.name === 'username') {
            setUsername(e.target.value);
        } else {
            setPassword(e.target.value);
        }
    }

    //We need a function to actually handle the login
    const login = async (event: any) => {
        event.preventDefault();
        await dispatch(
            loginUser({ username, password })
        );
    }

    return (
        <div className="container pt-5">

            <div className="container bg-white shadow p-3 mb-5 bg-body rounded">

                <h1 className="fw-light">Login to your account</h1>
                <hr />
                <form>

                    <div className="form-group">
                        <label>Username</label>
                        <input type="text" className="form-control" placeholder="Enter username" name="username" onChange={handleChange} id="username" />
                    </div>

                    <div className="form-group">
                        <label>Password</label>
                        <input type="password" className="form-control" placeholder="Enter password" name="password" onChange={handleChange} id="password" />
                    </div>

                    <hr />
                    <button type="submit" className="btn btn-primary btn-block" onClick={login}>Login</button>

                </form>
            </div>
            <div className="text-center">
                <img src="travel.jpeg" className="img-fluid" alt="craft image" width="1000" height="700" loading="lazy" />
            </div>
        </div>
    );


}