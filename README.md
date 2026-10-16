# 볼링 게임 카타

Uncle Bob의 [The Bowling Game Kata](http://www.butunclebob.com/ArticleS.UncleBob.TheBowlingGameKata)를 TDD(Red → Green → 코드 리뷰)로 진행한 Java 프로젝트입니다. `Game` 클래스의 `roll(int)`과 `score()`를 구현했고, 각 사이클은 `PLAN.md`의 테스트 하나씩을 기준으로 사람이 단계마다 승인하는 방식(`agentic-tdd`)으로 진행했습니다.

## 사이클별 진행

| # | 테스트 | Red | Green |
|---|--------|-----|-------|
| 1 | 거터 게임 → 0점 | 실패 확인 (`expected 0 but was -1`) | `score()`가 `0` 반환 |
| 2 | 전부 1핀 → 20점 | 실패 확인 (`expected 20 but was 0`) | `roll`이 핀 합산 |
| 3 | 스페어 보너스 → 16점 | 실패 확인 (`expected 16 but was 13`) | `rolls[]` 배열 + 프레임 순회, 스페어 분기 추가 |
| 4 | 스트라이크 보너스 → 24점 | 실패 확인 (`expected 24 but was 17`) | 스트라이크 분기 추가 (인덱스 1 전진) |
| 5 | 퍼펙트 게임 → 300점 | 처음부터 통과 | 구현 변경 없음 |
| 6 | 10프레임 스페어 → 17점 | 처음부터 통과 | 구현 변경 없음 |
| 7 | 10프레임 스트라이크 → 19점 | 처음부터 통과 | 구현 변경 없음 |
| 8 | 혼합 게임 → 167점 | 처음부터 통과 | 구현 변경 없음 |

5~8번은 4번의 스트라이크 구현이 이미 일반적인 형태여서 처음부터 통과했습니다. 이 네 테스트는 Red 실패를 직접 확인하지 못한 회귀 테스트입니다.

## 실행

Gradle 래퍼를 사용합니다 (Windows는 `gradlew.bat`).

```
./gradlew test
./gradlew test --tests "bowling.GameTest.mixedGame"
```

## 범위 밖

입력 검증(음수·11핀 이상·프레임 합 10 초과), 게임 종료 후 `roll` 거부, 미완성 게임의 점수 정책은 구현하지 않았습니다.
