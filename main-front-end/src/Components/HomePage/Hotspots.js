import React from 'react'
<<<<<<< HEAD
import Logo from 'C://Users//alfredeins_gabriel//Desktop//React//Trip-Partner-front-end//main-front-end//src//Assests//Modules.png'
=======
import Logo from 'A://Trip partner internship project//Trip partner office branch//Trip-Partner-//main-front-end//src//Assests//Modules.png'
>>>>>>> 98a1db2bea441de136e4cb7c6d43454aa9761ffa
import './Hotspots.css'
import '../styleguide.css'
function Hotspots({spotName}) {
  return (
    <div>
<<<<<<< HEAD
      <div className="spot-card">
  <svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M20 5H4V19L13.2923 9.70649C13.6828 9.31595 14.3159 9.31591 14.7065 9.70641L20 15.0104V5ZM2 3.9934C2 3.44476 2.45531 3 2.9918 3H21.0082C21.556 3 22 3.44495 22 3.9934V20.0066C22 20.5552 21.5447 21 21.0082 21H2.9918C2.44405 21 2 20.5551 2 20.0066V3.9934ZM8 11C6.89543 11 6 10.1046 6 9C6 7.89543 6.89543 7 8 7C9.10457 7 10 7.89543 10 9C10 10.1046 9.10457 11 8 11Z"></path></svg>
  <div className="spot-card__content">
    <p className="spot-card__title">Project Name</p>
    <p className="spot-card__description">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco.</p>
    <button className="spot-card__button"><span className='view-more'>View More!</span></button>
  </div>
</div>

=======
      <div className="list-card">
                <div className="card">
                    <div className="overlap-group">
                        <div className='spot-image'>
                            <img className="rectangle" alt="Rectangle" src={Logo} />
                        </div>
                        <div className="div-2">
                            <div className="div">
                                <div className="div-4">
                                    <div className="text-wrapper-3">{spotName}</div>
                                </div>
                            </div>
                            <p className="join-organize-button">
                                <button>Join</button><button>Organize</button>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
>>>>>>> 98a1db2bea441de136e4cb7c6d43454aa9761ffa
    </div>
  )
}

export default Hotspots
