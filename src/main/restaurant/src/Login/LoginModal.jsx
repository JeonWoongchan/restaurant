import React, { useEffect, useState } from 'react';
import './css/LoginModal.css'
import { useDispatch, useSelector } from 'react-redux';
import { setEmailCheck, setPhoneCheck } from '../store/loginStore';

export default function LoginModal(props) {
    const [isSend, setIsSend] = useState(false)
    const [inputText, setInputText] = useState('') // 입력 인증번호

    const signUpEmail = useSelector(state=>state.loginReducer.signUpEmail) // 회원가입 정보 입력시 입력한 이메일
    const signUpPhone = useSelector(state=>state.loginReducer.signUpPhone) // 회원가입 정보 입력시 입력한 전화번호

    const receiveText = useSelector(state=>state.loginReducer.receiveText)
    const dispatch = useDispatch()

    const checkText = ()=>{
        if(inputText == receiveText){
            if(props.type === '이메일'){
                dispatch(setEmailCheck(true))
            }else if(props.type === '휴대폰'){
                dispatch(setPhoneCheck(true))
            }
            props.setModalOn(false)
        }else{
            alert('인증번호 틀림')
        }
    }

    return (
        <div id='login-modal'>
            <div className="modal-container">
                <div className="modal-inner">
                    <div className="modal-title">
                        <h5>{props.type} 인증</h5>
                    </div>
                    <div className="modal-check">
                        <p>{props.type == '이메일' ? signUpEmail : signUpPhone.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3')}</p>
                        <div className="user-input">
                            <input type="text" placeholder='인증번호 입력' value={inputText} onChange={(e)=>{setInputText(e.target.value)}}/>
                            {
                                isSend ? <button onClick={()=>{checkText()}}>인증확인</button> 
                                : <>
                                    <button onClick={()=>{setIsSend(true)}}>인증번호 전송</button>
                                    <button>재전송</button>
                                </>
                            }
                        </div>
                    </div>
                    <div className="modal-detail">
                        {/* <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quisquam, nemo? Voluptatibus doloremque quae omnis totam ex obcaecati, nam autem ad maiores? Suscipit, minus sint sunt fugiat nemo et perferendis pariatur.</p> */}
                    </div>
                    <button className='close-btn' onClick={()=>{props.setModalOn(false)}}>취소</button>
                </div>
            </div>
        </div>
    );
}

