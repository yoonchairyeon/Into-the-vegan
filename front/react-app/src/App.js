import React, { useState, useEffect } from "react";
import "./App.css";
import axios from "axios";


function App() {
  const [ helloStr, setHelloStr ] = useState('');

  function callback(str){
    setHelloStr(str);
  }

  useEffect(
    () => {
    axios({
      url: '/home',
      method : 'GET'
    }).then((res) => {
      callback(res.data);
    })
  }, []);
  return (
    <div className="App">
      <header className="App-header">
       {helloStr}
      </header>
    </div>
  );
}
export default App;
