import React from 'react';
import { useSelector } from 'react-redux';

export default function MyReserv() {
    const isLogin = useSelector(state=>state.loginReducer.isLogin)

    return (
        <div id='my-reserv'>
            <div className="reserv-inner">
                <div className="reserv-intro">
                    <h1 className="reserv-title">예약 확인</h1>
                    <div className="reserv-detail">Lorem ipsum dolor sit amet consectetur adipisicing elit. Quidem cumque voluptates doloribus temporibus, minus repellat animi dolor, quos debitis molestiae explicabo, nemo incidunt laborum quas? Facilis doloribus expedita tenetur. Molestias!</div>
                </div>
                <div className="payment-main">
                    <div className="payment-product">
                        <div className="detail">
                            <h5 className='sub-title'>예약 일시</h5>
                            <p></p>
                        </div>
                        <div className="detail">
                            <h5 className='sub-title'>예약 내용</h5>
                            <p>{}</p>
                        </div>
                        <div className="detail">
                            <h5 className='sub-title'>예약자 정보</h5>
                            <ul>
                                <li className='text'>
                                    <p>이름</p>
                                    <input type="text" />
                                </li>
                                <li className='phone'>
                                    <p>휴대폰 번호</p>
                                    <div className="input-list" >
                                        <select name="" id="">
                                            <option value="010">010</option>
                                            <option value="011">011</option>
                                            <option value="016">016</option>
                                            <option value="017">017</option>
                                            <option value="018">018</option>
                                            <option value="019">019</option>
                                        </select>
                                        <div className="num">
                                            <input type="text" />
                                        </div>
                                        <div className="num">
                                            <input type="text"/>
                                        </div>
                                    </div>
                                </li>
                                <li className='text'>
                                    <p>이메일</p>
                                    <input type="text" placeholder='WooDy@restaurant.com' />
                                </li>
                            </ul>
                        </div>
                        <button className='payment-submit'>예약 취소</button>
                    </div>
                </div>
            </div>
        </div>
    );
}

