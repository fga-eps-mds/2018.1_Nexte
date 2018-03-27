# Command to build
# docker run --tty --interactive --volume=$(pwd)://application nexte_android  /bin/sh -c "./gradlew build"

FROM openjdk:8

# Setup environments to android sdk
ENV SDK_URL="https://dl.google.com/android/repository/sdk-tools-linux-3859397.zip"
ENV ANDROID_HOME="/usr/local/android-sdk"
ENV ANDROID_VERSION=26
ENV ANDROID_BUILD_TOOLS_VERSION=27.0.3

ENV PATH ${PATH}:${ANDROID_HOME}/tools:${ANDROID_HOME}/platform-tools

# Download Android SDK
RUN mkdir "$ANDROID_HOME" .android \
    && cd "$ANDROID_HOME" \
    && curl -o sdk.zip $SDK_URL \
    && unzip sdk.zip \
    && rm sdk.zip \
    && yes | $ANDROID_HOME/tools/bin/sdkmanager --licenses

# Create this empty file to work around problems in sdkmanager update
RUN touch ~/.android/repositories.cfg

# Install Android Build Tool and Libraries
RUN $ANDROID_HOME/tools/bin/sdkmanager --update
RUN $ANDROID_HOME/tools/bin/sdkmanager "build-tools;${ANDROID_BUILD_TOOLS_VERSION}" \
    "platforms;android-${ANDROID_VERSION}" \
    "platform-tools" \
    "system-images;android-26;google_apis_playstore;x86"

# Create an emulator
RUN echo "no" | $ANDROID_HOME/tools/bin/avdmanager create avd -n nexteavd -k "system-images;android-26;google_apis_playstore;x86"

# Create workspace
RUN mkdir /application
WORKDIR /application
