//This is our central/root reducer
import { combineReducers } from "redux";
//We will create these next
import {userReducer} from './UserReducer';

export default combineReducers({
    user: userReducer
});