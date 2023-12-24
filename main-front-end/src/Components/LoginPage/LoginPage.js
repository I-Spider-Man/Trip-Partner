import React, { useState } from 'react';
import './LoginPage.css';
import axios from 'axios';
function LoginPage({ onClose }) {
  const [otpInput, setOtpInput] = useState(false);
  const [email, setEmail] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [isPopupVisible, setPopupVisible] = useState(false);

  const [userName,setUserName]=useState("");
  const [userPassword,setUserPassword]=useState("");
  const [userPasswordC,setUserPasswordC]=useState("");
  const [userEmail,setUserEmail]=useState("");
  const [userotp,setUserOtp]=useState("");
  const [otp,setOtp] = useState("");
  const [showOtpInput, setShowOtpInput] = useState(false);

  const getOtp=async()=>{
    toggleOTPinput();
    await axios.get(`http://localhost:8080/User/otp/${userEmail}`)
    .then(response=>{
      alert("check your email for otp");
      setOtp(response.data);
      console.log(response.data);
      console.log(response.config);
      console.log(response.headers);
      console.log(response.status);
      console.log(response.statusText);
    })
    .catch(error => {
      if (error.response.status === 400) {
        alert("Email already present");
        console.error('Error fetching data:', error);
      }
    });
}

const handleSubmit=async()=>{
  console.log('Current state values:', { otp, userotp, userPassword, userPasswordC });
  alert(otp);
  alert(userotp);
  if(otp == userotp){
      if(userPassword === userPasswordC){
          try {
              const response = await axios.post('http://localhost:8080/User', {
                userName,
                userPassword,
                userEmail,
              });
              // Handle success, e.g., redirect to another page or show a success message
              console.log(response.data);
            } catch (error) {
              // Handle error, e.g., show an error message
              console.error('Error registering user:', error);
            }
}else{
  alert("check password");
}
  }else{
      alert("Entered otp is wrong.");
  }
}
const handleOtpChange = (e) => {
  const value = e.target.value;
  if (/^\d*$/.test(value)) {
    const formattedOtp = value.slice(0, 6);
    setUserOtp(formattedOtp);
    const formattedDisplay = formattedOtp
      .split('')
      .map((digit, index) => (index === 5 ? digit : digit + '-'))
      .join('');
    e.target.value = formattedDisplay;
  }
};

  const validateEmail = () => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (emailRegex.test(email)) {
      setErrorMessage('Invalid email address.');
      setPopupVisible(true);
    } else {
      getOtp();
      
    }
    
  };

  const closePopup = () => {
    setPopupVisible(false);
  };
  const toggleOTPinput = () =>{
    setOtpInput(!otpInput);
  };
  const popupStyle = {
    display: 'block',
    position: 'fixed',
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    padding: '20px',
    backgroundColor: '#fff',
    border: '1px solid #ccc',
    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
    zIndex: 1000,
  };

  return (
    <div className='login-page-overlay' onClick={onClose}>
      <div className="login-signup-container" onClick={(e)=> e.stopPropagation()}>
    <input type="checkbox" id="chk" aria-hidden="true" />




    <div className="login">
      <form className="form">
        <label htmlFor="chk" aria-hidden="true">Log in</label>
        <input
          className="input"
          type="email"
          name="email"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          onBlur={validateEmail}
          required
        />
        <input className="input" type="password" name="pswd" placeholder="Password" required />
        <button>Log in</button>
      </form>
    </div>






    <div className="register">
      <form className="form" onSubmit={handleSubmit}>
        <label htmlFor="chk" aria-hidden="true">Register</label>
        <input className="input" type="text" value={userName} name="userName" placeholder="Username" required onChange={(e)=>setUserName(e.target.value)}/>
        <div className='reg' style={{ display: "flex", flexDirection: "row" }}>
         {otpInput ? (<><input
      className="input"
      type="text"
      name="otpreg"
      placeholder="Enter OTP"
      pattern="\d{6}" 
      value={userotp}
      onChange={(e)=>setUserOtp(e.target.value)}
      style={{ paddingRight: '40px' }}
      required
    />
          <button className='verify-button'
            style={{ position: 'absolute',
            right: '5px',
            top: '50%',
            transform: 'translateY(-204%) translateX(-151%)', tabSize: "30px" }} 
            onClick={toggleOTPinput}
          >
            Check Email
          </button></>
            
           ) : (<><input
            className="input"
            type="email"
            name="email"
            placeholder="Email"
            value={userEmail}
            onChange={(e) => setUserEmail(e.target.value)}
            style={{ paddingRight: '40px' }}
            required
          />
          <button className='verify-button'
          style={{ position: 'absolute',
          right: '5px',
          top: '50%',
          transform: 'translateY(-204%) translateX(-151%)', tabSize: "30px" }} 
          onClick={validateEmail}
          disabled={!userEmail.trim()}
        >
          Get otp
        </button></>
            
           )} 
          
        </div>
        <input className="input" type="password" value={userPassword} name="pswd" placeholder="Password" required onChange={(e)=>setUserPassword(e.target.value)}/>
        <input className="input" type="password" value={userPasswordC} name="cpswd" placeholder="Confirm Password" required onChange={(e)=>setUserPasswordC(e.target.value)}/>
        <button >Register</button>
      </form>
    </div>
    </div>
        {isPopupVisible && (
          <div style={popupStyle}>
            <p>{errorMessage}</p>
            <button onClick={closePopup}>Close</button>
          </div>
        )}
      </div>
  );
}

export default LoginPage;
