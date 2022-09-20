
import React from "react";

// reactstrap components

// core components
import IndexNavbar from "components/Navbars/IndexNavbar.js";
import IndexHeader from "components/Headers/IndexHeader.js";

// index sections
import Banner from "./index-sections/Banner";
import Magazine from "./index-sections/Magazine";
import ItemNavbars from "./index-sections/ItemNavbars";
import VeginFooter from "components/Footers/VeginFooter";

function Index() {
  document.documentElement.classList.remove("nav-open");
  React.useEffect(() => {
    document.body.classList.add("index");
    return function cleanup() {
      document.body.classList.remove("index");
    };
  });
  return (
    <>
      <IndexNavbar />
      <IndexHeader />
      <div className="main">
        <ItemNavbars/>
        <Banner/>
        <Magazine/>
        <VeginFooter />
      </div>
    </>
  );
}

export default Index;