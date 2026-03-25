# 🍴 냉비서 부탁해~

> **재료를 넣으면, 레시피가 나온다.**
> 1인 가구를 위한 냉장고 재료 기반 레시피 추천 서비스


## 📌 프로젝트 소개

**fridge2fork**는 혼자 사는 사람들을 위한 레시피 추천 서비스입니다.
냉장고에 있는 재료를 등록하면, 지금 당장 만들 수 있는 레시피를 추천해드려요.

-  보유 재료 기반 레시피 매칭
-  AI Agent — 대체 재료 제안 · 유통기한 자동 추론
-  냉장고 파먹기 챌린지 (유통기한 임박 재료 소진 이벤트)
-  공공 API 연동 칼로리 · 영양정보 자동 계산
-  Redis 기반 실시간 인기 레시피 랭킹
-  Kafka 기반 유통기한 알림

![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot_3.x-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL_8-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white)
![JPA/QueryDSL](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Apache Kafka](https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=apachekafka&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazon-aws&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)



---


## 🚀 기술 스택

### Backend — Spring Boot

| 분류 | 기술 |
|------|------|
| Language | Java 17 |
| Framework | Spring Boot 3.x |
| ORM | Spring Data JPA + QueryDSL |
| Database | MySQL 8 |
| Cache | Redis |
| Message Queue | Kafka |
| 인증 | Spring Security + JWT |
| API 문서 | Swagger (SpringDoc) |
| 외부 API | 식품안전처 공공데이터 API |
| 빌드 | Gradle |
| 인프라 | AWS EC2 + RDS + S3 |
| CI/CD | GitHub Actions + Docker |

### AI Service — Python

| 분류 | 기술 |
|------|------|
| Language | Python 3.11 |
| Framework | FastAPI |
| LLM | Gemini API / Claude API |
| 통신 | REST (Spring Boot → AI Service 내부 호출) |

### Frontend

| 분류 | 기술 |
|------|------|
| Framework | Next.js (React) |
| API 통신 | Axios / React Query |

---

## ✨ 서비스 시나리오

### Phase 1 · 식재료 입력 및 관리

마트에서 장 보고 온 재료나 냉장고에 있는 재료를 앱에 등록한다.

- 가공식품은 사용자가 유통기한 직접 입력
- 야채·신선식품은 **AI가 구매일 기준 예상 유통기한 자동 제안**
- 유통기한 임박 시 Kafka 이벤트 → 푸시 알림 발송

### Phase 2 · 레시피 탐색 및 AI 가이드

"오늘 뭐 먹지?" 고민할 때, 내 냉장고 재료 기반으로 레시피를 추천받습니다.

- **AI 대체 재료 제안**: "고추장이 없어요. 두반장은 어떠세요?"
- **부족 재료 안내**: "달걀 하나만 더 사면 만들 수 있어요"
- **영양 정보**: 공공 API 연동으로 칼로리·탄단지 자동 계산

### Phase 3 · 냉장고 파먹기 챌린지

유통기한 임박 재료가 3개 이상 쌓이면 AI가 챌린지를 제안합니다.

> "유통기한 3일 남은 양파·스팸·달걀이 있어요! 스팸 덮밥 어때요? 🍳"

- 유통기한 임박순 상위 3개 재료로 최적 레시피 자동 매칭
- 요리 완료 시 **냉파 성공 배지** 및 포인트 적립

### Phase 4 · 기록 및 소셜 활동

요리 후 식사 기록을 남기고 다른 유저와 소통합니다.

- 별점 · 댓글 · 좋아요
- 실시간 인기 레시피 랭킹 (Redis Sorted Set)
- 일일 칼로리 누적 계산
- 1주일 식단 추천

---

## 🏗 아키텍처

```
[ Next.js Frontend ]
        │  REST API
        ▼
[ ALB / Nginx ]
        │
        ▼
[ Spring Boot — Modular Monolith ]  ──REST──▶  [ Python FastAPI — AI Service ]
        │                                               │
        ├── RDS MySQL    (메인 데이터)                  └── Gemini / Claude API
        ├── Redis        (랭킹, JWT 블랙리스트)
        └── Kafka        (유통기한 이벤트 브로커)
                │
                ▼
        [ Notification Consumer ]

[ GitHub Actions → Docker Build → EC2 배포 ]
```

> **AI Service는 처음부터 별도 서버(EC2)로 분리 배포합니다.**
> Spring Boot에서 내부 REST 호출로 연동하며, 추후 MSA 전환 시 API Gateway만 앞에 추가하면 됩니다.

---

## 📁 패키지 구조 (Modular Monolith)

Spring Boot 내부를 도메인 단위로 완전히 격리합니다.
각 도메인은 `api / application / domain / infrastructure` 4계층을 가지며,
**도메인 간 직접 의존을 금지하고 이벤트·인터페이스로만 통신**합니다.
특정 도메인을 MSA로 분리할 때 해당 패키지만 떼어내면 됩니다.

```
fridge2fork
├── domain
│   ├── member            # 회원가입/로그인, JWT, 알러지 설정
│   │   ├── api           # MemberController
│   │   ├── application   # MemberService, MemberUseCase (인터페이스)
│   │   ├── domain        # Member 엔티티, 도메인 이벤트
│   │   └── infrastructure# MemberRepository, JPA 구현체
│   │
│   ├── inventory         # 냉장고 재료 관리
│   │   ├── api
│   │   ├── application   # FridgeService, AI 유통기한 추론 호출
│   │   ├── domain        # FridgeItem 애그리거트, ExpiryEstimated 이벤트
│   │   └── infrastructure# FridgeItemRepository, AIServiceClient
│   │
│   ├── recipe            # 레시피 CRUD + 추천 + 챌린지 매칭
│   │   ├── api
│   │   ├── application   # RecipeService, RecommendService, ChallengeService
│   │   ├── domain        # Recipe 애그리거트, RecipeMatchPolicy
│   │   └── infrastructure# RecipeRepository, QueryDSL 구현체
│   │
│   ├── community         # 댓글, 별점, 좋아요, Redis 랭킹
│   │   ├── api
│   │   ├── application   # CommentService, LikeService, RankingService
│   │   ├── domain        # Comment, Like 엔티티
│   │   └── infrastructure# Redis Sorted Set 연동
│   │
│   ├── diet              # 식사 기록, 칼로리 합산, 식단 추천
│   │   ├── api
│   │   ├── application   # MealLogService, NutritionService
│   │   ├── domain        # MealLog 엔티티
│   │   └── infrastructure# 공공 API 영양정보 클라이언트
│   │
│   └── notification      # Kafka Consumer, 알림 발송
│       ├── application   # NotificationConsumer
│       └── infrastructure# FCM / 이메일 어댑터
│
└── global
    ├── config            # Security, Redis, Kafka, Swagger 설정
    ├── event             # 도메인 이벤트 인터페이스 (도메인 간 결합 차단)
    ├── exception         # 공통 예외 처리
    └── util              # JWT 유틸, 공통 응답 포맷
```

> **MSA 전환 우선순위**: community(트래픽 집중) → notification → diet 순 분리를 권장합니다.

---

## 🔑 핵심 구현

### 레시피 추천 로직

냉장고 재료를 기반으로 **QueryDSL**로 레시피를 매칭합니다.

`매칭률 = (보유 재료 수 / 전체 필요 재료 수) × 100` 순으로 정렬

```java
// 보유 재료와 1개 이상 겹치는 레시피 조회
SELECT r, COUNT(ri) as matchCount
FROM Recipe r
JOIN RecipeIngredient ri ON ri.recipe = r
WHERE ri.ingredient.id IN :ownedIngredientIds
GROUP BY r
ORDER BY matchCount DESC
```

### AI Service 연동 (도메인 간 결합 차단)

`inventory` 도메인은 AI Service를 인터페이스로만 참조합니다.
구현체를 교체하거나 MSA로 분리해도 도메인 코드는 바뀌지 않습니다.

```java
// domain 계층 — 인터페이스만 정의
public interface AIServiceClient {
    ExpiryEstimate estimateExpiry(String ingredientName, LocalDate purchasedAt);
    List<String> suggestAlternatives(String ingredientName, String recipeContext);
}

// infrastructure 계층 — FastAPI 호출 구현체
@Component
public class AIServiceHttpClient implements AIServiceClient {
    // Spring Boot → Python FastAPI REST 호출
}
```

### 냉장고 파먹기 챌린지 매칭

```java
// 유통기한 임박순 상위 3개 재료로 최적 레시피 자동 매칭
List<FridgeItem> urgentItems = fridgeItemRepository
    .findTop3ByUserIdOrderByExpiryDateAsc(userId);

List<Long> urgentIngredientIds = urgentItems.stream()
    .map(FridgeItem::getIngredientId)
    .toList();

Recipe matched = recommendService.matchBestRecipe(urgentIngredientIds);
```

### Redis 인기 랭킹

**Redis Sorted Set**으로 실시간 인기 레시피를 랭킹합니다.

점수 = `조회수 + (좋아요 수 × 2)`

```java
redisTemplate.opsForZSet().incrementScore("recipe:ranking", recipeId, score);
```

### Kafka 유통기한 알림

```
Producer : ExpiryScheduler    → Topic: ingredient-expiry
Consumer : NotificationConsumer → 유저에게 푸시 알림 발송
```

---

## 🗄 ERD

> 11개 테이블 · `USERS`와 `RECIPES` 중심 설계

```
USERS
 ├── USER_INGREDIENTS   (냉장고)
 ├── RECIPES
 │    ├── RECIPE_INGREDIENTS
 │    ├── RECIPE_STEPS
 │    ├── COMMENTS
 │    ├── LIKES
 │    ├── MEAL_LOGS
 │    └── CHALLENGE_LOGS  (냉파 챌린지 기록)
 └── (INGREDIENTS 재료 마스터)
```

---

## 🌐 API 명세

Base URL: `https://api.fridge2fork.com/api/v1`

| 메서드 | 엔드포인트 | 설명 | 인증 |
|--------|-----------|------|------|
| POST | `/auth/register` | 회원가입 | ✗ |
| POST | `/auth/login` | 로그인 | ✗ |
| GET | `/fridge` | 냉장고 재료 조회 | ✓ |
| POST | `/fridge` | 재료 등록 (AI 유통기한 추론 포함) | ✓ |
| GET | `/recipes/recommend` | 재료 기반 추천 | ✓ |
| POST | `/recipes` | 레시피 등록 | ✓ |
| GET | `/recipes/{id}/missing` | 부족한 재료 조회 | ✓ |
| GET | `/recipes/{id}/alternatives` | AI 대체 재료 제안 | ✓ |
| POST | `/recipes/{id}/likes` | 좋아요 토글 | ✓ |
| GET | `/ranking/recipes` | 인기 랭킹 조회 | ✗ |
| GET | `/challenge` | 냉파 챌린지 레시피 조회 | ✓ |
| POST | `/challenge/complete` | 챌린지 완료 (배지·포인트 적립) | ✓ |
| POST | `/meal-logs` | 식사 기록 | ✓ |
| GET | `/meal-logs/today` | 오늘 칼로리 합산 | ✓ |

> 전체 API 명세는 Swagger UI에서 확인: `/swagger-ui/index.html`

---

## 🗓 개발 로드맵

> **전략**: Modular Monolith로 먼저 배포 → 트래픽 집중 도메인부터 점진적 MSA 분리

| 기간 | 목표 |
|------|------|
| 1~2주차 | 프로젝트 세팅, ERD 확정, Modular Monolith 패키지 구조 확립, JWT 인증 |
| 3~4주차 | 냉장고 재료 관리, AI Service(FastAPI) 기본 연동, 유통기한 추론 |
| 5~6주차 | 레시피 CRUD, 재료 기반 추천 엔진, AI 대체 재료 제안, 공공 API 영양정보 |
| 7~8주차 | 냉파 챌린지, 댓글·좋아요, Redis 랭킹, Kafka 유통기한 알림 |
| 9~10주차 | 식사 기록, 식단 추천, 프론트엔드 API 연동 마무리 |
| 11~12주차 | 테스트 코드, AWS 배포, 모니터링, README 정리 |

---

## ⚙️ 로컬 실행 방법

### 요구사항

- Java 17+
- Python 3.11+
- Docker & Docker Compose

### 실행

```bash
git clone https://github.com/hi4579675/fridge2fork.git
cd fridge2fork

# 인프라 실행 (MySQL, Redis, Kafka)
docker-compose up -d

# AI Service 실행 (별도 터미널)
cd ai-service
pip install -r requirements.txt
uvicorn main:app --port 8081

# Spring Boot 실행
./gradlew bootRun
```

### 환경변수

```env
# Database
DB_URL=jdbc:mysql://localhost:3306/fridge2fork
DB_USERNAME=root
DB_PASSWORD=your_password

# JWT
JWT_SECRET=your_jwt_secret
JWT_EXPIRATION=3600000

# Redis
REDIS_HOST=localhost
REDIS_PORT=6379

# Kafka
KAFKA_BOOTSTRAP_SERVERS=localhost:9092

# AI Service
AI_SERVICE_URL=http://localhost:8081

# 외부 API
PUBLIC_API_KEY=your_public_api_key
GEMINI_API_KEY=your_gemini_api_key
```

---

## 🐛 트러블슈팅

> *(개발하면서 겪은 문제와 해결 과정을 여기에 채워나갑니다)*

---

## 📄 라이선스

MIT License © 2025 fridge2fork
