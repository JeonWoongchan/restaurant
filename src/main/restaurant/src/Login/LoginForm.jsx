import React, { useEffect, useState } from 'react';
import LoginModal from './LoginModal';
import { useDispatch, useSelector } from 'react-redux';
import { setEmailCheck, setPhoneCheck, setSignUpEmail, setSignUpPhone } from '../store/loginStore';
import SignIn from './SignIn';
import SignUp from './SignUp';

export default function LoginForm(props) {
    const [formInner, setFormInner] = useState()
    const dispatch = useDispatch()

    // useEffect(() => {
    //     if (props.nowForm) {
    //         setTimeout(() => {
    //             setFormInner(siginIn)
    //             
    //         }, 300)
    //     } else if (!props.nowForm) {
    //         setTimeout(() => {
    //             setFormInner(siginUp)
    //         }, 300)
    //     }
    // }, [props.nowForm, emailCheck, phoneCheck])

    useEffect(()=>{
        if(props.nowForm){
            setTimeout(()=>{
                setFormInner(true)
                dispatch(setEmailCheck(false))
                dispatch(setPhoneCheck(false))
            },300)
        }else{
            setTimeout(()=>{
                setFormInner(false)
            },300)
        }
    },[props.nowForm])

    return (
        <div className="form-inner" style={props.chaneForm(0)}>
            {formInner ? <SignIn/> : <SignUp setModalOn={props.setModalOn}/>}
        </div>
    );
}

