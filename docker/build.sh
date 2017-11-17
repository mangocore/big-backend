#!/usr/bin/env sh

./gradlew clean --refresh-dependencies -x test -x check assemble -P projectEnv=prod --stacktrace

mv -f ./build/libs/big-backend.jar ./big-backend-server/docker