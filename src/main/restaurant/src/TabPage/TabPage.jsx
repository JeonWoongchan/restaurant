import React, { useEffect, useState } from 'react';
import './TabPage.css'

export default function TabPage(props) {

    return (
        <div id='tab-page'>
            <div className="tab-page-container">
                <div className="tab-page-image">
                    <img src="/image/login1.png" alt="" />
                </div>
                <div className="tab-page-inner">
                    <div className="tab-wrap">
                        <ul className="tab-list">
                            {props.tabMenu.map((a,i) => {
                                    return (
                                        <li className='tab-item' key={i}>
                                            <a href="" className="tab-link">{a}</a>
                                        </li>
                                    )
                                })}
                        </ul>
                    </div>
                    <div className="tab-page-main">
                        {props.subMenu}
                    </div>
                </div>
            </div>
        </div>
    );
}

