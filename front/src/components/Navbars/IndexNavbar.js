/*!

=========================================================
* Paper Kit React - v1.3.0
=========================================================

* Product Page: https://www.creative-tim.com/product/paper-kit-react

* Copyright 2021 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/paper-kit-react/blob/main/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import React, { useState, useEffect } from "react";
// nodejs library that concatenates strings
import classnames from "classnames";
import 'assets/scss/paper-kit/_indexnavbar.scss'
// reactstrap components
import {
  Collapse,
  NavbarBrand,
  Navbar,
  NavItem,
  NavLink,
  Nav,
  UncontrolledDropdown,
  DropdownToggle,
  DropdownMenu,
  DropdownItem,
  Container,
} from "reactstrap";
import MemberService from "service/MemberService";

function IndexNavbar() {
  const [navbarCollapse, setNavbarCollapse] = useState(false);
  const [activeName, setActiveName] = useState("");

  const toggleNavbarCollapse = () => {
    setNavbarCollapse(!navbarCollapse);
    document.documentElement.classList.toggle("nav-open");
  };

  useEffect(() => {
    if(window.location.pathname.includes("index")) {
      setActiveName("HOME");
    }
    else if(window.location.pathname.includes("recipe")) {
      setActiveName("RECIPE");
    }
    else if(window.location.pathname.includes("place")) {
      setActiveName("PLACE");
    }
    else if(window.location.pathname.includes("board") || window.location.pathname.includes("diary")) {
      setActiveName("COMMUNITY");
    }
  }, [window.location.pathname]);

  const logout = () => {
    MemberService.logout();
    window.location.replace("/");
  }

  return (
    <div>
      <Navbar expand="lg">
        <Container fluid>
          <div className="col d-none d-lg-block"></div>
          <div className="col text-center">
            <NavbarBrand
              className="pb-0"
              data-placement="bottom"
              href="/index"
              target="_top"
              title="Into the vegan"
            >
              <img
                alt="Logo"
                src={require("assets/img/home_logo.png")}
                width="300px"
                max-width="100%"
                height="auto"
              />
            </NavbarBrand>
          </div>
          <div className="col d-none d-lg-block">
            {localStorage.length === 0 
            ? <Nav navbar className="justify-content-end">
                <NavItem>
                  <NavLink
                    className="pt-0 mt-0"
                    data-placement="bottom"
                    href="/login-page"
                    title="Login"
                  >
                    <p>Login</p>
                  </NavLink>
                </NavItem>
                <NavItem>
                  <NavLink
                    className="pt-0 mt-0"
                    data-placement="bottom"
                    href="/join-page"
                    title="Join"
                  >
                    <p>Join</p>
                  </NavLink>
                </NavItem>
                </Nav>
              : <Nav navbar className="justify-content-end">
                  <NavItem>
                    <NavLink
                      className="pt-0 mt-0"
                      data-placement="bottom"
                      onClick={()=>logout()}
                      style={{cursor:'pointer'}}
                      title="Login"
                    >
                      <p>Logout</p>
                    </NavLink>
                  </NavItem>
                  <NavItem>
                    <NavLink
                      className="pt-0 mt-0"
                      data-placement="bottom"
                      href="/mypage-page"
                      title="My Page"
                    >
                      <p>My page</p>
                    </NavLink>
                  </NavItem>
                </Nav>}
          </div>
        </Container>
      </Navbar>
      <Navbar expand="lg">
        <Container>
          <div className="navbar-translate">
            <NavbarBrand>
            </NavbarBrand>
            <button
              aria-expanded={navbarCollapse}
              className={classnames("navbar-toggler navbar-toggler", {
                toggled: navbarCollapse,
              })}
              onClick={toggleNavbarCollapse}
            >
              <span className="navbar-toggler-bar bar1" />
              <span className="navbar-toggler-bar bar2" />
              <span className="navbar-toggler-bar bar3" />
            </button>
          </div>
          <Collapse
            className="justify-content-center"
            navbar
            isOpen={navbarCollapse}
          >
            <Nav navbar>
              <NavItem>
                <NavLink
                  className={`px-5 ${activeName === "HOME" ? "active" : ""}`}
                  data-placement="bottom"
                  href="/index"
                  title="HOME"
                >
                  <p>HOME</p>
                </NavLink>
              </NavItem>
             
              <NavItem>
                <NavLink
                  className={`px-5 ${activeName === "RECIPE" ? "active" : ""}`}
                  data-placement="bottom"
                  href="/recipe-page"
                  title="RECIPE"
                >
                  <p>RECIPE</p>
                </NavLink>
              </NavItem>
              <NavItem>
                <NavLink
                  className={`px-5 ${activeName === "PLACE" ? "active" : ""}`}
                  data-placement="bottom"
                  href="/place-page"
                  title="PLACE"
                >
                  <p>PLACE</p>
                </NavLink>
              </NavItem>
              <UncontrolledDropdown nav inNavbar className='in-dropdown-wrapper'>
                <DropdownToggle 
                  className={`in-dropdown ${activeName === "COMMUNITY" ? "active" : ""}`}
                  style={{ height: '47px', padding: '0px 48px', margin: '15px 3px' }}>
                  COMMUNITY
                  </DropdownToggle>
                <DropdownMenu
                  aria-labelledby="dropdownMenuButton"
                  className='dropdown-info'
                  style={{ minWidth: '211px' }}
                >
                  <DropdownItem
                    href="/board"
                    className="in-dropdown-item"
                  >
                    자유게시판
                      </DropdownItem>
                  <DropdownItem
                    href="/diary"
                    className="in-dropdown-item"
                  >
                    다이어리
                      </DropdownItem>
                </DropdownMenu>
              </UncontrolledDropdown>
            </Nav>
          </Collapse>
        </Container>
      </Navbar>
    </div>
  );
}

export default IndexNavbar;