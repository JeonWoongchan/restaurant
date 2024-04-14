import React, { useEffect } from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';
import './App.css'
import "./index.css";
import Header from './Header/Header'
import Footer from './Footer/Footer'
import Main from './Main/Main'
import Login from './Login/Login'
import Reservation from './Reservation/Reservation';
import About from './About/About';
import Menu from './Menu/Menu'
import Contact from './Contact/Contact'
import Payment from './Reservation/Payment';
import AdminPage from './AdminPage/AdminPage';

export default function App() {
  const location = useLocation();
  const isAdminPage = location.pathname.startsWith('/admin');

  return (
    <div>
      {!isAdminPage && <Header />}
      <Routes>
        <Route path='/admin/*' element={<AdminPage />} />
        <Route path='/*' element={<Main />} />
        <Route path='/login/:value' element={<Login />} />
        <Route path='/reservation' element={<Reservation />} />
        <Route path='/payment' element={<Payment />} />
        <Route path='/about/:subMenu' element={<About />} />
        <Route path='/menu/:subMenu' element={<Menu />} />
        <Route path='/contact/:subMenu' element={<Contact />} />
        <Route path='/my-page/:subMenu' element={<MyPage />} />
      </Routes>
      {!isAdminPage && <Footer />}
    </div>
  );
}


