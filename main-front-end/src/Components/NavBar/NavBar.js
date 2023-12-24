// NavBar.js

import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Login from "A://Trip partner internship project//Trip partner office branch//Trip-Partner-//main-front-end//src//Assests//login_signUp.svg";
import './NavBar.css';
import '../styleguide.css';
import LoginPage from '../LoginPage/LoginPage';

function NavBar() {
  const [visible, setvisible] = useState(false);

  const login = () => {
    setvisible(!visible);
  };

  return (
    <div className='nav-bar'>
      <div className='text-wrapper'>
        <Link to="/" style={{ textDecoration: "none", color: "black" }}>
          Travel Partner
        </Link>
      </div>
      <div className='menu-list'>
        <div className='nav'>
          <Link to="/EventsHome">
            <button>Events</button>
          </Link>
        </div>
        <div className='nav'>
          <Link to="/HotSpotsHome">
            <button>Hot Spots</button>
          </Link>
        </div>
        <div className='nav'>
          <Link to="/OrganizerHome">
            <button>Organizers</button>
          </Link>
        </div>
      </div>
      <div className='login-signup-outline'>
        <button className='login-button' onClick={login}>
          <img className='login-signup' alt='login or sign up' src={Login} />
        </button>
      </div>
      {visible && <LoginPage onClose={() => setvisible(false)} />}
    </div>
  );
}

export default NavBar;
