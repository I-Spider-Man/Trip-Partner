import React from 'react'
import NavBar from '../NavBar/NavBar'
import Events from '../Events/Events'

function EventsHome() {
  return (
    <div>
      <div className='nav'><NavBar/></div>
      <div className='event'><Events/></div>
    </div>
  )
}

export default EventsHome