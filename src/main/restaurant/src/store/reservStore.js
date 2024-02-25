import { combineReducers, createSlice } from "@reduxjs/toolkit";

const personnelSlice = createSlice({
    name: 'personnelSlice',
    initialState: '',
    reducers: {
        setPersonnelSlice(state, action) {
            return action.payload; // 예시 액션 생성자
        }
    }
});

export const { setPersonnelSlice } = personnelSlice.actions;

const reservReducer = combineReducers({
    personnelSlice: personnelSlice.reducer,

});

export default reservReducer;