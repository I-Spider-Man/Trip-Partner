import React from 'react'
import Logo from 'C://Users//alfredeins_gabriel//Desktop//React//Trip-Partner-front-end//main-front-end//src//Assests//logo.svg'
import './EventComponent.css'
import '../styleguide.css'
import { Link } from 'react-router-dom'
function Events({eventId,eventName,eventDiscription}) {

  return (
    <div className="event-card">
  <img src={Logo} alt='event images'/>
  <div className="event-card__content">
    
    <p className="event-card__title">{eventName}</p>
    <p className="event-card__description">{eventDiscription}</p>
    <Link to={`/Events/${encodeURIComponent(eventId)}`}><button class="event-card__button"><span className='event-view-more'>View More!</span></button></Link>
  </div>
</div>

  )
}

export default Events
