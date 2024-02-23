import { configureStore } from '@reduxjs/toolkit'
import loginReducer from './loginStore'

export default configureStore({
    reducer: {
        loginReducer: loginReducer
    }
});