import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter, Route, Redirect, Switch } from "react-router-dom";

import Index from "./views/Index.js";
import Join from "./views/examples/Join.js";
import NucleoIcons from "./views/NucleoIcons.js";

ReactDOM.render(
  <BrowserRouter>
    <Switch>
      <Route path="/index" render={(props) => <Index {...props} />} />
      
      <Route
        path="/nucleo-icons"
        render={(props) => <NucleoIcons {...props} />}
      />

      <Route
        path="/join"
        render={(props) => <Join {...props} />}
      />
      
      
      <Redirect to="/index" />
    </Switch>
  </BrowserRouter>,
  document.getElementById("root")
);