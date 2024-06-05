
請實作一個列表畫面的 APP，並寫文件或註解來解釋設計考量。除了指定需求外，可以自由設計 model 和 UI 來提供更好的體驗。

## 技術規定

- Deployment Target 為 34。
- 請使用 Kotlin 和 Compose（或是 XML）開發。
- 可使用第三方 library。
- 可使用 GitHub Packages 或任何第三方管理工具。
- 請寫文件或註解來解釋你的設計考量。

## 需求

請實作一個列表畫面的 APP，需求如下：

#### 資料

- 請設計一個 Data Loader 的抽象層來提供課程資料。
- 請用專案中提供的 JSON file 實作上述 Data Loader 的一個實例。

    - courses.json

- 課程型態分為「企業課程」與「訂閱課程」兩種，對應到數據中的 `source` ：
    
    - 「企業課程」→ `TENANT_COURSE`
    - 「訂閱課程」→ `UNLIMITED_PRODUCT`

#### UI 設計

- 課程縮略圖
    - 左上角課程型態 tag →「企業課程」的場合顯示
    - 右下角訊息 tag
        - `recentStartedAssignment` 有值時 → 取 `recentStartedAssignment.assigners` 第 1 項中的 `name` ，並顯示「${name} 指派」
        - `lastViewedAt` 有值時用這個時間做計算並顯示「〇〇 天前觀看」
        - 其餘的場合 → 不顯示 tag

- 課程 title
    - 行數至多 2 行
    - `recentStartedAssignment` 有值時 → 取 `recentStartedAssignment.rule` 來決定 title 前面要顯示的 tag 內容
        - 「必修」→ `COMPULSORY`
        - 「選修」→ `ELECTIVE`

- 通過文案
    - `studiedAt` 有值時用這個時間做計算並顯示「yyyy-MM-dd 通過」
    - 其餘的場合 → 不顯示

- 期限文案
    - `studiedAt` 有值時 → 不顯示，因為已經通過課程
    - `recentStartedAssignment` 有值時
        - 取 `recentStartedAssignment.timeline.dueAt` 以及當下的時間差來決定要顯示的內容，以下也提供了一些例子
            - 8 日以上 → 「yyyy-MM-dd 截止」
            - 1 日以上未滿 8 日 → 「截止日剩 〇〇 天」
            - 未滿 1 日 → 「〇〇 天內截止」
            - 0 日以下 → 「已逾期」

            | dueAt                | now                  | 期待値           |
            |----------------------|----------------------|---------------|
            | 2022-04-01T00:00:00Z | 2022-04-01T00:00:00Z | 已逾期           |
            | 2022-04-01T01:00:00Z | 2022-04-01T00:00:00Z | 1 天內截止        |
            | 2022-04-02T00:00:00Z | 2022-04-01T00:00:00Z | 截止日剩 1 天      |
            | 2022-04-02T01:00:00Z | 2022-04-01T00:00:00Z | 截止日剩 1 天      |
            | 2022-04-03T00:00:00Z | 2022-04-01T00:00:00Z | 截止日剩 2 天      |
            | 2022-04-03T01:00:00Z | 2022-04-01T00:00:00Z | 截止日剩 2 天      |
            | 2022-04-08T00:00:00Z | 2022-04-01T00:00:00Z | 截止日剩 7 天      |
            | 2022-04-08T01:00:00Z | 2022-04-01T00:00:00Z | 截止日剩 7 天      |
            | 2022-04-09T00:00:00Z | 2022-04-01T00:00:00Z | 2022-04-09 截止 |

        - 顯示內容的文字顏色
            - 「必修」→ 紅色
            - 「選修」→ 默認顏色
    - 其餘的場合 → 顯示「無期限」，並且文字顏色為默認顏色

- 右下角 more icon
    - 顯示但可以不用實作點擊功能

- 提示
    - 不用在意卡片尺寸、顏色、間距等細節，請將重點放在如何排版。（你仍然可以盡量符合示意圖）

- 示意圖

    <img width="300" alt="CleanShot 2021-12-09 at 10 59 30@2x" src="https://github.com/hahow/android-recruit-project/assets/15129081/70ad7c8d-5298-4506-a7ab-1cfdf6fc9ed9">

## 提交

- 請下載或 fork Hahow Android Engineer 面試題目初始專案。
- 請將成果上傳至 GitHub 並直接提供 repo 連結。

---

# Check list

- [x] 程式碼使用 Kotlin 撰寫。
- [x] 使用 Compose。
- [x] 註解。
- [x] 撰寫測試。
- [x] Target 34。
- [x] 使用courses.json作為資料來源。

# Tech stack & Open-source libraries

- 使用 Material3
- 100% Kotlin
- 使用 Koin 進行 injection
- 使用 Coroutines & Flow 處理 API
- 使用 Truth 搭配 Junit5 做 DateTime 測試
- 使用 Ktlint format code
- 模組化
- 使用 MockWebServer 模擬 API
- 使用 version catalog 管理 dependencies
- 使用 composite build

# Demo
![手機畫面](https://res.cloudinary.com/deu7aohfe/image/upload/v1717589927/github/hzxijzoqbcpngudiiigi.webp)