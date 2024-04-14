import React from 'react';
import './AdminPage.css'
import AdminUser from './AdminUser'
import AdminReserv from './AdminReserv'
import { useLocation, useNavigate, useParams } from 'react-router-dom';

export default function AdminPage() {
    const location = useLocation();
    const subMenu = location.pathname.split('/').pop();
    const navigate = useNavigate()

    return (
        <div className='admin-container'>
            <div className="admin-side">
                <div className="admin-title">WooDy Admin</div>
                <ul className="admin-menu-list">
                    <li className='admin-menu' onClick={()=>{navigate('/admin/user')}}>회원 목록</li>
                    <li className='admin-menu' onClick={()=>{navigate('/admin/reserv')}}>예약 목록</li>
                </ul>
            </div>
            <div className="admin-main">
                <h1 className="admin-main-title">{subMenu === 'user' ? '회원 목록' : subMenu === 'reserv' ? '예약 목록' : '관리자 페이지'}</h1>
                <div className="admin-main-detail">
                    {subMenu === 'user' ? <AdminUser/> : subMenu === 'reserv' ? <AdminReserv/> : null}
                </div>
            </div>
        </div>
    );
}

