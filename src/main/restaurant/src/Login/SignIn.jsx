import React from 'react';

export default function SignIn() {
    return (
        <>
            <h1 className="form-title">로그인</h1>
            <label>
                <span>Email</span>
                <input type="text" />
            </label>
            <label>
                <span>Password</span>
                <input type="password" />
            </label>
            <label>
                <p>비밀번호 찾기</p>
            </label>
            <button className='form-btn' type="button">SIGN IN</button>
        </>
    );
}

