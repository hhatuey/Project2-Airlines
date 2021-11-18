
import {IUser} from "../Interfaces/IUser";

//Later we will import our action types
import {LOGIN_USER, ADD_USER} from '../Actions/ActionTypes';

let initialState:IUser = {

    id: 0,
    username: '',
    password: '',
    firstname: '',
    lastname: '',
    roleid: 0,
    email: ''   
};

type Action = {type: string, payload: IUser};

export const userReducer = (state: IUser = initialState, action:Action) => {

    switch(action.type){
        case LOGIN_USER:
            initialState = action.payload;
            console.log(action.payload);
            return {
                ...initialState
            }
        case ADD_USER:
            initialState = action.payload;
            console.log(action.payload);
            return {
                ...initialState
            }    
        default:
            return state;
    }

}