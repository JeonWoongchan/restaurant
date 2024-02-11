import React, { useEffect, useState } from 'react';

export default function LoginForm(props) {
    const [formInner, setFormInner] = useState()

    const siginIn = <>
                        <h1 className="form-title">Welcome Back</h1>
                        <label>
                            <span>Email</span>
                            <input type="email" />
                        </label>
                        <label>
                            <span>Password</span>
                            <input type="password" />
                        </label>
                        <label>
                            <p>비밀번호 찾기</p>
                        </label>
                        <button className='form-btn' type="button">SIGN IN</button>
                    </>;
                
    const siginUp = <>
                <h1 className="form-title">Welcome to WooDy</h1>
                <label>
                    <span>Email</span>
                    <input type="email" />
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
                    <input type="text" />
                </label>
                <button className='form-btn' type="button">SIGN UP</button>
            </>;

    useEffect(()=>{ 
        if(props.nowForm){
            setTimeout(()=>{
                setFormInner(siginIn)
            },300)
        }else if(!props.nowForm){
            setTimeout(()=>{
                setFormInner(siginUp)
            },300)
        }
    },[props.nowForm])

    return (
        <div className="form-inner" style={props.chaneForm(0)}>
            {formInner}
        </div>
    );
}

