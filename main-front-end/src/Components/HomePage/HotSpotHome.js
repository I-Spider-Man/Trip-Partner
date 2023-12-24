import React from 'react'
import NavBar from '../NavBar/NavBar'
import Hotspots from './Hotspots'

function HotSpotHome() {
  return (
    <div>
      <div className='nav'>
        <NavBar/>
      </div>
      <div className='hotspot'>
        <Hotspots/>
      </div>
    </div>
  )
}

export default HotSpotHome
