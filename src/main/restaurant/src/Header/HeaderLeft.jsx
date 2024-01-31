import React from 'react';
import './css/HeaderLeft.css'
import { GrClose } from "react-icons/gr";

export default function HeaderLeft(props) {

    return (
        <div id='header-left'>
            <div className="header-left-container">
                <div className="header-left-inner">
                    <GrClose className="header-left-close" onClick={()=>{props.setHeaderLeftOn(false)}}/>
                    <div className="inner-top">
                        {/* <div className="profile">
                                <div className="profile-icon"></div>
                                <div className="user-name"></div>
                                <div className="logout"></div>
                            </div> */}
                        <div className="sign-in">로그인</div>
                        <div className="sign-up">회원가입</div>
                    </div>
                    <div className="inner-middle"></div>
                    <div className="inner-bottom"></div>
                </div>
            </div>
        </div>
    );
}

