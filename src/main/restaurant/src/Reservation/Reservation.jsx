import React from 'react';
import './css/Reservation.css'
import Personnel from './Personnel';
import Calendar from './Calendar';

export default function Reservation() {
    return (
        <div id='reservation'>
            <div className="reserv-container">
                <div className="reserv-image">
                    <img src="/image/login1.png" alt="" />
                    <div className="image-text">Reservation</div>
                </div>
                <div className="reserv-inner">
                    <div className="reserv-intro">
                        <h1 className="reserv-title">Reservation</h1>
                        <div className="reserv-detail">Lorem ipsum dolor sit amet consectetur adipisicing elit. Quidem cumque voluptates doloribus temporibus, minus repellat animi dolor, quos debitis molestiae explicabo, nemo incidunt laborum quas? Facilis doloribus expedita tenetur. Molestias!</div>
                    </div>
                    <div className="reserv-main">
                        <Personnel/>
                        <Calendar/>
                        <div className="reserv-time"></div>
                        <div className="caution-box"></div>
                        <div className="submit"></div>
                    </div>
                </div>
            </div>
        </div>
    );
}

