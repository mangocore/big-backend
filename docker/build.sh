#!/usr/bin/env sh

cd ..

./gradlew clean --refresh-dependencies -x test -x check assemble -P projectEnv=prod --stacktrace

mv -f ./build/libs/big-backend.jar ./docker