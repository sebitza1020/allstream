import React, { useState } from "react";
import ReactDOM from "react-dom";
import Home from "../../pages/Home";

const Login = () => {
    const [mesajEroare, setMesajEroare] = useState({});
    const [butonApasat, setButonApasat] = useState(false);

    

    const erori = {
        uname: "Username invalid",
        pass: "Password invalid"
      };

    const createMessage = (name) =>
    name === mesajEroare.name && (
      <div className="error">{mesajEroare.message}</div>
    );

    const handleLogin = (event) => {
        event.preventDefault();
    
        var { uname, pass } = document.forms[0];
    
        const userData = database.find((user) => user.username === uname.value);
    
        if (userData) {
          if (userData.password !== pass.value) {
            setMesajEroare({ name: "pass", message: erori.pass });
          } else {
            setButonApasat(true);
          }
        } else {
          setMesajEroare({ name: "uname", message: erori.uname });
        }
      };

    const renderForm = (
        <div className="login-form">
          <form onSubmit={handleLogin}>
            <div className="text-input">
              <label>Username </label>
              <input type="text" name="uname" required />
              {createMessage("uname")}
            </div>
            <div className="text-input">
              <label>Password </label>
              <input type="password" name="pass" required />
              {createMessage("pass")}
            </div>
            <div className="button-container">
              <input type="submit" />
            </div>
          </form>
        </div>
      );
    
      return (
        <div className="app">
          <div className="login-form">
            <div className="title">Sign In</div>
            {butonApasat ? <Home /> : renderForm}
          </div>
        </div>
      );
}

export default Login;