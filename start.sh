#!/bin/bash
sbt reload clean assembly
docker build -t mystub041217 .
docker run -p 8089:8080 mystub041217