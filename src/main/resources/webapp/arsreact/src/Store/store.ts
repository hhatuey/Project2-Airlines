import {applyMiddleware, createStore} from 'redux';
import thunk from 'redux-thunk';


//We will create this in a second
import reducer from '../Reducers';

import {AppState} from '../Interfaces/IUser';

const initialState:AppState = {
    user: {
        id: 0,
        username: '',
        password: '',
        firstname: '',
        lastname: '',
        roleid: 0,
        email: ''   
    },
}

const middleWare = [thunk];

export const store = createStore(reducer, initialState, applyMiddleware(...middleWare));