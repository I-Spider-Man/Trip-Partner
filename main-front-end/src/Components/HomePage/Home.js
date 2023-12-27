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
        <div><h3>Events</h3><div className='events-container'><Events/><Events/></div></div>
        <div><h3>Popular Spots</h3><div className='hotspots-container'><Hotspots/><Hotspots/><Hotspots/><Hotspots/><Hotspots/><Hotspots/></div>
</div>
    </div>
  )
}

export default Home
