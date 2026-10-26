## Dựa trên 1 image có sẵn để làm việc tiếp
#FROM eclipse-temurin:21-alpine
#LABEL authors="admin"
#
## Thư mục làm việc
#WORKDIR /app
#
## Tạo 1 user
## Lệnh addgroup (của alpine) sẽ tạo 1 nhóm người dùng mới
## -S nhóm system
## -g 10001 chỉ Goup Id là 10001
## app là tên group
## Lệnh adduser
## -D không dùng password
## -H không tạo home
## -u là User ID là 10001
## -G app cho user đang tạo vào group "app"
## app tên user
#RUN addgroup -S -g 10001 app && adduser -S -D -H -u 10001 -G app app
#
## Copy file jar (đã package từ Maven) của catalog-serivce vào image "application.jar"
#COPY target/se1905-catalog-service.jar /app/application.jar
#
## Quy định sẽ chạy container dưới quyền của nào 10001 (app)
## user sẽ là owner cho các dữ liệu sinh ra khi chạy container
#USER 10001:10001
#
## Báo cho biết khi container chạy, nó lắng nghe port 8080 (port trong container)
#EXPOSE 8081
#
## Lệnh mặc định sẽ được chạy khi run container
#ENTRYPOINT ["java", "-jar", "/app/application.jar"]

# Build stage
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]