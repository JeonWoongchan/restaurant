import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setSignUpEmail, setSignUpName, setSignUpPhone, setSignUpPw } from '../store/loginStore';
import SignUpLogic from '../backend/SignUpLogic';
import EmailDuplicateLogic from '../backend/EmailDuplicateLogic';
import ModalCheckLogic from '../backend/ModalCheckLogic'

export default function SignUp(props) {
    const dispatch = useDispatch()

    const emailDupli = useSelector(state=>state.loginReducer.emailDupli) // 이메일 중복 체크 여부
    const emailCheck = useSelector(state => state.loginReducer.emailCheck)// 이메일 인증 여부
    const phoneCheck = useSelector(state => state.loginReducer.phoneCheck) // 휴대폰 인증 여부
    const signUpPw = useSelector(state=>state.loginReducer.signUpPw)

    const [phoneFirst, setPhoneFirst] = useState('010')
    const [phoneMiddle, setPhoneMiddle] = useState('')
    const [phoneLast, setPhoneLast] = useState('')

    const { emailDuplicateCheck } = EmailDuplicateLogic()
    const { signUpHandeler } = SignUpLogic()
    const { modalCheck } = ModalCheckLogic()

    useEffect(()=>{
        dispatch(setSignUpPhone(phoneFirst + phoneMiddle + phoneLast))
    },[phoneFirst, phoneMiddle, phoneLast])

    return (
        <>
            <h1 className="form-title">회원가입</h1>
            <label>
                <span>Email</span>
                <input type="text" onChange={(e) => { dispatch(setSignUpEmail(e.target.value)) }} readOnly={emailDupli ? true : false}/>
                {
                    !emailDupli ? <button className='email-btn' onClick={() => { emailDuplicateCheck() }}>이메일 중복 체크</button>
                    : <button className='email-btn' onClick={()=>{props.setModalOn('이메일'); modalCheck('이메일')}} style={emailCheck ? { pointerEvents: 'none' } : {}}>{emailCheck ? '인증완료' : '이메일 인증'}</button>
                }
            </label>
            <label>
                <span>Password</span>
                <input type="password" onChange={(e)=>{dispatch(setSignUpPw(e.target.value))}}/>
            </label>
            <label>
                <span>Name</span>
                <input type="text" onChange={(e)=>{dispatch(setSignUpName(e.target.value))}}/>
            </label>
            <label>
                <span>Phone</span>
                <div className="input-list">
                    <select onChange={(e) => { setPhoneFirst(e.target.value) }}>
                        <option value="010">010</option>
                        <option value="011">011</option>
                        <option value="016">016</option>
                        <option value="017">017</option>
                        <option value="018">018</option>
                        <option value="019">019</option>
                    </select>
                    <div className="num">
                        <input type="text" onChange={(e) => { setPhoneMiddle(e.target.value) }} readOnly={phoneCheck ? true : false}/>
                    </div>
                    <div className="num">
                        <input type="text" onChange={(e) => { setPhoneLast(e.target.value) }} readOnly={phoneCheck ? true : false}/>
                    </div>
                </div>
                <button className='phone-btn' onClick={()=>{props.setModalOn('휴대폰'); modalCheck('휴대폰')}} style={phoneCheck ? { pointerEvents: 'none' } : {}}>{phoneCheck ? '인증완료' : '휴대폰 인증'}</button>
            </label>
            <button className='form-btn' type="button" onClick={()=>{signUpHandeler()}}>SIGN UP</button>
        </>
    );
}

