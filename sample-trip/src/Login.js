import React, { useState } from 'react'
import axios from 'axios'
function Login() {
    const [userName,setUserName]=useState("");
    const [userPassword,setUserPassword]=useState("");
    const [userPasswordC,setUserPasswordC]=useState("");
    const [userEmail,setUserEmail]=useState("");
    const [userotp,setUserOtp]=useState("");
    const [otp,setOtp] = useState("");
    const [showOtpInput, setShowOtpInput] = useState(false);

    const getOtp=async()=>{
        const response=await axios.get(`http://localhost:8080/User/otp/${userEmail}`)
        if(response.data!=null){
            setOtp(response.data);
            alert(response.data);
            setShowOtpInput(true);
        }else{
            alert("Email already registered");
        }
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
  return (
    <div>
        <form onSubmit={handleSubmit} style={{display:"flex", flexDirection:"column", paddingInline:"10px", alignItems:"center"}}>
      <input type='text' placeholder="Enter your name" value={userName} onChange={(e) => setUserName(e.target.value)} required />
      <input type='password' placeholder='Enter your password' value={userPassword} onChange={(e) => setUserPassword(e.target.value)} required />
      <input type='password' placeholder='Re-enter your password' value={userPasswordC} onChange={(e) => setUserPasswordC(e.target.value)} required />
      <input type='email' value={userEmail} placeholder='Enter your email' onChange={(e) => setUserEmail(e.target.value)} />
      <button type="button" onClick={getOtp} disabled={!userEmail.trim()}>Send OTP</button>
      {showOtpInput && (
        <>
          <input type='text' value={userotp} placeholder='Enter OTP' onChange={(e) => setUserOtp(e.target.value)} required />
          <button type="submit" disabled={!userotp.trim() || userPassword.trim() === '' || userPasswordC.trim() === ''}>Register</button>
        </>
      )}
    </form>
    </div>
  )
}
export default Login