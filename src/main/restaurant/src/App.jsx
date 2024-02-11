import React from 'react';
import { Route, Routes } from 'react-router-dom';
import './App.css'
import Header from './Header/Header'
import Footer from './Footer/Footer'
import Main from './Main/Main'
import Login from './Login/Login'
import Reservation from './Reservation/Reservation';
import About from './About/About';
import Menu from './Menu/Menu'
import Contact from './Contact/Contact'

export default function App() {
  return (
    <div>
      <Header/>
      <Routes>
        <Route path='/*' element={<Main/>}/>
        <Route path='/login/:value' element={<Login/>}/>
        <Route path='/reservation' element={<Reservation/>}/>
        <Route path='/about/:subMenu' element={<About/>}/>
        <Route path='/menu/:subMenu' element={<Menu/>}/>
        <Route path='/contact/:subMenu' element={<Contact/>}/>
      </Routes>
      <Footer/>
    </div>
  );
}


