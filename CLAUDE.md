# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

# 볼링 게임 카타

Uncle Bob(로버트 C. 마틴)의 [The Bowling Game Kata](http://www.butunclebob.com/ArticleS.UncleBob.TheBowlingGameKata)에서 영감을 받았습니다. TDD(Red → Green → Refactor)로 `Game` 클래스를 만드는 연습 프로젝트입니다.

## 명령어

Gradle 래퍼 사용 (Windows는 `gradlew.bat`, Git Bash/PowerShell은 `./gradlew`).

- 전체 테스트: `./gradlew test`
- 한 클래스: `./gradlew test --tests "bowling.GameTest"`
- 한 메서드: `./gradlew test --tests "bowling.GameTest.gutterGame"`
- 빌드: `./gradlew build`

별도 린터/포매터 설정은 없습니다.

## 현재 프로젝트 상태

- 빌드 스크립트는 **Kotlin DSL**(`build.gradle.kts`, `settings.gradle.kts`)이며 JUnit BOM은 `6.0.0`입니다. 툴체인 버전은 지정돼 있지 않아 실행 중인 JDK를 사용합니다 (IntelliJ 프로젝트 생성 기본값). `JavaCompile`은 UTF-8로 지정돼 있습니다 (Windows 기본 인코딩 cp949에서 한글 주석 때문에 컴파일이 실패하던 문제 방지).
- `bowling.Game`(`src/main/java/bowling/Game.java`)이 구현돼 있습니다. `roll`은 투구를 `rolls[21]` 배열에 순서대로 저장하고, `score()`는 10프레임을 순회하며 스트라이크(보너스 다음 2투구) → 스페어(보너스 다음 1투구) → 일반 프레임 순으로 판별해 계산합니다. 10번째 프레임을 따로 처리하지 않고, 배열 21칸이 추가 투구를 그대로 담습니다.
- `GameTest`(`src/test/java/bowling/GameTest.java`)에는 `PLAN.md`의 테스트 8개(거터, 전부 1핀, 스페어, 스트라이크, 퍼펙트 게임, 10프레임 스페어/스트라이크, 혼합 게임 167점)가 모두 통과합니다.
- 입력 검증은 없습니다. 음수·11핀 이상·프레임 합 10 초과나 게임 종료 후 `roll`을 거부하지 않으며, 21번을 넘겨 `roll`하면 `ArrayIndexOutOfBoundsException`이 납니다.
- `src/main/java/org/example/Main.java`는 IntelliJ가 만든 예제 파일이며 카타와 무관합니다. 카타 코드는 `bowling` 패키지에 둡니다.
- 테스트 스타일: JUnit 5+ API(`org.junit.jupiter.api.*`), `@BeforeEach`에서 `Game` 생성, 한국어 `@DisplayName`, `assertEquals(기대값, game.score())`.

## 작업 방식 (TDD 스킬)

`.claude/skills/`에 두 스킬이 있습니다.

- `test-driven-development`: 실패하는 테스트 없이는 프로덕션 코드를 쓰지 않습니다. 실패를 직접 확인하고, 최소 구현만 하고, 초록불에서만 리팩터링합니다.
- `agentic-tdd`: 사람이 단계마다 승인하는 방식 (Red Plan → Red → Green → 코드 리뷰). `PLAN.md`를 먼저 쓰고, **승인 없이 다음 단계로 가지 않으며, `git add/commit/push`는 사용자가 직접 합니다.**

## 볼링 규칙

게임은 10프레임입니다. 각 프레임에서 플레이어는 두 번 공을 굴려 핀 10개를 쓰러뜨립니다. 프레임 점수는 쓰러뜨린 핀의 총합에 스트라이크·스페어 보너스를 더한 값입니다.

- **스페어**: 두 번의 투구로 핀 10개를 모두 쓰러뜨린 경우. 보너스는 다음 투구 한 번의 핀 수.
- **스트라이크**: 첫 투구에 핀 10개를 모두 쓰러뜨린 경우. 프레임은 투구 한 번으로 끝나며, 보너스는 다음 두 번의 투구 값.
- **10번째 프레임**: 스페어나 스트라이크를 치면 프레임을 마무리하기 위해 추가 투구를 할 수 있습니다. 단, 최대 세 번까지만 굴릴 수 있습니다.

## 요구사항

메서드 두 개를 가진 `Game` 클래스를 작성합니다.

1. `void roll(int)` — 공을 굴릴 때마다 호출. 인자는 쓰러뜨린 핀의 개수.
2. `int score()` — 해당 게임의 총점 반환.
