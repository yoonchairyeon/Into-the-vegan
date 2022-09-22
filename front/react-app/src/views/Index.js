import React from "react";

// core components
import IndexNavbar from "components/Navbars/IndexNavbar.js";
import IndexHeader from "components/Headers/IndexHeader.js";

// index sections
// import Banner from "./index-sections/Banner";
// import Magazine from "./index-sections/Magazine";
// import ItemNavbars from "./index-sections/ItemNavbars";
import Footer from "components/Footers/Footer";

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
        <Footer />
      </div>
    </>
  );
}

export default Index;