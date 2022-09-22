

import React, { Component } from 'react';
import { Button, Card, Form, Input, Container, Row, Col, UncontrolledTooltip } from "reactstrap";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser, faFingerprint, faLock, faEnvelope, faPhone, faHouse, faCakeCandles } from '@fortawesome/free-solid-svg-icons'
import IndexNavbar from "components/Navbars/IndexNavbar.js";
import UserService from "service/UserService";
import VeginFooter from "components/Footers/Footer";

/* 회원가입 페이지 */
class JoinPage extends Component {

    constructor(props) {
        super(props)
        this.state = {
            username: '',
            email: '',
            password: '',
            name: ''
        }
    }

    /* 아이디 입력 */
    setUsernameHandler = (e) => {
        this.setState({
            id: e.target.value
        });
    }

    /* 이메일 입력 */
    setEmailHandler = (e) => {
        this.setState({
            email: e.target.value
        });
    }

    /* 비밀번호 입력 */
    setPasswordHandler = (e) => {
        this.setState({
            password: e.target.value
        });
    }

    /* 이름 입력 */
    setNameHandler = (e) => {
        this.setState({
            name: e.target.value
        });
    }

    /* 비밀번호 유효성 검사 */
    checkPassword = (password) => {
        const regExp = /(?=.*[A-Za-z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,20}/g;
        if (regExp.test(password))
            return true;
        else
            return false;
    }

    render() {
        return (
            <>
                <IndexNavbar />
                <div className="join-main">
                    <div className="filter" />
                    <Container>
                        <Row>
                            <Col className="ml-auto mr-auto">
                                <Card className="card-register join ml-auto mr-auto" style={{ backgroundColor: "white" }}>
                                    <h3 className="join-title">JOIN</h3>
                                    <Form className="register-form">
                                        {/* 이름 입력 */}
                                        <div className="join-group" id='bottom-name'>
                                            <span className="join-icon"> <FontAwesomeIcon icon={faUser} /> <text>이름</text> </span>
                                            <Input className='input-join' placeholder="Name" type="text" id="name" onChange={this.setNameHandler} />
                                        </div>
                                        <UncontrolledTooltip placement="top" target="bottom-name" delay={0}>
                                        2자 이상 16자 이하로 입력하시오
                                        </UncontrolledTooltip>
                                        {/* 아이디 입력 */}
                                        <div className="join-group" id='bottom-id'>
                                            <span className="join-icon"> <FontAwesomeIcon icon={faFingerprint} /> <text>아이디</text> </span>
                                            <Input className='input-join' placeholder="ID" type="text" id="id" onChange={this.setIdHandler} />
                                        </div>
                                        <UncontrolledTooltip placement="top" target="bottom-id" delay={0}>
                                        6자 이상 12자 이하로 입력하시오
                                        </UncontrolledTooltip>
                                        {/* 이메일 입력 */}
                                        <div className="join-group" id='bottom-email'>
                                            <span className="join-icon"> <FontAwesomeIcon icon={faEnvelope} /> <text>이메일</text> </span>
                                            <Input className='input-join' placeholder="Email" type="email" id="username"  onChange={this.setEmailHandler} />
                                        </div>
                                        <UncontrolledTooltip placement="top" target="bottom-email" delay={0}>
                                        @을 포함한 이메일을 입력하시오
                                        </UncontrolledTooltip>
                                        {/* 비밀번호 입력 */}
                                        <div className="join-group" id='bottom-password'>
                                            <span className="join-icon"> <FontAwesomeIcon icon={faLock} /> <text>비밀번호</text> </span>
                                            <Input className='input-join' placeholder="Password" type="password" id="password" onChange={this.setPasswordHandler} />
                                        </div>
                                        <UncontrolledTooltip placement="top" target="bottom-password" delay={0} style={{ width: "200px" }}>
                                            영문, 숫자, 특수문자(!@#$%^&*)를 조합하여 8-20자로 입력하시오
                                        </UncontrolledTooltip>
                                      
                                        {/* 회원가입 완료 버튼 */}
                                        <Button block className="join-btn" type='button' onClick={() => {
                                            let memberDto = {
                                                username: this.state.username,
                                                email: this.state.email,
                                                password: this.state.password,
                                                name: this.state.name,
                            
                                            };
                                            /* 회원정보 유효성 검사 */
                                            if (userDto.name == '')
                                                alert("이름을 입력하세요.");
                                            else if (userDto.id == '')
                                                alert("아이디를 입력하세요.");
                                            else if (userDto.password == '')
                                                alert("비밀번호를 입력하세요.");
                                            else if (!this.checkPassword(userDto.password))
                                                alert("비밀번호를 올바르게 입력하세요.");
                                            else if (userDto.email == '')
                                                alert("이메일을 입력하세요.");
                            
                                            else
                                                /* 회원가입 요청 */
                                                UserService.signup(userDto)
                                                    .then(res => this.props.history.push("/signup-success"))
                                                    .catch(err => alert("회원정보를 올바르게 입력하세요."));
                                        }}>
                                            Join
                                        </Button>
                                    </Form>
                                </Card>
                            </Col>
                        </Row>
                    </Container>
                </div>
                <Footer />
            </>
        );
    }
}

export default Join;