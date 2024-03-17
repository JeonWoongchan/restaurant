import { combineReducers, createSlice } from "@reduxjs/toolkit";

const reservUserData = createSlice({
    name: 'reservUserData',
    initialState: {'name': '', 'email': '', 'phone': '', 'point': ''},
    reducers: {
        setReservUserData(state, action) {
            return action.payload; 
        }
    }
});

export const { setReservUserData } = reservUserData.actions;

const reservData = createSlice({
    name: 'reservData',
    initialState: '',
    reducers: {
        setReservData(state, action) {
            return action.payload; 
        }
    }
});

export const { setReservData } = reservData.actions;

const reservReducer = combineReducers({
    reservUserData: reservUserData.reducer,
    reservData: reservData.reducer,
});

export default reservReducer;