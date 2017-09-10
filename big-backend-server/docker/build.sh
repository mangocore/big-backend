#!/usr/bin/env sh

cd ./../..
pwd
./gradlew clean --refresh-dependencies -x test -x check assemble -P projectEnv=prod --stacktrace

mv -f ./big-backend-server/build/libs/big-backend.jar ./big-backend-server/docker