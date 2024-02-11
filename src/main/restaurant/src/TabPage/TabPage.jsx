import React, { useEffect, useState } from 'react';
import './TabPage.css'
import { useNavigate, useParams } from 'react-router-dom';

export default function TabPage(props) {
    const navigate = useNavigate()
    const { subMenu } = useParams()

    const tabStyle = (i)=>{
        return{
            backgroundColor : subMenu === props.tabLink[i] ? 'rgb(194,122,52)' : ''
        }
    }

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
                                        <li className='tab-item' key={i} onClick={()=>{navigate(`/about/${props.tabLink[i]}`)}} style={tabStyle(i)}>
                                            <a className="tab-link" onClick={()=>{navigate(`/about/${props.tabLink[i]}`)}}>{a}</a>
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

