import React from "react";

// reactstrap components
import { Row, Container } from "reactstrap";

function Footer() {
  return (
    <footer className="footer footer-black footer-white">
      <hr></hr>
      {/* <Container> */}
      {/* <Row> */}
      <nav className="footer-nav">
        <ul className="footer-inner">
          <div className="foot_left">
            <ul>
              <li className="footer-title">
                <span>CS</span>
              </li>
              chryis14@skuiv.ac.kr
            </ul>
            <ul>
              <li className="tel">010-1234-5678</li>
              <li>chryis14@skuiv.ac.kr</li>
              <li>
                10:00 ~ 18:00 (평일 및 주말 휴무)<br />
                점심시간 12:00 ~ 13:30
              </li>
            </ul>
          </div>
          <div className="foot_center">
            <ul>
              <li className="footer-title">
                <span>COMPANY</span>
              </li>
              <div>
                <span className="ft-center-title">
                  상호
                </span>
                <span>&nbsp;&nbsp;(주)윤채련</span>
                <span className="ft-center-title">
                  대표
                </span>
                <span>&nbsp;&nbsp;윤채련</span>
                <span className="ft-center-title">
                  사업자등록번호
                </span>
                <span>&nbsp;&nbsp;02-760-8076</span>
                <br />
                <span className="ft-center-title">
                  전화
                </span>
                <span>&nbsp;&nbsp;02-000-000</span>
                <span className="ft-center-title">
                  주소
                </span>
                <span>&nbsp;&nbsp;서울특별시 성북구 서경대학교</span>
                <br />
                <span className="ft-center-title">
                  개인정보관리책임자
                </span>
                <span>&nbsp;&nbsp;윤채련 (chryis14@naver.com)</span>
              </div>
            </ul>
          </div>
          <div className="foot_right">
            <ul>
              <li className="footer-title">
                <span>BANK</span>
              </li>
            </ul>
            <ul>
              <li>
                <span>우리은행</span>
                <br />
                <span>1002-000-000000</span>
                <br />
                <span>윤채련</span>
              </li>
            </ul>
          </div>
        </ul>
      </nav>
    </footer>
  );
}

export default Footer;
