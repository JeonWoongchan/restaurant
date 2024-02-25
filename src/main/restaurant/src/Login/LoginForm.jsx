import React, { useEffect, useState } from 'react';

export default function LoginForm(props) {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleEmailChange = (e) => {
        setEmail(e.target.value);
    };

    const handlePasswordChange = (e) => {
        setPassword(e.target.value);
    };

    const handleSubmit = () => {
        // 이 부분에서 로그인 요청을 보내고 데이터를 처리할 수 있습니다.
        fetch('/sign-in', {
            method: 'POST',
            body: JSON.stringify({ email, password }),
            headers: {
                'Content-Type': 'application/json',
            },
        })
            .then(response => response.json())
            .then(data => {
                // 로그인 성공 시 필요한 처리를 여기에 작성하세요.
            })
            .catch(error => {
                // 로그인 실패 시 필요한 처리를 여기에 작성하세요.
            });
    };

    return (
        <div className="form-inner" style={props.chaneForm(0)}>
            <h1 className="form-title">Welcome Back</h1>
            <label>
                <span>Email</span>
                <input type="email" value={email} onChange={handleEmailChange} />
            </label>
            <label>
                <span>Password</span>
                <input type="password" value={password} onChange={handlePasswordChange} />
            </label>
            <label>
                <p>비밀번호 찾기</p>
            </label>
            <button className='form-btn' type="button" onClick={handleSubmit}>로그인</button>
        </div>
    );
}
