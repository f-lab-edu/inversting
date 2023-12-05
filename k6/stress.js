import http from "k6/http";
import { check, sleep } from "k6";

// 평균 rps - 550 , 최대 rps - 1000
export let options = {
    stages: [
        { duration: "1m", target: 100 },
        { duration: "1m", target: 200 },
        { duration: "1m", target: 300 },
        { duration: "1m", target: 400 },
        { duration: "1m", target: 500 },
        { duration: "1m", target: 600 },
        { duration: "1m", target: 700 },
        { duration: "1m", target: 800 },
        { duration: "1m", target: 900 },
        { duration: "1m", target: 1000 }
    ],

    thresholds: {
        http_req_duration: ["p(99)<1500"], // 99% of requests must complete below 1.5s
    },
};

const BASE_URL = "https://investing.n-e.kr";
const USERNAME = "flab@flab.com";
const PASSWORD = "1234";

export default function () {
    //메인 페이지 주식 조회
    mainPage();

    //로그인
    let token = login();

    // 주식 조회
    searchStock();

    // 주식 구매
    purchase(token);

    sleep(1);
}

function mainPage() {
    let mainRes = http.get(`${BASE_URL}/stocks`);
    check(mainRes, {
        "go mainPage successfully": (resp) => resp.status == 200,
    });
}

function login() {
    let payload = JSON.stringify({
        userId: `${USERNAME}`,
        password: `${PASSWORD}`,
    });

    let params = {
        headers: {
            "Content-Type": "application/json",
        },
    };

    let loginRes = http.post(`${BASE_URL}/users/login`, payload, params);

    check(loginRes, {
        "logged in successfully": (resp) => resp.json("accessToken") !== "",
    });

    return loginRes.json("accessToken");
}

function searchStock() {
    let changeInfoRes = http.get(`${BASE_URL}/stocks/10001`);
    check(changeInfoRes, {
        "search Stock successfully": (response) => response.status === 200,
    });
}

function purchase(accessToken) {
    let payload = JSON.stringify({
        stockId: "10001",
        stockOfAmount: "9430",
        stockCount: "1",
    });

    let params = {
        headers: {
            "Content-Type": "application/json",
            accessToken: accessToken,
        },
    };

    let pathRes = http.post(`${BASE_URL}/stocks/purchases`, payload, params);
    check(pathRes, {
        "stock purchases successfully": (resp) => resp.status == 200,
    });
}
