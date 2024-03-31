# 알고바로 (Algobaro)

![Algobaro Logo](https://github.com/1e5i-Shark/algobaro-api/assets/113650170/011959cc-09a9-468d-853b-e7c20b13c9e8)

- [Deploy Link](https://algobaro.vercel.app)
- [GitHub Wiki](https://github.com/1e5i-Shark/algobaro-api/wiki)
- [FE Repository](https://github.com/1e5i-Shark/algobaro-fe)

<br>

## 🔍 Overview

> 알고바로 (AlgoBaro) 바로바로 함께 푸는 알고리즘 테스트

실제 코딩테스트 유사하게 시간 제한과 기본 레퍼런스만 제공되는 환경에서 코딩테스트를 다 같이 풀고, 여러 사람의 풀이 코드를 공유할 수 있는 서비스

![Algobaro Landing](https://github.com/1e5i-Shark/algobaro-api/assets/113650170/8d34ee83-da80-412d-82ce-df4426eaed5a)

<br>

## 📝 Specification

- Auth: 인증
- Members: 회원
- Rooms: 방
- Problems: 문제 정보
- Solves: 문제 풀이
- Compile: 코드 컴파일 및 실행

크게 6가지의 Domain으로 구성되어 있으며, 각 Domain 기능을 제공하기 위해 Rest API와 WebSocket 두 가지 통신 방식을 사용한다.

<br>

## 🔬 Domain Analysis

### Room Life Cycle

![Algobaro Room Life Cycle](https://github.com/1e5i-Shark/algobaro-api/assets/113650170/69afd520-1abf-4548-845e-0693310b714a)

<br>

## 🛠 System Design

### Stack

<img src="https://img.shields.io/badge/Java 17-008FC7?style=for-the-badge&logo=Java&logoColor=white"></img>

<img src="https://img.shields.io/badge/Spring 6.1.3-58CC02?style=for-the-badge&logo=Spring&logoColor=white"/></img>
<img src="https://img.shields.io/badge/Spring Boot 3.2.2-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"/></img>
<img src="https://img.shields.io/badge/Spring Security 6.2.1-669DF6?style=for-the-badge&logo=JPA&logoColor=white"/></img>
<img src="https://img.shields.io/badge/Spring Data JPA-ECD53F?style=for-the-badge&logo=JPA&logoColor=white"/></img>
<img src="https://img.shields.io/badge/WebSocket-000000?style=for-the-badge&logo=WebSocket&logoColor=white"/></img>

<img src="https://img.shields.io/badge/MySQL 8.0-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"></img>
<img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"></img>

### CI/CD

![CI/CD](https://github.com/1e5i-Shark/algobaro-api/assets/113650170/18d20e7a-5004-4656-be18-7ca95fef1c74)

### Infrastructure

![Infrastructure](https://github.com/1e5i-Shark/algobaro-api/assets/113650170/5c217611-fe7c-4eae-a5d9-75bb7e1fa2bc)

<br>

###### ETC

그 외 자세한 내용은 [Wiki Home](https://github.com/1e5i-Shark/algobaro-api/wiki) 참고

