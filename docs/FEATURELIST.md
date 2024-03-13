## 💬 요구사항 분석

---
- 회원은 상품을 주문할 수 있다.
- 주문 시 여러 종류의 상품을 선택할 수 있다.

## 📑 기능 목록

---
<img width="426" alt="스크린샷 2024-03-13 오후 4 43 55" src="https://github.com/solmoonkang/spring-jpa/assets/109902582/54fe56de-76f4-4393-9caf-2420389a5c2e">

---
### 회원 기능
- 회원 등록
- 회원 조회

### 상품 기능
- 상품 등록
- 상품 수정
- 상품 조회

### 주문 기능
- 상품 주문
- 주문 내역 조회
- 주문 취소

## 🔎 도메인 모델 분석

---
- **회원과 주문의 관계**: **회원**은 여러 번 **주문**할 수 있다. *(1:N)*
- **주문과 상품의 관계**: **주문**할 때 여러 **상품**을 선택할 수 있다.
<br>
반대로 같은 **상품**도 여러 번 **주문**될 수 있다.
<br>
**주문상품**이라는 모델을 만들어서 *N:N* 관계를 *1:N*, *N:1* 관계로 풀어낸다.

<img width="764" alt="스크린샷 2024-03-13 오후 4 42 24" src="https://github.com/solmoonkang/spring-jpa/assets/109902582/58822f57-beb5-4d14-94e3-b253c8f5c222">

## 📠 테이블 설계

---
<img width="789" alt="스크린샷 2024-03-13 오후 4 42 35" src="https://github.com/solmoonkang/spring-jpa/assets/109902582/7660a9b8-44d0-4b5b-8e81-a4d4e87e0f53">

## 🔬 엔티티 설계와 매핑

---
<img width="806" alt="스크린샷 2024-03-13 오후 4 42 44" src="https://github.com/solmoonkang/spring-jpa/assets/109902582/d14afad3-3e21-4953-a04e-59bf993e1011">