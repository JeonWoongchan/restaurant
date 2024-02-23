import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { setSignUpEmail, setSignUpPhone } from '../store/loginStore';

export default function SignUp(props) {
    const emailCheck = useSelector(state => state.loginReducer.emailCheck)// 이메일 인증 완료
    const phoneCheck = useSelector(state => state.loginReducer.phoneCheck) // 휴대폰 인증 완료

    const signUpEmail = useSelector(state => state.loginReducer.signUpEmail) // 회원가입 정보 입력시 입력한 이메일
    const signUpPhone = useSelector(state => state.loginReducer.signUpPhone) // 회원가입 정보 입력시 입력한 전화번호

    const [phoneFirst, setPhoneFirst] = useState('010')
    const [phoneMiddle, setPhoneMiddle] = useState('')
    const [phoneLast, setPhoneLast] = useState('')

    const dispatch = useDispatch()

    const modalHandler = (type) => {
        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        // console.log(signUpEmail)
        if (type == '이메일') {
            if (emailPattern.test(signUpEmail)) {
                props.setModalOn(type)
            } else {
                alert('잘못된 이메일 형식입니다.')
            }
        } else if (type == '휴대폰') {
            if (signUpPhone .length == 11) {
                props.setModalOn(type)
            } else {
                alert('잘못된 휴대폰 번호 형식입니다.')
            }
        }

    }

    const signUpHandeler = () => {
        if (emailCheck && phoneCheck) {
            alert('회원가입 완료')
        } else if (!emailCheck) {
            alert('이메일 인증을 완료 해주세요')
        } else if (!emailCheck) {
            alert('휴대폰 인증을 완료 해주세요')
        }
    }

    useEffect(()=>{
        dispatch(setSignUpPhone(phoneFirst + phoneMiddle + phoneLast))
    },[phoneFirst, phoneMiddle, phoneLast])
    console.log(signUpPhone)
    
    return (
        <>
            <h1 className="form-title">회원가입</h1>
            <label>
                <span>Email</span>
                <input type="text" onChange={(e) => { dispatch(setSignUpEmail(e.target.value)) }} />
                <button className='email-btn' onClick={() => { modalHandler('이메일') }} style={emailCheck ? { pointerEvents: 'none' } : {}}>{emailCheck ? '인증완료' : '이메일 인증'}</button>
            </label>
            <label>
                <span>Password</span>
                <input type="password" />
            </label>
            <label>
                <span>Name</span>
                <input type="text" />
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
                        <input type="text" onChange={(e) => { setPhoneMiddle(e.target.value) }} />
                    </div>
                    <div className="num">
                        <input type="text" onChange={(e) => { setPhoneLast(e.target.value) }} />
                    </div>
                </div>
                <button className='phone-btn' onClick={() => { modalHandler('휴대폰') }} style={phoneCheck ? { pointerEvents: 'none' } : {}}>{phoneCheck ? '인증완료' : '휴대폰 인증'}</button>
            </label>
            <button className='form-btn' type="button" onClick={() => { signUpHandeler() }}>SIGN UP</button>
        </>
    );
}

