import React from 'react'
<<<<<<< HEAD
import Logo from 'C://Users//alfredeins_gabriel//Desktop//React//Trip-Partner-front-end//main-front-end//src//Assests//logo.svg'
import './Events.css'
import '../styleguide.css'
import { Link } from 'react-router-dom'
function Events({eventId,eventName,eventDiscription}) {

  return (
    <div className="event-card">
  <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M20 5H4V19L13.2923 9.70649C13.6828 9.31595 14.3159 9.31591 14.7065 9.70641L20 15.0104V5ZM2 3.9934C2 3.44476 2.45531 3 2.9918 3H21.0082C21.556 3 22 3.44495 22 3.9934V20.0066C22 20.5552 21.5447 21 21.0082 21H2.9918C2.44405 21 2 20.5551 2 20.0066V3.9934ZM8 11C6.89543 11 6 10.1046 6 9C6 7.89543 6.89543 7 8 7C9.10457 7 10 7.89543 10 9C10 10.1046 9.10457 11 8 11Z"></path></svg>
  <div className="event-card__content">
    
    <p className="event-card__title">{eventName}</p>
    <p className="event-card__description">{eventDiscription}</p>
    <Link to={`/Events/${encodeURIComponent(eventId)}`}><button class="event-card__button"><span className='event-view-more'>View More!</span></button></Link>
  </div>
</div>

=======
import Logo from 'A://Trip partner internship project//Trip partner office branch//Trip-Partner-//main-front-end//src//Assests//logo.svg'
import './Events.css'
import '../styleguide.css'
function Events({eventName}) {
  return (
    <div>
      <div className="list-card">
            <div className="card">
                <div className="overlap-group">
                    <div className='event'>
                        <img className="rectangle" alt="Rectangle" src={Logo} />
                    </div>
                    <div className="div-2">
                        <div className="div">
                            <div className="div-4">
                                <div className="text-wrapper-3">{eventName}</div>
                            </div>
                        </div>
                        <p className="join-organize-button">
                            <button>Join</button>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
>>>>>>> 98a1db2bea441de136e4cb7c6d43454aa9761ffa
  )
}

export default Events
