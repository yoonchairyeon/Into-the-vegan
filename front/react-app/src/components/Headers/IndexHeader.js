import React from "react";

// reactstrap components
import { Container } from "reactstrap";

// core components

function IndexHeader() {
  return (
    <>
      <div
        className="page-header section-dark"
        style={{
          backgroundImage:
            "url(" + require("assets/img/home_picture.jpg") + ")",
        }}
      >
      </div>
    </>
  );
}

export default IndexHeader;