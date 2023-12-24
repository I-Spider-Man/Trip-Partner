import React from 'react'
import Logo from 'A://Trip partner internship project//Trip partner office branch//Trip-Partner-//main-front-end//src//Assests//Modules.png'
import './Hotspots.css'
import '../styleguide.css'
function Hotspots() {
  return (
    <div>
      <div className="list-card">
                <div className="card">
                    <div className="overlap-group">
                        <div className='spot-image'>
                            <img className="rectangle" alt="Rectangle" src={Logo} />
                        </div>
                        <div className="div-2">
                            <div className="div">
                                <div className="div-4">
                                    <div className="text-wrapper-3">Flores Road Trip 3D2N</div>
                                </div>
                            </div>
                            <p className="join-organize-button">
                                <button>Join</button><button>Organize</button>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
    </div>
  )
}

export default Hotspots
