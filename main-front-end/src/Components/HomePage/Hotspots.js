import React from 'react'
import Logo from 'C://Users//alfredeins_gabriel//Desktop//React//Trip-Partner-front-end//main-front-end//src//Assests//Modules.png'
import './Hotspots.css'
import '../styleguide.css'
import { Link } from 'react-router-dom'
function Hotspots({spotId,spotName,spotDescription}) {
  return (
    <div>
      <div className="spot-card">
  <img src={Logo}/>
  <div className="spot-card__content">
    <p className="spot-card__title">{spotName}</p>
    <p className="spot-card__description">{spotDescription}</p>
    <Link to={`/Spot/${encodeURIComponent(spotId)}`}>
      <button className="spot-card__button"><span className='view-more'>View More!</span></button>
    </Link>
  </div>
</div>

    </div>
  )
}

export default Hotspots
