import { combineReducers, createSlice } from "@reduxjs/toolkit";

const reservUserData = createSlice({
    name: 'reservUserData',
    initialState: JSON.parse(localStorage.getItem('reservUserData')),
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

const guestPhone = createSlice({
    name: 'guestPhone',
    initialState: '',
    reducers: {
        setGuestPhone(state, action) {
            return action.payload; 
        }
    }
});

export const { setGuestPhone } = guestPhone.actions;

const reservReducer = combineReducers({
    reservUserData: reservUserData.reducer,
    reservData: reservData.reducer,
    guestPhone: guestPhone.reducer,
});

export default reservReducer;