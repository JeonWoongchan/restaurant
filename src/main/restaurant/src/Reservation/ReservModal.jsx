import React from 'react';
import './css/ReservModal.css'
import { useNavigate } from 'react-router-dom';

export default function ReservModal(props) {
    const navigate = useNavigate()

    return (
        <div id='reserv-modal'>
            <div className="modal-container">
                <div className="modal-inner">
                    <div className="modal-title">
                        <p>Restaurant WooDy</p>
                        <h1>로그인 하시고<br/> 더 편리하게 이용하세요.</h1>
                    </div>
                    <div className="modal-main">
                        <div className="content" onClick={()=>{navigate('/login/sign-in')}}>로그인</div>
                        OR
                        <div className="content" onClick={()=>{props.setModalOn(false)}}>비회원 예약</div>
                    </div>
                    <div className="modal-detail">
                        <p>레스토랑 우디의 회원이 되시면<br/> 더 많은 혜택을 누리실 수 있습니다.</p>
                        <button type='button' onClick={()=>{navigate('/login/sign-up')}}>회원가입</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

