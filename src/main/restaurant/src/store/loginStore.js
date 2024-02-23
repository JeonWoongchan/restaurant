import { combineReducers, createSlice } from "@reduxjs/toolkit";

const emailCheck = createSlice({
    name: 'emailCheck',
    initialState: '',
    reducers: {
        setEmailCheck(state, action) {
            return action.payload; // 예시 액션 생성자
        }
    }
});

export const { setEmailCheck } = emailCheck.actions;

const phoneCheck = createSlice({
    name: 'phoneCheck',
    initialState: '',
    reducers: {
        setPhoneCheck(state, action) {
            return action.payload; // 예시 액션 생성자
        }
    }
});

export const { setPhoneCheck } = phoneCheck.actions;

const signUpEmail = createSlice({
    name: 'signUpEmail',
    initialState: '',
    reducers: {
        setSignUpEmail(state, action) {
            return action.payload; // 예시 액션 생성자
        }
    }
});

export const { setSignUpEmail } = signUpEmail.actions;

const signUpPhone = createSlice({
    name: 'signUpPhone',
    initialState: '',
    reducers: {
        setSignUpPhone(state, action) {
            return action.payload; // 예시 액션 생성자
        }
    }
});

export const { setSignUpPhone } = signUpPhone.actions;

const receiveText = createSlice({ // 이메일, 휴대전화로 받은 인증문자
    name: 'receiveText',
    initialState: '1234',
    reducers: {
        setReceiveText(state, action) {
            return action.payload; // 예시 액션 생성자
        }
    }
});

export const { setReceiveText } = receiveText.actions;

const loginReducer = combineReducers({
    emailCheck: emailCheck.reducer,
    phoneCheck: phoneCheck.reducer,
    signUpEmail: signUpEmail.reducer,
    signUpPhone: signUpPhone.reducer,
    receiveText: receiveText.reducer
});

export default loginReducer;