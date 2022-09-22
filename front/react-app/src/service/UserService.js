import axios from 'axios';
import qs from 'qs';

const LOGIN_API_BASE_URL = "http://localhost:8080/login";    /* 로그인 URL */
const SIGNUP_API_BASE_URL = "http://localhost:8080/signup";  /* 회원가입 URL */
axios.defaults.xsrfCookieName = 'csrftoken';
axios.defaults.xsrfHeaderName = 'X-CSRFTOKEN'

/* 회원 관리 */
class UserService {
    /* 로그인 */
    login(username, password) {
        const axiosConfig = {
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            }
        }

        return axios.post(LOGIN_API_BASE_URL,
            qs.stringify({ username: username, password: password }),
            axiosConfig)
            .then(localStorage.setItem("user", username))
            .catch(err => console.log(err));
    }

    /* 로그아웃 */
    logout() {
        localStorage.removeItem("user");
    }

    /* 특정 회원 조회 */
    getOneUser(username){
        return axios.get(LOGIN_API_BASE_URL+"/"+username);
    }

    /* 회원 정보 변경 */
    updateUser(username, user){
        return axios.put(LOGIN_API_BASE_URL+"/"+username, user);
    }

    /* 현재 로그인 한 회원 조회 */
    getCurrentUser() {
        return localStorage.getItem("user");
    }

    /* 회원가입 */
    signup(userDto) {
        return axios.post(SIGNUP_API_BASE_URL, userDto);
    }
}

export default new UserService();