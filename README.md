# 和暦の取得、元号の追加

## 和暦の取得

`java.time.chrono.JapaneseDate`を作って`java.time.format.DateTimeFormatter`で`Gy年M月d日`といったパターンでフォーマットすると`令和4年5月1日`のような文字列を得られる。

## 元号の追加

システムプロパティ`jdk.calendar.japanese.supplemental.era`に値を設定すれば元号を追加できる。

値には`name`、`abbr`、`since`の3つのパラメーターを設定する。

例えば次のシステムプロパティで西暦3000年1月1日から新しい元号「新元号」を追加できる。

```
-Djdk.calendar.japanese.supplemental.era="name=新元号,abbr=N,since=32503680000000"
```

追加できる元号は1つだけという制約がある（と思う。JDKのソースコードを見た限り1つだけしか追加できなさそう）。

### Spring BootのWebアプリケーション

メインクラスで`SpringApplication#run`する前に`System#setProperty`で元号を追加してみた。

次のいずれの起動方法でも適用されたので、元号を追加したい場合はメインクラスにハードコーディングすれば良さそう。

```
./mvnw spring-boot:run
```

```
./mvnw package
java -jar target/spring-boot-japanese-era-example-0.0.1-SNAPSHOT.jar
```

```
./mvnw spring-boot:build-image
docker run --rm -p 8080:8080 spring-boot-japanese-era-example:0.0.1-SNAPSHOT
```

もちろんアプリケーション起動時にパラメーターでシステムプロパティを渡しても良いけれど、「元号の追加」なんかハードコーディングすればええやろ、という気持ち。

