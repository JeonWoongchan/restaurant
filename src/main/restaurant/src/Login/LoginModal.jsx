import React, { useState } from 'react';
import './css/LoginModal.css';
import { useDispatch, useSelector } from 'react-redux';
import axios from 'axios'; // axios 모듈을 가져옵니다.
import { setReceiveText, setEmailCheck, setPhoneCheck } from '../store/loginStore';

export default function LoginModal(props) {
  const [isSend, setIsSend] = useState(false);
  const [inputText, setInputText] = useState(''); // 입력 인증번호

  const signUpEmail = useSelector(state => state.loginReducer.signUpEmail); // 회원가입 정보 입력시 입력한 이메일
  const signUpPhone = useSelector(state => state.loginReducer.signUpPhone); // 회원가입 정보 입력시 입력한 전화번호
  const dispatch = useDispatch();

  const authcheck = async () => {
    console.log(props.type);
    try {
      // 요청할 URL을 인증 타입에 따라 다르게 설정합니다.
      const url = props.type === '이메일' ? '/login/verify-check-email' : '/login/verify-check-phone';

      // 요청에 사용할 데이터 (email 또는 phone 및 입력된 인증번호)
      const requestData = {
        email: props.type == '이메일' ? signUpEmail : null,
        phone: props.type == '휴대폰' ? signUpPhone : null,
        auth: inputText, // 사용자가 입력한 인증번호
      };

      // 지정된 URL로 요청을 보냅니다.
      const response = await axios.post(url, requestData);

      // 응답 처리
      if (response.status === 200) {
        if (response.data.success === 1) {
          alert('인증완료');
          props.setModalOn(false);
          if (props.type === '이메일') {
            dispatch(setEmailCheck(true));
          } else if (props.type === '휴대폰') {
            dispatch(setPhoneCheck(true));
          }

        } else {
          alert('인증번호가 일치하지 않습니다. 다시 시도해주세요.');
        }
      } else {
        alert('인증 확인에 실패했습니다. 다시 시도해주세요.');
      }
    } catch (error) {
      console.error('인증 확인 중 오류가 발생했습니다:', error);
      alert('인증 확인 중 오류가 발생했습니다. 다시 시도해주세요.');
    }
  };

  const sendVerificationCode = async () => {
    console.log(props.type);

    try {
      // 요청할 URL을 인증 타입에 따라 다르게 설정합니다.
      const url = props.type === '이메일' ? '/login/send-verify-email' : '/login/send-verify-phone';

      // 요청에 사용할 데이터 (email 또는 phone)
      const requestData = {
        email: props.type === '이메일' ? signUpEmail : null,
        phone: props.type === '휴대폰' ? signUpPhone : null,
      };

      // 지정된 URL로 POST 요청을 보냅니다.
      const response = await axios.post(url, requestData);

      // 응답 처리
      if (response.status === 200) {
        alert('인증번호 전송 성공');
        setIsSend(true);

        // 5분 후 타이머가 만료되면 실행할 코드
        setTimeout(() => {
          alert('인증코드가 만료되었습니다');
          setIsSend(false);
        }, 5 * 60 * 1000); // 5분(300,000ms) 대기
      } else {
        alert('인증번호 전송 실패');
      }
    } catch (error) {
      console.error('인증번호 전송 중 오류가 발생했습니다:', error);
    }
  };

  return (
    <div id="login-modal">
      <div className="modal-container">
        <div className="modal-inner">
          <div className="modal-title">
            <h5>{props.type} 인증</h5>
          </div>
          <div className="modal-check">
            <p>{props.type === '이메일' ? signUpEmail : signUpPhone.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3')}</p>
            <div className="user-input">
              <input
                type="text"
                placeholder="인증번호 입력"
                value={inputText}
                onChange={(e) => setInputText(e.target.value)}
              />

              {isSend ? (
                <>
                  <button onClick={authcheck}>인증확인</button>
                  {/* 재전송 버튼 */}
                  <button onClick={sendVerificationCode}>재전송</button>
                </>
              ) : (
                <button onClick={sendVerificationCode}>인증번호 전송</button>
              )}
            </div>
          </div>
          <button className='close-btn' onClick={() => props.setModalOn(false)}>취소</button>
        </div>
      </div>
    </div>
  );
}
