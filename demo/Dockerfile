FROM openjdk:11-slim

WORKDIR /app

RUN apt-get update && apt-get install -y \
    libx11-6 libxext6 libxrender1 libxtst6 libxi6 libgtk-3-0 mesa-utils wget unzip xvfb \
    && rm -rf /var/lib/apt/lists/*

RUN mkdir -p /javafx-sdk \
    && wget -O javafx.zip https://download2.gluonhq.com/openjfx/13.0.2/openjfx-13.0.2_linux-x64_bin-sdk.zip \
    && unzip javafx.zip -d /javafx-sdk \
    && mv /javafx-sdk/javafx-sdk-13.0.2/lib /javafx-sdk/lib \
    && rm -rf /javafx-sdk/javafx-sdk-13.0.2 javafx.zip

# Copy your fat JAR
COPY target/demo-1.0-SNAPSHOT-shaded.jar app.jar

# Start Xvfb and run JavaFX app
CMD bash -c "Xvfb :99 -screen 0 1024x768x16 & export DISPLAY=:99 && java --module-path /javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -jar app.jar"