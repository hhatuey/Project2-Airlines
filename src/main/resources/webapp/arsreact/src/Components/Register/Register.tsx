import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { createUser } from '../../Actions/UserActions';
import { useHistory, Link } from 'react-router-dom';



export const Register: React.FC<any> = () => {

    //First we will pull in the application level state with useSelector
    const appState = useSelector<any, any>((state) => state);
    //Create the dispatcher to dispatch actions
    const dispatch = useDispatch();

    //We will setup useHistory to naviate using JS
    const history = useHistory();

    //Set up our component level state, that other components don't need to know about
    let [username, setUsername] = useState('');
    let [password, setPassword] = useState('');
    let [firstName, setFirstname] = useState('');
    let [lastName, setLastname] = useState('');
    let [role_id, setRoleId] = useState('');
    let [email, setEmail] = useState('');

    useEffect(() => {
        if (appState.user.id == 1) {
            history.push("/login");
            alert("User was created");
        }
        else if (appState.user.id == -1) {
            history.push("/register");
            alert("Username or email already registered to a user");

        }

    }, [appState]);

    //Update the username or password state with whatever is typed in the fields
    const handleChange = (e: any) => {
        switch (e.target.name) {
            case 'username':
                setUsername(e.target.value);
                break;
            case 'password':
                setPassword(e.target.value);
                break;
            case 'firstName':
                setFirstname(e.target.value);
                break;
            case 'lastName':
                setLastname(e.target.value);
                break;
            case 'roleId':
                setRoleId(e.target.value);
                break;
            case 'email':
                setEmail(e.target.value);
                break;
        }
    }

    //We need a function to actually handle the login
    const signup = async () => {
        let roleId = +role_id;
        await dispatch(
            createUser({ username, password, firstName, lastName, roleId, email })
        );
    }

    return (
        <div className="container pt-5">

            <div className="container bg-white shadow p-3 mb-5 bg-body rounded">

                <h1 className="fw-light">Sign up and create a new account</h1>
                <hr />
                <form>
                    <h3>Sign Up</h3>

                    <div className="form-group">
                        <label>Username</label>
                        <input type="text" className="form-control" placeholder="Enter username" name="username" onChange={handleChange} id="username" />
                    </div>

                    <div className="form-group">
                        <label>Password</label>
                        <input type="password" className="form-control" placeholder="Enter password" name="password" onChange={handleChange} id="password" />
                    </div>

                    <div className="form-group">
                        <label>First Name</label>
                        <input type="text" className="form-control" placeholder="Enter First Name" name="firstName" onChange={handleChange} id="firstName" />
                    </div>

                    <div className="form-group">
                        <label>Last Name</label>
                        <input type="text" className="form-control" placeholder="Enter Last Name" name="lastName" onChange={handleChange} id="lastname" />
                    </div>

                    <div className="form-group">
                        <label>Role</label>
                        <select className="form-control" name="roleId" id="roleId" onChange={handleChange}>
                            <option value="1">Employee</option>
                            <option value="2">Customer</option>
                        </select>
                    </div>

                    <div className="form-group">
                        <label>Email</label>
                        <input type="text" className="form-control" placeholder="Enter Email" name="email" onChange={handleChange} id="email" />
                    </div>


                    <button type="submit" className="btn btn-primary btn-block" onClick={signup}>Submit</button>

                </form>
            </div>
        </div>
    );


}