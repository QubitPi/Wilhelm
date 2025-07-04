# Copyright 2025 Jiaqi Liu. All rights reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
FROM ubuntu:latest

LABEL maintainer="Jiaqi (Jack) Liu"
LABEL maintainer-email="jack20220723@gmail.com"

WORKDIR /app

RUN apt update
RUN apt upgrade -y
RUN apt install golang-go -y
RUN apt install make -y
RUN apt install curl -y

COPY . .

# Command to run the application
ENTRYPOINT ["make", "run"]
