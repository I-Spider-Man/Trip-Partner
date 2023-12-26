import React from 'react'
import NavBar from '../NavBar/NavBar'
import SearchBar from '../SearchBar/SearchBar'
import './Home.css'
import Events from '../Events/Events'
import Hotspots from './Hotspots'
function Home() {
  return (
    <div className='home'>
        <div className='nav'><NavBar/></div>
        <SearchBar/>
<<<<<<< HEAD
        <div><h3>Events</h3><div className='events-container'><Events/><Events/></div></div>
        <div><h3>Popular Spots</h3><div className='hotspots-container'><Hotspots/><Hotspots/><Hotspots/><Hotspots/><Hotspots/><Hotspots/></div>
=======
        <div><h3>Events</h3>
          <div className='events-container'>
          <Events/>
          <Events/>
          <Events/>
          <Events/>
          <Events/>
          <Events/>
          <Events/>
        </div></div>
        <div><h3>Popular Spots</h3><div className='hotspots'><Hotspots/><Hotspots/><Hotspots/><Hotspots/><Hotspots/><Hotspots/></div>
>>>>>>> 98a1db2bea441de136e4cb7c6d43454aa9761ffa
</div>
    </div>
  )
}

export default Home
